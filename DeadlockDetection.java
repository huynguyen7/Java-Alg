import java.util.Arrays;

// element-prog 19.4
public class DeadlockDetection {
	public static void main(String[] args) {
		int[][] graph1 = {{1},{2},{3},{0}};
		showResults(graph1); // expect true

		int[][] graph2 = {{1,2},{},{3},{0}};
		showResults(graph2); // expect true

		int[][] graph3 = {{1},{2},{3},{}};
		showResults(graph3); // expect false
	}

	private static void showResults(int[][] graph) {
		System.out.println("----ShowResults----");
		for(int[] arr: graph)
			System.out.println(Arrays.toString(arr));
		System.out.println("\n");

		boolean rs = hasCycle(graph);
		System.out.printf("Directed graph has cycle: %b\n\n", rs);
	}

	// Detect if the graph has cycle
	
	// Using dfs
	// Time: O(V+E), space: O(E)
	public static boolean hasCycle(int[][] graph) {
		if(graph.length == 0) return false;

		boolean[] visited = new boolean[graph.length];
		for(int source = 0; source < graph.length; ++source) { // Time: O(V)
			if(!visited[source]) {
				if(dfs(graph, visited, source))
					return true;
			}
		}

		return false;
	}

	private static boolean dfs(int[][] graph, boolean[] visited, int source) {
		visited[source] = true;
		
		for(int vertex: graph[source]) {
			if(!visited[vertex]) {
				return dfs(graph, visited, vertex);
			} else return true;
		}

		return false;
	}
}

// Constraints:
// Input graph is a directed graph.
