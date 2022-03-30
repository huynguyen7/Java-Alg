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
		int rs = minCostClimbingStairs(cost);
		System.out.printf("Min cost: %d\n\n", rs);
	}

	// DP approach.
	// bottom-up DP.
	// Time: O(n), space: O(1)
	public static int minCostClimbingStairs(int[] cost) {
		if(cost == null || cost.length == 0) return 0;
		
		int n = cost.length;
		for(int i = 2; i < n; ++i)
			cost[i] += Math.min(cost[i - 1], cost[i - 2]);

		return Math.min(cost[n - 1], cost[n - 2]);
	}

    // DP approach, top-down.
    // Time: O(n), space: O(n) 
    public int minCostClimbingStairsI(int[] cost) {
        final int n = cost.length;
        int[] mem = new int[n];
        Arrays.fill(mem, -1);
        
        return Math.min(dfsI(cost, mem, 0), dfsI(cost, mem, 1));
    }
    
    private int dfsI(int[] cost, int[] mem, int curr) {
        if(curr >= cost.length) return 0;
        else if(mem[curr] != -1) return mem[curr];
        else {
            int rs = Math.min(dfsI(cost, mem, curr+1), dfsI(cost, mem, curr+2)) + cost[curr];
            mem[curr] = rs;
            return mem[curr];
        }
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
