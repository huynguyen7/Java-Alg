import java.util.Arrays;

// leetcode 643.
public class MaximumAverageSubarrayI {
	public static void main(String[] args) {
		int[] nums1 = {1,12,-5,-6,50,3};
		int k1 = 4;
		showResults(nums1 , k1); // expect 12.75
		
		int[] nums2 = {5};
		int k2 = 1;
		showResults(nums2, k2); // expect 5
		
		int[] nums3 = {4,0,4,3,3};
		int k3 = 5;
		showResults(nums3, k3); // expect 2.8
	}

	private static void showResults(int[] nums, int k) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		System.out.printf("k = %d\nResults: %f\n\n", k,
							findMaxAverage(nums, k));
	}

	// k is length of subarrays,
	// n is length nums.
	// Time: O(n*k), space: O(1)
	public static double findMaxAverage(int[] nums, int k) {
		double sum = 0;
		int i = 0;
		
		for( ; i < k; ++i)
			sum += nums[i];
		
		double maxSum = sum;
		for( ; i < nums.length; ++i) {
			sum += nums[i] - nums[i - k]; // remove the last element,
										// added the newest element
			maxSum = Math.max(maxSum, sum);
		}

		return maxSum / k;
	}
}
