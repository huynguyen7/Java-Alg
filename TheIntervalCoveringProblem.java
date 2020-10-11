import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

// element-prog 18.3
public class TheIntervalCoveringProblem {
	public static void main(String[] args) {
		int[][] intervals1 = {
								{1,2},
								{2,3},
								{3,4},
								{2,3},
								{3,4},
								{4,5}
							};
		showResults(intervals1); // expect [2,4]
								// since 2 -> 4 can reach all other intervals.

		int[][] intervals2 = {
								{1,5},
								{2,3},
								{1,2},
							};
		showResults(intervals2); // expect [1,5]
	}

	private static void showResults(int[][] intervals) {
		System.out.println("----ShowResults----");
		
		List<List<Integer>> input = new ArrayList<>();
		for(int[] arr : intervals) {
			List<Integer> tmp = new ArrayList<>();
			for(int i: arr)
				tmp.add(i);
			input.add(tmp);
		}
		
		for(List<Integer> l : input)
			System.out.println(l.toString());

		System.out.println("\nResults:");

		List<Integer> rs = findMinimumVisits(input);
		System.out.println(rs.toString() + "\n");
	}

	// Return the interval which
	// can reach all other intervals.

	// Greedy approach.
	// n = intervals.size()
	// Time: O(nlogn), space: O(1)
	public static List<Integer> findMinimumVisits(List<List<Integer>> intervals) {
		List<Integer> rs = new ArrayList<>();
		if(intervals.size() == 0) return rs;
		if(intervals.size() == 1) return intervals.get(0);

		Collections.sort(intervals,
						(i1, i2) -> Integer.compare(i1.get(0), i2.get(0)));
		rs.add(intervals.get(0).get(1)); // init dummy values.
		rs.add(intervals.get(0).get(1)); // init dummy values.

		for(int i = 1; i < intervals.size(); ++i) {
			rs.set(0, Math.min(rs.get(0), intervals.get(i).get(0)));
			rs.set(1, Math.max(rs.get(1), intervals.get(i).get(0)));
		}

		return rs;
	}
}
