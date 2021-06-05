import java.util.Arrays;

// leetcode 1877.
public class MinimizeMaximumPairSumInArray {
    public static void main(String[] args) {
        int[] nums1 = {3,5,2,3};
        showResults(nums1); // expect 7
        
        int[] nums2 = {3,5,4,2,4,6};
        showResults(nums2); // expect 8
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s\nOUTPUT: %d\n\n", Arrays.toString(nums), minPairSum(nums));
    }

    // Time: O(nlogn), space: O(1)
    public static int minPairSum(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        Arrays.sort(nums); // time: O(nlogn)
        final int n = nums.length;
        int rs = Integer.MIN_VALUE;
        for(int i = 0; i < n/2; ++i)
            rs = Math.max(rs, nums[i]+nums[n-i-1]);

        return rs;
    }
}
