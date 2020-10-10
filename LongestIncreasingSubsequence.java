import java.util.Arrays;

// leetcode 300.
public class LongestIncreasingSubsequence {
	public static void main(String[] args) {
		int[] nums1 = {10,9,2,5,3,7,101,18};
		showResults(nums1); // expect 4
							// {2,3,7,101}

		int[] nums2 = {5,1,3,2,8,0};
		showResults(nums2); // expect 3
							// {1,3,8} or {1,2,8}

		int[] nums3 = {10,9,2,5,3,4};
		showResults(nums3); // expect 3
							// {2,3,4}
							// GOOD EXAMPLE TO DRAW RECURSION TREE.

		int[] nums4= {};
		showResults(nums4); // expect 0

		int[] nums5 = {1,2,3};
		showResults(nums5); // expect 3

		int[] nums6 = {1,3,6,7,9,4,10,5,6};
		showResults(nums6); // expect 6
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		int rs = lengthOfLIS(nums);
		System.out.printf("Length of LIS: %d\n\n", rs);
	}

	// best approach
	// DP approach.
	// bottom-up DP.
	// Time: O(n^2), space: O(n)
	public static int lengthOfLIS(int[] nums) {
		if(nums.length <= 1) return nums.length;
		
		int[] maxLengthSubsequences = new int[nums.length];
		Arrays.fill(maxLengthSubsequences, 1);
		
		for(int j = 1; j < nums.length; ++j) {
			for(int i = 0; i < j; ++i) {
				if(nums[j] > nums[i])
					maxLengthSubsequences[j] = Math.max(maxLengthSubsequences[j], maxLengthSubsequences[i] + 1);
			}
		}
		
		int maxLength = 0;
		for(int i = 0; i < maxLengthSubsequences.length; ++i)
			maxLength = Math.max(maxLength, maxLengthSubsequences[i]);
		
		return maxLength;
	}

	// DP approach.
	// top-down DP.
	// Time: O(n^2), space: O(n) => This costs more space than bottom-up (for stack calls)
	public static int lengthOfLISI(int[] nums) {
		int[] maxLengthSubsequences = new int[nums.length];

		int maxLength = 0;
		for(int i = 0; i < nums.length; ++i) {
			int currMaxLength = dfsI(nums, maxLengthSubsequences, i) + 1;
			maxLength = Math.max(maxLength, currMaxLength);
		}
		
		return maxLength;
	}

	private static int dfsI(int[] nums, int[] maxLengthSubsequences, int currIndex) {
		if(currIndex >= nums.length) return 0;
		
		int maxLengthAtCurrIndex = 0;
		if(maxLengthSubsequences[currIndex] == 0) { // currIndex has not been processed.
			for(int i = currIndex; i < nums.length; ++i) {
				if(nums[i] > nums[currIndex]) {
					int currMaxLength = dfsI(nums, maxLengthSubsequences, i) + 1;
					maxLengthAtCurrIndex = Math.max(maxLengthAtCurrIndex, currMaxLength);
				}
			}

			maxLengthSubsequences[currIndex] = maxLengthAtCurrIndex;
		}

		return maxLengthSubsequences[currIndex];
	}

	// Recursive approach.
	// Time: O(n^2), space: O(n^2)
	public static int lengthOfLISII(int[] nums) {
		int maxLength = 0;
		for(int i = 0; i < nums.length; ++i) {
			int currMaxLength = dfsII(nums, i) + 1;
			maxLength = Math.max(maxLength, currMaxLength);
		}
		
		return maxLength;
	}

	private static int dfsII(int[] nums, int currIndex) {
		if(currIndex >= nums.length) return 0;

		int maxLengthAtCurrIndex = 0;		
		for(int i = currIndex; i < nums.length; ++i) {
			if(nums[i] > nums[currIndex]) {
				int currMaxLength = dfsII(nums, i) + 1;
				maxLengthAtCurrIndex = Math.max(maxLengthAtCurrIndex, currMaxLength);
			}
		}

		return maxLengthAtCurrIndex;
	}
}

// Constraints:
// - unsorted array.
