import java.util.Iterator;
import java.util.NoSuchElementException;

// This class represents an undirected graph of vertices.
// This implementation uses adjacency-matrix representation.
// Constraints:
// - All vertex values need to be: 0 <= v <= V; v is vertex value.:w

public class AdjacencyMatrixGraph {
	public static void main(String[] args) {
		AdjacencyMatrixGraph G = new AdjacencyMatrixGraph(4,7);
		System.out.println(G.toString());
	}

	private final int V; // number of vertices.
	private int E; // number of edges.
	private boolean[][] adj;

	// Create just an empty graph.
	public AdjacencyMatrixGraph(int V) {
		if(V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative.");
		this.V = V;
		this.E = 0;
		this.adj = new boolean[V][V];
	}

	// Assign random values to graph.
	public AdjacencyMatrixGraph(int V, int E) {
		if(V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative.");
		if(E > (long) V * (V - 1) / 2 + V) throw new IllegalArgumentException("Too many edges.");
		if(E < 0) throw new IllegalArgumentException("Number of edges must be a non-negative.");
		this.V = V;
		this.E = 0;
		this.adj = new boolean[V][V];

		while(this.E != E) {
			int v = StdRandom.uniform(V);
			int w = StdRandom.uniform(V);
			addEdge(v, w);
		}
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}
	
	public void addEdge(int v, int w) {
		if(!adj[v][w]) { // if this edge is not exist
			E++;
			adj[v][w] = true;
			adj[w][v] = true;
		}
	}

	public boolean contains(int v, int w) {
		return adj[v][w];
	}

	public Iterable<Integer> adj(int v) {
		return new AdjIterator(v);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(String.format("%d vertices, %d edges\n", V, E));
		for(int v = 0; v < V; ++v) {
			s.append(v + ": ");
			for(int w: adj(v))
				s.append(w + " ");
			s.append("\n");
		}

		return s.toString();
	}

	private class AdjIterator implements Iterator<Integer>, Iterable<Integer> {
		private int v;
		private int w;

		AdjIterator(int v) {
			this.v = v;
			this.w = 0;
		}

		public Iterator<Integer> iterator() {
			return this;
		}

		public boolean hasNext() {
			while(w < V) {
				if(adj[v][w]) return true;
				w++;
			}
			return false;
		}

		public Integer next() {
			if(!hasNext()) throw new NoSuchElementException();
			return w++;
		}
	}
}
