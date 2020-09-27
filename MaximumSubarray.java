import java.util.Arrays;

// leetcode 53.
public class MaximumSubarray {
	public static void main(String[] args) {
		int[] nums1 = {-2,1,-3,4,-1,2,1,-5,4};
		showResults(nums1); // expect 6
							// [4,-1,2,1] has the largest sum = 6.

		int[] nums2 = {1};
		showResults(nums2); // expect 1

		int[] nums3 = {0};
		showResults(nums3); // expect 0

		int[] nums4 = {-1};
		showResults(nums4); // expect -1

		int[] nums5 = {-2147483647}; // expect -2147483647
		showResults(nums5);
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		
		int rs = maxSubArray(nums);
		System.out.printf("Max Subarray: %d\n\n", rs);
	}

	// Time: O(n), space: O(1)
	public static int maxSubArray(int[] nums) {
		if(nums.length == 0) return 0;

		int maxSum = nums[0];
		for(int i = 1; i < nums.length; ++i) {
			nums[i] = Math.max(nums[i], nums[i] + nums[i - 1]);
			maxSum = Math.max(maxSum, nums[i]);
		}
		System.out.println(Arrays.toString(nums));
		
		return maxSum;
	}
}
