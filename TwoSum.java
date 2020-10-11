import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

// leetcode 1.
public class TwoSum {
	public static void main(String[] args) {
		int[] nums1 = {2,7,11,15};
		showResults(nums1, 9); // expect {0,1}

		int[] nums2 = {3,2,4};
		showResults(nums2, 6); // expect {1,2}

		int[] nums3 = {3,3};
		showResults(nums3, 6); // expect {0,1}
	}

	private static void showResults(int[] nums, int target) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		System.out.printf("Target: %d -> ", target);

		int[] rs = twoSum(nums, target);
		if(rs != null) System.out.print(Arrays.toString(rs) + "\n\n");
		else System.out.println("\n");
	}

	// RULES:
	// Do not use the same element twice.
	
	// Returns the indexes of found target sum in the array.

	// Best approach using memoization.
	// Time: O(n), space: O(n)
	public static int[] twoSumI(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>(); // <value-index> pair
		for(int i = 0; i < nums.length; ++i)
			map.put(nums[i], i);
		
		for(int i = 0; i < nums.length; ++i) {
			int complement = target - nums[i];
			if(map.containsKey(complement) && map.get(complement) != i)
				return new int[] {i, map.get(complement)};
		}

		return null;
	}

	// Brute-force approach
	// Time: O(n^2), space: O(1)
	public static int[] twoSumII(int[] nums, int target) {
		int[] rs = new int[2];
		for(int i = 0; i < nums.length - 1; ++i) {
			int nextTarget = target - nums[i];
			rs[0] = i;
			for(int j = i + 1; j < nums.length; ++j) {
				if(j == i) continue; // do not use the same element twice.
				if(nums[j] == nextTarget) {
					rs[1] = j;
					return rs;
				}
			}
		}

		return null;
	}
}

// Constraints:
// It is guaranteed that the input will have exactly one solution.
// 2 <= nums.length
