import java.util.Arrays;

// leetcode 1800.
public class MaximumAscendingSubarraySum {
    public static void main(String[] args) {
        int[] nums1 = {10,20,30,5,10,50};
        showResults(nums1); // expect 65

        int[] nums2 = {10,20,30,40,50};
        showResults(nums2); // expect 150

        int[] nums3 = {12,17,15,13,10,11,12};
        showResults(nums3); // expect 33

        int[] nums4 = {100,10,1};
        showResults(nums4); // expect 100
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        System.out.printf("Results: %d\n\n", maxAscendingSum(nums));
    }

    // Time: O(n), space: O(1)
    public static int maxAscendingSum(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int maxSum = 0, currSum = 0;
        int lastNum = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length; ++i) {
            if(lastNum >= nums[i]) {
                maxSum = Math.max(maxSum, currSum);
                currSum = nums[i];
            } else currSum += nums[i];
            lastNum = nums[i];
        }

        return Math.max(maxSum, currSum);
    }
}
