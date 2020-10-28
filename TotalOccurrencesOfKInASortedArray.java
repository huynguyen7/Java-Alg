import java.util.Arrays;

// This problem is similar to Leetcode 34.
public class TotalOccurrencesOfKInASortedArray {
	public static void main(String[] args) {
		int[] nums1 = {1,1,1,2,3,4};
		showResults(nums1, 1); // expect 3

		int[] nums2 = {1};
		showResults(nums2, 1); // expect 1

		int[] nums3 = {1,2,3};
		showResults(nums3, 2); // expect 1

		int[] nums4 = {2,2,4,5};
		showResults(nums4, 5); // expect 1

		int[] nums5 = {1,2,2,2,2,4};
		showResults(nums5, 2); // expect 4

		int[] nums6 = {4,5,9};
		showResults(nums6, 7); // expect 0
		showResults(nums6, 3); // expect 0

		int[] nums7 = {1};
		showResults(nums7, 2); // expect 0
	}

	private static void showResults(int[] nums, int k) {
		System.out.println("----ShowResults----");
		Arrays.sort(nums);
		System.out.println(Arrays.toString(nums));
		

		int rs = findTotalOccurrencesOfKII(nums, k);
		System.out.printf("Total occurrences of %d: %d\n\n", k, rs);
	}

	// GOOD EXPLANATION:
	// https://www.youtube.com/watch?v=RlXtTF34nnE

	// Time: O(logn), space: O(logn)
	public static int findTotalOccurrencesOfKII(int[] nums, int k) {
		if(nums == null || nums.length == 0) return 0;
		
		int lowerBound = binarySearchLowerBound(nums, k, 0, nums.length - 1);
		int upperBound = binarySearchUpperBound(nums, k, 0, nums.length - 1);

		if(lowerBound > upperBound) return 0;
		if(nums[lowerBound] != k && nums[upperBound] != k) return 0;
		
		return upperBound - lowerBound + 1;
	}

	// Time: O(logn), space: O(logn)
	private static int binarySearchLowerBound(int[] nums, int k, int lo, int hi) {
		if(lo > hi) return lo;

		int mid = lo + (hi - lo) / 2;
		if(nums[mid] >= k) return binarySearchLowerBound(nums, k, lo, mid - 1);
		else return binarySearchLowerBound(nums, k, mid + 1, hi);
	}

	// Time: O(logn), space: O(logn)
	private static int binarySearchUpperBound(int[] nums, int k, int lo, int hi) {
		if(lo > hi) return hi;
		
		int mid = lo + (hi - lo) / 2;
		if(nums[mid] <= k) return binarySearchUpperBound(nums, k, mid + 1, hi);
		else return binarySearchUpperBound(nums, k, lo, mid - 1);
	}
}
