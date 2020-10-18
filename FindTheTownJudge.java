import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

// leetcode 997
public class FindTheTownJudge {
	public static void main(String[] args) {
		int n1 = 2;
		int[][] trust1 = {{1,2}};
		showResults(n1, trust1); // expect 2

		int n2 = 3;
		int[][] trust2 = {{1,3},{2,3}};
		showResults(n2, trust2); // expect 3

		int n3 = 3;
		int[][] trust3 = {{1,3},{2,3},{3,1}};
		showResults(n3, trust3); // expect -1

		int n4 = 3;
		int[][] trust4 = {{1,2},{2,3}};
		showResults(n4, trust4); // expect -1

		int n5 = 4;
		int[][] trust5 = {{1,3},{1,4},{2,3},{2,4},{4,3}};
		showResults(n5, trust5); // expect 3
	}

	private static void showResults(int n, int[][] trust) {
		System.out.println("----ShowResults----");
		for(int[] arr: trust)
			System.out.print(Arrays.toString(arr) + " ");
		System.out.println();
		
		System.out.printf("N = %d\n", n);
		int rs = findJudge(n , trust);
		System.out.printf("Result: %d\n\n", rs);
	}
	
	// NOTES: 
	// Return the town judge's label if exists, else return -1.
	// n people labelled from 1 to n.

	// E * V = trust.length * trust[0].length
	// Time: O(E*V), space: O(E*V)
	public static int findJudge(int n, int[][] trust) {
		if(trust.length == 0 && n == 1) return n;
		else if(trust.length == 0 && n != 1) return -1;

		Map<Integer, Set<Integer>> trustGraph = buildGraph(trust);
		
		for(Map.Entry<Integer, Set<Integer>> entry: trustGraph.entrySet()) {
			if(entry.getValue().size() == n - 1) {
				boolean isValidTownJudge = true;
				for(int i: entry.getValue()) {
					Set<Integer> edge = trustGraph.get(i);
					if(edge != null && edge.contains(entry.getKey())) {
						isValidTownJudge = false;
						break;
					}
				}
				if(isValidTownJudge) return entry.getKey();
			}
		}

		return -1;
	}
	
	private static Map<Integer, Set<Integer>> buildGraph(int[][] trust) {
		Map<Integer, Set<Integer>> graph = new HashMap<>();
		for(int i = 0; i < trust.length; ++i) {
			Set<Integer> edge = graph.get(trust[i][1]);
			if(edge == null) {
				edge = new HashSet<>();
				graph.put(trust[i][1], edge);
			}
			edge.add(trust[i][0]);
		}

		return graph;
	}
}

// Constraints:
// trust[i].length = 2
// trust[i][0] != trust[i][1]
