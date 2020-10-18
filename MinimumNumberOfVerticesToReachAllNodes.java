import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

// leetcode 1557.
public class MinimumNumberOfVerticesToReachAllNodes {
	public static void main(String[] args) {
		int n1 = 6;
		int[][] input1 = {{0,1},{0,2},{2,5},{3,4},{4,2}};
		showResults(n1, input1); // expect [0,3]

		int n2 = 5;
		int[][] input2 = {{0,1},{2,1},{3,1},{1,4},{2,4}};
		showResults(n2, input2); // expect [0,2,3]
	}
	
	// Find the smallest set of vertices from which all nodes in the graph are reachable. It's guaranteed that a unique solution exists.

	private static void showResults(int n, int[][] input) {
		System.out.println("----ShowResults----");
		System.out.printf("n = %d\n", n);
		List<List<Integer>> edges = new ArrayList<>();
		for(int[] arr: input) {
			System.out.println(Arrays.toString(arr));
			List<Integer> tmp = new ArrayList<>();
			for(int i: arr)
				tmp.add(i);
			edges.add(tmp);
		}

		System.out.println();
		List<Integer> rs = findSmallestSetOfVertices(n, edges);
		System.out.println(rs.toString() + "\n");
	}

	// Trick: Find the vertex which has degree 0.

	// E = edges.length, V = n
	// Time: O(V+E), space: O(E)
	public static List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
		List<Integer> rs = new ArrayList<>();
		Set<Integer> set = new HashSet<>(); // <vertex>
		
		for(List<Integer> edge: edges) // if vertex is not added to the set, so its degree is 0.
			set.add(edge.get(1));
		for(int i = 0; i < n; ++i)
			if(!set.contains(i))
				rs.add(i);
		
		return rs;
	}
}

// Constraints:
// - Input graph is a DAG.
// - edges.length >= 1
// - edges[i].length = 2
// - edges[i] = [0, n-1] with n is the number of vertices.
// - n >= 2
