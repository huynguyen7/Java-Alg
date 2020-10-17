public class DepthFirstSearch {
	public static void main(String[] args) {
		In in = new In(args[0]);
		Graph G = new Graph(in);
		int s = Integer.parseInt(args[1]);
		
		DepthFirstSearch dfs = new DepthFirstSearch(G, s);
		for(int v = 0; v < G.V(); ++v) {
			if(dfs.marked(v)) // if there is a s-v path
				System.out.printf("%d ", v);
		}
		System.out.println();

		// Check if Graph G is a connected graph.
		if(dfs.count() != G.V()) System.out.println("NOT CONNECTED.");
		else System.out.println("CONNECTED.");
	}

	// s is source vertex
	private boolean[] marked; // marked[v] = is there an s-v path?
	private int count; // number of vertices connected to source vertex.

	public DepthFirstSearch(Graph G, int s) {
		marked = new boolean[G.V()];
		validateVertex(s);
		dfs(G, s);
	}

	private void dfs(Graph G, int v) {
		count++;
		marked[v] = true;
		for(int w: G.adj(v)) {
			if(!marked[w])
				dfs(G, w);
		}
	}

	// Return if there is a path between the source vertex
	// and vertex v.
	public boolean marked(int v) {
		validateVertex(v);
		return marked[v];
	}

	public int count() {
		return count;
	}

	private void validateVertex(int v) {
		int V = marked.length;
		if(v < 0 || v >= V)
			throw new IllegalArgumentException(String.format("vertex %d is not between 0 and %d\n", v, V - 1));
	}
}
