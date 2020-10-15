import java.util.Stack;
import java.util.NoSuchElementException;

// This class represents an undirected graph of vertices.
// This implementation uses adjacency-lists representation. (Bag class)
// Constraints:
// - All vertex values need to be: 0 <= v <= V; v is vertex value.

public class Graph {
	public static void main(String[] args) {
		Graph G = new Graph(new In(args[0]));
		System.out.println(G.toString());
		System.out.println(G.degree(9));
		System.out.println(G.adj(9));
	}

	private static final String NEWLINE = System.getProperty("line.separator"); // define NEWLINE based on different OS.

	private final int V; // number of vertices.
	private int E; // number of edges.
	private Bag<Integer>[] adj; // list of linked-lists.

	public Graph(int V) { // init graph with the number of vertices V.
		if(V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative.");
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for(int v = 0; v < V; ++v)
			adj[v] = new Bag<Integer>();
	}

	public Graph(In in) { // read text file
		if(in == null) throw new IllegalArgumentException("Argument is null.");

		try {
			this.V = in.readInt();
			if(V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative.");

			adj = (Bag<Integer>[]) new Bag[V];
			for(int v = 0; v < V; ++v)
				adj[v] = new Bag<Integer>();

			int E = in.readInt();
			if(E < 0) throw new IllegalArgumentException("Number of edges must be non-negative");

			for(int i = 0; i < E; ++i) {
				int v = in.readInt();
				int w = in.readInt();
				validateVertex(v);
				validateVertex(w);
				addEdge(v, w);
			}
		} catch(NoSuchElementException e) {
			throw new IllegalArgumentException("Invalid input format.");
		}
	}

	public Graph(Graph G) {
		this.V = G.V();
		this.E = G.E();
		if(V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");

		adj = (Bag<Integer>[]) new Bag[V];
		for(int v = 0; v < V; ++v)
			adj[v] = new Bag<Integer>();

		for(int v = 0 ; v < G.V(); ++v) {
			Stack<Integer> reverse = new Stack<Integer>();
			for(int w: G.adj[v])
				reverse.push(w);
			for(int w: reverse)
				adj[v].add(w);
		}
	}

	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}

	// IMPORTANT: This code won't handle if edge already exists.
	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		E++; // increment number of edges
		adj[v].add(w); // connect edge v -> w
		adj[w].add(v); // connect edge w -> v
	}

	// vertices adjacent to v.
	public Iterable<Integer> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

	public int degree(int v) {
		validateVertex(v);
		return adj[v].size();
	}

	private void validateVertex(int v) {
		if(v < 0 || v >= V)
			throw new IllegalArgumentException(String.format("Vertex %d is not between 0 and %d", v, V - 1));
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edge " + NEWLINE);
		for(int v = 0; v < V; ++v) {
			s.append(v + ": ");
			for(int w: adj[v])
				s.append(w + " ");
			s.append(NEWLINE);
		}

		return s.toString();
	}
}
