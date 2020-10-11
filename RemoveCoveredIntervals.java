import java.util.Arrays;

// leetcode 1288.
public class RemoveCoveredIntervals {
	public static void main(String[] args) {
		int[][] intervals1 = {
								{1,4},
								{3,6},
								{2,8}
							};
		showResults(intervals1); // expect 2
		
		int[][] intervals2 = {
								{1,4},
								{2,3}
							};
		showResults(intervals2); // expect 1

		int[][] intervals3 = {
								{0,10},
								{5,12}
							};
		showResults(intervals3); // expect 2
		
		int[][] intervals4 = {
								{3,10},
								{4,10},
								{5,11}
							};
		showResults(intervals4); // expect 2

		int[][] intervals5 = {
								{1,2},
								{1,4},
								{3,4}
							};
		showResults(intervals5); // expect 1

		int[][] intervals6 = {
								{3,10},
								{4,11},
								{5,10}
							};
		showResults(intervals6); // expect 2

		int[][] intervals7 = {
								{1,2},
								{1,3},
								{1,4}
							};
		showResults(intervals7); // expect 1

		int[][] intervals8 = {
								{66672,75156},
								{59890,65654},
								{92950,95965},
								{9103,31953},
								{54869,69855},
								{33272,92693},
								{52631,65356},
								{43332,89722},
								{4218,57729},
								{20993,92876}
							};
		showResults(intervals8); // expect 3
	}

	private static void showResults(int[][] intervals) {
		System.out.println("----ShowResults----");
		for(int[] interval: intervals)
			System.out.println(Arrays.toString(interval));
		System.out.println();

		int rs = removeCoveredIntervals(intervals);
		System.out.printf("Total intervals left: %d\n\n", rs);
	}

	// Interval form: [a,b)

	// Greedy approach.
	// n = intervals.length
	// Time: O(nlogn), space: O(1)
	public static int removeCoveredIntervals(int[][] intervals) {
		if(intervals.length == 1) return 1;
		Arrays.sort(intervals,
					(a, b) -> {
						if(a[0] == b[0]) return Integer.compare(a[1], b[1]);
						else return Integer.compare(a[0], b[0]);
					});

		int currMin = intervals[0][0];
		int currMax = intervals[0][1];
		int rs = intervals.length;
		for(int i = 1; i < intervals.length; ++i) { // time: O(n)
			if((currMin >= intervals[i][0] && currMax <= intervals[i][1]) || 
				(currMin <= intervals[i][0] && currMax >= intervals[i][1]))
				rs--;

			currMin = Math.min(currMin, intervals[i][0]);
			currMax = Math.max(currMax, intervals[i][1]);
		}

		return rs;
	}
}
