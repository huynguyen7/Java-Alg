import java.util.Arrays;

// leetcode 494.
public class TargetSum {
    public static void main(String[] args) {
        int[] nums1 = {1,1,1,1,1};
        int s1 = 3;
        showResults(nums1, s1); // expect 5
    }

    private static void showResults(int[] nums, int s) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        int rs = findTargetSumWaysI(nums, s);
        System.out.printf("Target: %d -> %d ways.\n\n", s, rs);
    }

    // DP approach.
    // Top-down DP.
    // l = 2001, n = nums.length
    // Time: O(l*n), space: O(l*n)
    public static int findTargetSumWaysI(int[] nums, int s) {
        if(nums.length == 0) return 0;
        int[][] memo = new int[nums.length][2001];
        for(int[] row: memo)
            Arrays.fill(row, Integer.MIN_VALUE);

        return dfsI(memo, nums, s, 0, 0);
    }

    private static int dfsI(int[][] memo, int[] nums, int sum, int currSum, int i) {
        if(i == nums.length) return currSum == sum ? 1 : 0;
        else if(memo[i][currSum + 1000] != Integer.MIN_VALUE) return memo[i][currSum + 1000];
        else {
            int plus = dfsI(memo, nums, sum, currSum + nums[i], i + 1);
            int minus = dfsI(memo, nums, sum, currSum - nums[i], i + 1);

            memo[i][currSum + 1000] = plus + minus;
            return memo[i][currSum + 1000];
        }
    }

    // Recursive approach.
    // Time: O(2^n), space: O(n)
    public static int findTargetSumWaysII(int[] nums, int s) {
        if(nums.length == 0) return 0;
        return dfsII(nums, s, 0, 0);
    }

    private static int dfsII(int[] nums, int sum, int currSum, int i) {
        if(i == nums.length) return currSum == sum ? 1 : 0;
        else {
            int plus = dfsII(nums, sum, currSum + nums[i], i + 1);
            int minus = dfsII(nums, sum, currSum - nums[i], i + 1);

            return plus + minus;
        }
    }
}

/*
    Constraints:
    - Sum of elements will not exceed 1000
    - Output answer is guaranteed to fit int32.
 */
