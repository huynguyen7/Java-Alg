import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

// leetcode 207.
public class CourseSchedule {
	public static void main(String[] args) {
		int numCourses1 = 2;
		int[][] prerequisites1 = {
			{1,0}
		};
		showResults(numCourses1, prerequisites1); // expect true

		int numCourses2 = 2;
		int[][] prerequisites2 = {
			{1,0},
			{0,1}
		};
		showResults(numCourses2, prerequisites2); // expect false

		int numCourses3 = 4;
		int[][] prerequisites3 = {
			{2,3},
			{1,3},
			{1,2},
			{0,1}
		};
		showResults(numCourses3, prerequisites3); // expect true 
	}

	private static void showResults(int numCourses, int[][] prerequisites) {
		System.out.println("----ShowResults----");
		for(int[] edge: prerequisites)
			System.out.println(Arrays.toString(edge));
		System.out.println();

		System.out.printf("NUM_COURSES: %d\n", numCourses);
		boolean rs = canFinish(numCourses, prerequisites);
		System.out.printf("Can finish: %b\n\n", rs);
	}

	// TRICK: Use topological sort.

	// Time: O(E+V), space: O(E+V)
	public static boolean canFinish(int numCourses, int[][] prerequisites) {
		if(prerequisites == null || prerequisites.length == 0) return true;
		
		// Changing edges representation to adjacency matrices representation.
		Map<Integer, List<Integer>> graph = new HashMap<>();

		for(int i = 0; i < numCourses; ++i)
			graph.put(i, new ArrayList<>());

		for(int[] edge: prerequisites) {
			int src = edge[0];
			int dest = edge[1];
			graph.get(src).add(dest);
		}
		
		boolean[] visited = new boolean[numCourses];
		for(int i = 0; i < numCourses; ++i) {
			boolean hasCyle = dfs(graph, visited, i); // start at i
			if(hasCyle) return false;
		}

		return true;
	}

	private static boolean dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int v) {
		if(visited[v]) return true; // graph has cycle.
		
		visited[v] = true; // MAKE CHOICE
		for(int w: graph.get(v)) {
			if(dfs(graph, visited, w)) // EXPLORE, if true, found cycle
				return true;
		}

		// If found nothing, UNDO CHOICE.
		visited[v] = false;
		return false;
	}
}

/*
Constraints:
	- Directed Graph
	- Start at node 0 -> numCourses - 1.
	- The input is a graph presented by a list of edges (not adjacency matrices).
	- No duplicate edges.
**/
