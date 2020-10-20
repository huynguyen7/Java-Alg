import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class TopologicalSort {
	public static void main(String[] args) {
		int[][] graph1 = {{},{},{3},{1},{0,1},{0,2}};
		showResults(graph1); // expect 5 4 2 3 1 0
							// or 4 5 2 3 1 0

		int[][] graph2 = {{1,3},{2,3},{},{2,4},{}};
		showResults(graph2);
	}

	private static void showResults(int[][] graph) {
		System.out.println("----ShowResults----");
		for(int[] arr: graph)
			System.out.println(Arrays.toString(arr));
		System.out.println("\n");

		List<Integer> rs = topologicalOrder(graph);
		System.out.println(rs.toString() + "\n");
	}

	// GOOD EXPLANATION:
	// https://www.youtube.com/watch?v=eL-KzMXSXXI

	// NOTES:
	// Topological orderings are NOT unique.
	// Graph must be DAG in order to have a topological ordering.

	// Using dfs.
	// Time: O(V+E), space: O(V)
	public static List<Integer> topologicalOrder(int[][] graph) {
		List<Integer> rs = new ArrayList<>();
		if(graph.length == 0) return rs;

		boolean[] visited = new boolean[graph.length];
		for(int source = 0; source < graph.length; ++source) {
			if(!visited[source]) // not visited
				dfs(graph, visited, rs, source);
		}
		Collections.reverse(rs); // time: O(V)

		return rs;
	}

	private static void dfs(int[][] graph, boolean[] visited, List<Integer> rs, int source) {
		visited[source] = true;
		for(int vertex: graph[source]) {
			if(!visited[vertex]) // not visited
				dfs(graph, visited, rs, vertex);
			
		}
		rs.add(source);
	}
}

// Constraints:
// - Input graph is a DAG.
