import java.util.Arrays;

// element-prog 17.12
public class LongestNondecreasingSubsequence {
	public static void main(String[] args) {
		int[] nums1 = {-1,3,4,5,2,2,2,2};
		showResults(nums1); // expect 5

		int[] nums2 = {1,1};
		showResults(nums2); // expect 2
		
		int[] nums3 ={0,1,2,1,1,2};
		showResults(nums3); // expect 3
	}
	
	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		
		int rs = lengthOfLNDS(nums);
		System.out.printf("Length of LNDS: %d\n\n", rs);
	}

	// best approach
	// DP approach.
	// bottom-up DP.
	// Time: O(n^2), space: O(n)
	public static int lengthOfLNDS(int[] nums) {
		if(nums.length <= 1) return nums.length;

		int[] maxLengthSubsequences = new int[nums.length];
		Arrays.fill(maxLengthSubsequences, 1);

		for(int j = 1; j < nums.length; ++j) {
			for(int i = 0; i < j; ++i) {
				if(nums[j] <= nums[i])
					maxLengthSubsequences[j] = Math.max(maxLengthSubsequences[j], maxLengthSubsequences[i] + 1);
			}
		}

		int maxLength = 1;
		for(int i = 0; i < maxLengthSubsequences.length; ++i)
			maxLength = Math.max(maxLength, maxLengthSubsequences[i]);

		return maxLength;
	}
	
	// DP approach.
	// top-down DP.
	// Time: O(n^2), space: O(n)
	public static int lengthOfLNDSI(int[] nums) {
		if(nums.length <= 1) return nums.length;
		
		int maxLength = 1;
		int[] maxLengthSubsequences = new int[nums.length];
		
		for(int i = 0; i < nums.length; ++i) {
			int currMaxLength = dfsI(nums, maxLengthSubsequences, i);
			maxLength = Math.max(maxLength, currMaxLength);
		}
		
		return maxLength;
	}

	private static int dfsI(int[] nums, int[] maxLengthSubsequences, int currIndex) {
		if(currIndex >= nums.length) return 0;
		
		int maxLengthAtCurrIndex = 1;
		if(maxLengthSubsequences[currIndex] == 0) { // currIndex has not been processed
			for(int i = currIndex + 1; i < nums.length; ++i) {
				if(nums[i] <= nums[currIndex]) {
					int currMaxLength = dfsI(nums, maxLengthSubsequences, i) + 1;
					maxLengthAtCurrIndex = Math.max(maxLengthAtCurrIndex, currMaxLength);
				}
			}

			maxLengthSubsequences[currIndex] = maxLengthAtCurrIndex;
		}

		return maxLengthSubsequences[currIndex];
	}

	// Recursive approach.
	// Time: O(2^n), space: O(2^n)
	public static int lengthOfLNDSII(int[] nums) {
		if(nums.length <= 1) return nums.length;
		
		int maxLength = 1;
		for(int i = 0; i < nums.length; ++i) {
			int currMaxLength = dfsII(nums, i);
			maxLength = Math.max(maxLength, currMaxLength);
		}

		return maxLength;
	}

	private static int dfsII(int[] nums, int currIndex) {
		if(currIndex >= nums.length) return 0;
		
		int maxLengthAtCurrIndex = 1;
		for(int i = currIndex + 1; i < nums.length; ++i) {
			if(nums[i] <= nums[currIndex]) {
				int currMaxLength = dfsII(nums, i) + 1;
				maxLengthAtCurrIndex = Math.max(maxLengthAtCurrIndex, currMaxLength);
			}
		}

		return maxLengthAtCurrIndex;
	}
}
