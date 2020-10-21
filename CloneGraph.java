import java.util.*;

// leetcode 133, element-prog 19.5
public class CloneGraph {
	public static void main(String[] args) {
		
	}

	// GOOD EXPLANATION:
	// https://www.youtube.com/watch?v=vma9tCQUXk8

	// Using BFS.
	// Time: O(V+E), space: O(V)
	public static Node cloneGraph(Node node) {
		if(node == null) return null;

		Map<Node, Node> visited = new HashMap<>(); // <node-clone> pair.		
		Deque<Node> queue = new LinkedList<>();
		queue.addLast(node);
		visited.put(node, new Node(node.val));

		while(!queue.isEmpty()) {
			Node sourceNode = queue.removeFirst();

			// Copy vertices
			for(Node adjNode: sourceNode.neighbors) {
				if(!visited.containsKey(adjNode)) {
					queue.addLast(adjNode);
					visited.put(sourceNode, new Node(sourceNode.val));
				}

				// Copy edge
				visited.get(sourceNode).neighbors.add(visited.get(adjNode));
			}
		}
		
		return visited.get(node);
	}
	
	public static class Node {
		public int val;
		public List<Node> neighbors;

		public Node() {
			val = 0;
			neighbors = new ArrayList<>();
		}

		public Node(int val) {
			this.val = val;
			neighbors = new ArrayList<>();
		}

		public Node(int val, ArrayList<Node> neighbors) {
			this.val = val;
			this.neighbors = neighbors;
		}
	}
}

// Constraints:
// - Input graph is an undirected graph.
// - Input graph is connected.
// - graph[i][j] != i => No self loop.
// - graph[i][j] have no dups.
