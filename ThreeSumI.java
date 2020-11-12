import java.util.*;

// leetcode 15.
public class ThreeSumI {
	public static void main(String[] args) {
		int[] nums1 = {-1,0,1,2,-1,-4};
		showResults(nums1); // expect {{-1,-1,2},{-1,0,1}}
		
		int[] nums2 = {};
		showResults(nums2); // expect {}

		int[] nums3 = {0};
		showResults(nums3); // expect {}

		int[] nums4 = {0,0,0};
		showResults(nums4); // expect {{0,0,0}}
		
		int[] nums5 = {-2,0,1,1,2};
		showResults(nums5); // expect {{-2,0,2},{-2,1,1}}

		int[] nums6 = {-3,3,4,-3,1,2};
		showResults(nums6); // expect {{-3,1,2}}
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		System.out.println("\nOUTPUT:");
		List<List<Integer>> rs = threeSum(nums);
		for(List<Integer> list: rs)
			System.out.println(list.toString());
		System.out.println();
	}

	// GOOD EXPLANATION:
	// https://www.youtube.com/watch?v=jzZsG8n2R9A

	// Constraints:
	// - Cannot use the same element twice.
	// - Input array is not sorted.
	// - sum should equals to zero and their must be three values a, b, c.

	// Time: O(n*(n + logn)), space: O(n^2)
	public static List<List<Integer>> threeSum(int[] nums) {
		if(nums == null || nums.length < 3)
			return Collections.emptyList();

		Arrays.sort(nums); // time: O(nlogn)
		List<List<Integer>> rs = new ArrayList<>(); // use set to check dups.

		for(int i = 0; i < nums.length; ++i) {
			if(i > 0 && nums[i] == nums[i - 1]) continue; // prevent dups
			
			int left = i + 1, right = nums.length - 1;
			while(left < right) {
				int sum = nums[i] + nums[left] + nums[right];
				if(sum < 0) left++;
				else if(sum > 0) right--;
				else {
					List<Integer> holder = new ArrayList<>();
					holder.add(nums[i]);
					holder.add(nums[left]);
					holder.add(nums[right]);

					rs.add(holder);
					left++;
					
					while(nums[left] == nums[left - 1] && left < right)
						left++;
				}
			}
		}

		return rs;
	}
}
