import java.util.Arrays;

// leetcode 1913.
public class MaximumProductDifferenceBetweenTwoPairs {
    public static void main(String[] args) {
        int[] nums1 = {5,6,2,7,4};
        showResults(nums1); // expect 34

        int[] nums2 = {4,2,5,9,7,4,8};
        showResults(nums2); // expect 64
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        int rs = maxProductDifference(nums);
        System.out.printf("RESULT: %d\n\n", rs);
    }
    
    // Time: O(nlogn), space: O(1)
    public static int maxProductDifference(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        final int n = nums.length-1;
        return nums[n]*nums[n-1]- nums[0]*nums[1];
    }
}
