import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Deque;
import java.util.LinkedList;

// leetcode 785.
public class IsGraphBipartite {
	public static void main(String[] args) {
		int[][] graph1 = {{1,3}, {0,2}, {1,3}, {0,2}};
		showResults(graph1); // expect true

		int[][] graph2 = {{1,2,3}, {0,2}, {0,1,3}, {0,2}};
		showResults(graph2); // expect false

		int[][] graph3 = {{},{2,4,6},{1,4,8,9},{7,8},{1,2,8,9},{6,9},{1,5,7,8,9},{3,6,9},{2,3,4,6,9},{2,4,5,6,7,8}};
		showResults(graph3); // expect false

		int[][] graph4 = {{3,4,6},{3,6},{3,6},{0,1,2,5},{0,7,8},{3},{0,1,2,7},{4,6},{4},{}};
		showResults(graph4); // expect true
	}

	private static void showResults(int[][] graph) {
		System.out.println("----ShowResults----");
		for(int[] adj: graph)
			System.out.println(Arrays.toString(adj));
		System.out.println();
		boolean rs = isBipartite(graph);
		System.out.printf("Bipartite graph: %b\n\n", rs);
	}

	// GOOD EXPLANATION:
	// https://www.youtube.com/watch?v=mVmXwFkgoJ0&t=49s

	// Using DFS.
	// Time: O(V+E), space: O(E)
	public static boolean isBipartite(int[][] graph) {

		boolean[] visited = new boolean[graph.length];
		// True means RED, False means BLUE.
		boolean[] colors = new boolean[graph.length];
		
		for(int sourceVertex = 0; sourceVertex < graph.length; ++sourceVertex) {
			if(!visited[sourceVertex]) { // unvisited
				if(!dfsVisit(graph, colors, visited, sourceVertex)) 
					return false;
			}
		}

		return true;
	}

	private static boolean dfsVisit(int[][] graph, boolean[] colors, boolean[] visited, int source) {
		visited[source] = true;

		for(int vertex: graph[source]) {
			if(!visited[vertex]) {
				colors[vertex] = !colors[source];
				if(!dfsVisit(graph, colors, visited, vertex))
					return false;
			} else if(colors[vertex] == colors[source])
				return false;
		}

		return true;
	}

	// Using BFS.
	// Time: O(V+E), space: O(E)
	public static boolean isBipartiteII(int[][] graph) {
		if(graph.length <= 1) return true;

		Deque<Integer> queue = new LinkedList<>();
		// -1 means unvisited, 0 is RED, 1 is BLUE.
		int[] colors = new int[graph.length];
		Arrays.fill(colors, -1); 
		
		
		for(int sourceVertex = 0; sourceVertex < graph.length; ++sourceVertex) {
			if(colors[sourceVertex] == -1) { // not visited.
				queue.addLast(sourceVertex);
				colors[sourceVertex] = 0;
				
				while(!queue.isEmpty()) {
					int source = queue.removeFirst();
					for(int vertex: graph[source]) {
						if(colors[vertex] == -1) {
							queue.addLast(vertex);
							if(colors[source] == 1)
								colors[vertex] = 0;
							else
								colors[vertex] = 1;
						} else {
							if(colors[vertex] == colors[source])
								return false;
						}
					}
				}
			}
		}

		return true;
	}
}

// Constraints:
// - Input graph is an undirected graph.
// - graph[i][j] = [0, V-1]; with V is the number of vertices.
// - graph[i] will not contain duplicates.
// - graph[i] will not contain i => No self loop.
