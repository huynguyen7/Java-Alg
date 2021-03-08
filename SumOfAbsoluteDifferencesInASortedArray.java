import java.util.Arrays;

// leetcode 1685.
public class SumOfAbsoluteDifferencesInASortedArray {
    public static void main(String[] args) {
        int[] nums1 = {2,3,5};
        showResutls(nums1); // expect [4,3,5]
        int[] nums2 = {1,4,6,8,10};
        showResutls(nums2); // expect [24,15,13,15,21]
    }

    private static void showResutls(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        int[] rs = getSumAbsoluteDifferences(nums);
        System.out.println(Arrays.toString(rs) + "\n");
    }

    // Time: O(n), space: O(1)
    public static int[] getSumAbsoluteDifferences(int[] nums) {
        if(nums == null || nums.length == 0) return new int[0];
        
        final int n = nums.length;
        int sum = 0;
        for(int i = 0; i < n; ++i)
            sum += nums[i];

        int currSum = 0;
        for(int i = 0; i < n; ++i) {
            int prefix = i*nums[i] - currSum;
            int suffix = (sum-currSum-nums[i]) - (n-i-1)*nums[i];
            currSum += nums[i];
            nums[i] = prefix+suffix;
        }

        return nums;
    }
}
