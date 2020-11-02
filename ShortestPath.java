import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// Dijkstra's algorithm.
public class ShortestPath {
	public static void main(String[] args) {
		// Graph 1
		List<List<Edge>> graph1 = new ArrayList<>();
		List<Edge> edgesHolder = new ArrayList<>();

		edgesHolder.add(new Edge(0,1,2));
		edgesHolder.add(new Edge(0,2,4));
		graph1.add(new ArrayList<>(edgesHolder));
		edgesHolder.clear();

		edgesHolder.add(new Edge(1,2,1));
		edgesHolder.add(new Edge(1,3,7));
		graph1.add(new ArrayList<>(edgesHolder));
		edgesHolder.clear();

		edgesHolder.add(new Edge(2,4,3));
		graph1.add(new ArrayList<>(edgesHolder));
		edgesHolder.clear();


		edgesHolder.add(new Edge(3,5,1));
		graph1.add(new ArrayList<>(edgesHolder));
		edgesHolder.clear();

		edgesHolder.add(new Edge(4,3,2));
		edgesHolder.add(new Edge(4,5,5));
		graph1.add(new ArrayList<>(edgesHolder));
		edgesHolder.clear();

		graph1.add(new ArrayList<>(edgesHolder));
		edgesHolder.clear();

		showResults(graph1, 0); // expect {0,2,3,8,6,9}

		// Graph 2
		List<List<Edge>> graph2 = new ArrayList<>();
		
		edgesHolder.add(new Edge(0,1,6));
		edgesHolder.add(new Edge(0,3,1));
		graph2.add(new ArrayList<>(edgesHolder));
		edgesHolder.clear();

		edgesHolder.add(new Edge(1,4,2));
		graph2.add(new ArrayList<>(edgesHolder));
		edgesHolder.clear();

		graph2.add(new ArrayList<>(edgesHolder));
		edgesHolder.clear();

		edgesHolder.add(new Edge(3,1,2));
		edgesHolder.add(new Edge(3,4,1));
		graph2.add(new ArrayList<>(edgesHolder));
		edgesHolder.clear();

		edgesHolder.add(new Edge(4,2,5));
		graph2.add(new ArrayList<>(edgesHolder));
		edgesHolder.clear();

		showResults(graph2, 0); // expect {0,3,7,1,2}
	}

	private static void showResults(List<List<Edge>> graph, int source) {
		System.out.println("----ShowResults----");
		System.out.println("GRAPH: ");
		for(List<Edge> l: graph)
			System.out.println(l.toString());

		System.out.printf("\nShortest path from %d:\n", source);
		int[] shortestDists = findShortestPath(graph, source);
		System.out.println(Arrays.toString(shortestDists) + "\n");
	}

	// GOOD EXPLANATION:
	// https://www.youtube.com/watch?v=XB4MIexjvY0
	// https://www.youtube.com/watch?v=pVfj6mxhdMw
	// https://www.youtube.com/watch?v=K_1urzWrzLs

	// Greedy approach.
	// E is the number of edges, V is the number of vertices.
	// Time: O(ElogV), space: O(V)
	public static int[] findShortestPath(List<List<Edge>> graph, int source) {
		if(graph == null || graph.size() == 0) return null;
		if(source >= graph.size()) return null;

		boolean[] visited = new boolean[graph.size()];
		int[] shortestDists = new int[graph.size()];
		Arrays.fill(shortestDists, Integer.MAX_VALUE);
		shortestDists[source] = 0; // dist from source to source is 0

		PriorityQueue<ShortestPathToNode> minHeap = new PriorityQueue<>(
			(a, b) -> Integer.compare(a.weight, b.weight)
		); // space: O(V)

		Integer startNode = Integer.valueOf(source);
		while(startNode != null) {
			for(Edge e: graph.get(startNode)) { // time: O(E)
				int shortestTo = shortestDists[e.v] + e.weight;
				shortestDists[e.w] = Math.min(shortestDists[e.w], shortestTo);
				if(!visited[e.w])
					minHeap.add(new ShortestPathToNode(Integer.valueOf(e.w), shortestDists[e.w])); // time: O(logV)
			}

			visited[startNode] = true;
			startNode = minHeap.size() == 0 ? null : minHeap.poll().w; // time: O(logV)
		}

		return shortestDists;
	}

	// Directed-edge
	static class Edge {
		int v; // source.
		int w; // destination.
		int weight;
		
		public Edge(int v, int w, int weight) {
			this.weight = weight;
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return String.format("[%d->%d, %d]", v, w, weight);
		}
	}

	static class ShortestPathToNode {
		Integer w;
		int weight;
		
		public ShortestPathToNode(Integer w, int weight) {
			this.w = w;
			this.weight = weight;
		}
	}
}

// Constraints:
// - Input graph is weighted-directed graph.
// - node's value should be in [0, graph.size())
// - weight >= 0
