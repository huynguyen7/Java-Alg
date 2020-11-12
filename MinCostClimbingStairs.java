import java.util.Arrays;

// leetcode 746.
public class MinCostClimbingStairs {
	public static void main(String[] args) {
		int[] cost1 = {10,15,20};
		showResults(cost1); // expect 15

		int[] cost2 = {1,100,1,1,1,100,1,1,100,1};
		showResults(cost2); // expect 6
	}

	private static void showResults(int[] cost) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(cost));
		int rs = minCostClimbingStairsI(cost);
		System.out.printf("Min cost: %d\n\n", rs);
	}

	// DP approach.
	// bottom-up DP.
	// Time: O(n), space: O(1)
	public static int minCostClimbingStairsI(int[] cost) {
		if(cost == null || cost.length == 0) return 0;
		
		int n = cost.length;
		for(int i = 2; i < n; ++i)
			cost[i] += Math.min(cost[i - 1], cost[i - 2]);

		return Math.min(cost[n - 1], cost[n - 2]);
	}

	// Recursive approach.
	// Time: O(2^n), space: O(2^n)
	public static int minCostClimbingStairsII(int[] cost) {
		if(cost == null || cost.length == 0) return 0;

		return Math.min(dfsII(cost, 0), dfsII(cost, 1));
	}

	private static int dfsII(int[] cost, int startIndex) {
		if(startIndex >= cost.length) return 0;
		else {
			int minNumStepsWhenAdvancesOne = dfsII(cost, startIndex + 1);
			int minNumStepsWhenAdvancesTwo = dfsII(cost, startIndex + 2);

			return Math.min(minNumStepsWhenAdvancesOne, minNumStepsWhenAdvancesTwo) + cost[startIndex];
		}
	}
}

// Constraints:
// - cost.length >= 2
// - cost[i] >= 0 
