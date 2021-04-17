import java.util.Arrays;

// leetcode 1827.
public class MinimumOperationsToMakeTheArrayIncreasing {
    public static void main(String[] args) {
        int[] nums1 = {1,1,1};
        showResults(nums1); // expect 3

        int[] nums2 = {1,5,2,4,1};
        showResults(nums2); // expect 14

        int[] nums3 = {8};
        showResults(nums3); // expect 0
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        int rs = minOperations(nums);
        System.out.printf("MIN OPS = %d\n\n", rs);
    }

    // Time: O(n), space:O(1)
    public static int minOperations(int[] nums) {
        if(nums == null || nums.length <= 1) return 0;

        int numOps = 0;
        int currMax = nums[0];
        for(int i = 1; i < nums.length; ++i) {
            if(nums[i] <= currMax) {
                numOps += currMax+1-nums[i];
                currMax = nums[i] > currMax ? nums[i] : currMax+1;
            } else currMax = Math.max(currMax, nums[i]);
        }

        return numOps;
    }
}
