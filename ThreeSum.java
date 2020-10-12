import java.util.*;

// element-prog 18.4
public class ThreeSum {
	public static void main(String[] args) {
		int[] nums1 = {3,3};
		showResults(nums1, 9); // expect true

		int[] nums2 = {11,2,5,7,3};
		showResults(nums2, 21); // expect true

		int[] nums3 = {2,7,9,4};
		showResults(nums3, 6); // expect true

		int[] nums4 = {4,8,3};
		showResults(nums4, 5); // expect false
	}

	// RULES:
	// We can use the same element more than once.

	private static void showResults(int[] nums, int target) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		System.out.printf("Target: %d -> ", target);
		
		boolean rs = threeSum(nums, target);
		System.out.printf("Has three sum: %b\n\n", rs);
	}

	// Greedy approach.
	// Time: O(n^2), space: O(1)
	public static boolean threeSum(int[] nums, int target) {
		Arrays.sort(nums);
		for(int num: nums) { // Time: O(n)
			if(twoSum(nums, target - num)) // Time: O(n)
				return true;
		}

		return false;
	}

	// Time: O(n), space: O(1)
	private static boolean twoSum(int[] nums, int target) {
		int i = 0, j = nums.length - 1;
		while(i <= j) {
			int currSum = nums[i] + nums[j];
			if(currSum == target) return true;
			else if(currSum < target) ++i;
			else --j;
		}

		return false;
	}
}
