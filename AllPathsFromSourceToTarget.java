import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// leetcode 797.
public class AllPathsFromSourceToTarget {
	public static void main(String[] args) {
		int[][] graph1 = {{1,2},{3},{3},{}};
		showResults(graph1); // expect [[0,1,3],[0,2,3]]

		int[][] graph2 = {{4,3,1},{3,2,4},{3},{4},{}}; 
		showResults(graph2); // expect [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]

		int[][] graph3 = {{1},{}};
		showResults(graph3); // expect [[0,1]]
		
		int[][] graph4 = {{1,2,3},{2},{3},{}};
		showResults(graph4); // expect [[0,1,2,3],[0,2,3],[0,3]]

		int[][] graph5 = {{1,3},{2},{3},{}};
		showResults(graph5); // expect [[0,1,2,3],[0,3]]
	}

	// Find all possible paths from node 0 to node n - 1.
	
	private static void showResults(int[][] graph) {
		System.out.println("----ShowResults----");
		System.out.println();
		for(int[] arr: graph)
			System.out.println(Arrays.toString(arr));
		System.out.println();

		List<List<Integer>> rs = allPathsSourceTarget(graph);
		for(List<Integer> list: rs)
			System.out.println(list.toString());
		System.out.println();
	}

	// Time: O(V+E), space: O(E)
	public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
		List<List<Integer>> rs = new ArrayList<>();
		List<Integer> holder = new ArrayList<>();
		holder.add(0);
		dfs(graph, rs, 0, graph.length - 1, holder);
		return rs;
	}

	private static void dfs(int[][] graph, List<List<Integer>> rs, int i, int n, List<Integer> holder) {
		if(i == n) { // goal
			rs.add(new ArrayList<>(holder));
			return;
		} else if(graph[i].length == 0) return; // constraints
		else {
			for(int j = 0; j < graph[i].length; ++j) {
				holder.add(graph[i][j]); // make choice
				dfs(graph, rs, graph[i][j], n, holder); // explore
				holder.remove(holder.size() - 1); // undo choice
			}
		}
	}
}

// Constraints:
// - Input graph is a DAG.
// - graph[i][j] != i => There is no self-loop.
// - graph[i][j] = [0, n-1] with n = graph.length
