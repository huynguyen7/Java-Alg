import java.util.Arrays;

// leetcode 198.
public class HouseRobber {
	public static void main(String[] args) {
		int[] nums1 = {1,2,3,1};
		showResults(nums1); // expect 4

		int[] nums2 = {2,7,9,3,1};
		showResults(nums2); // expect 12

		int[] nums3 = {1,2,3,4};
		showResults(nums3); // expect 6
	}
	
	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		int rs = rob(nums);
		System.out.printf("Result: %d\n\n", rs);
	}

	// DP approach
	// Time: O(n), space: O(1)
	public static int rob(int[] nums) {
		// rob1 is to hold the previous house's max sum value.
		// rob2 is to hold the curr house's max sum value.
		int rob1 = 0, rob2 = 0; // init base case
		
		for(int i = 0; i < nums.length; ++i) {
			int tmp = Math.max(nums[i] + rob1, rob2);
			rob1 = rob2;
			rob2 = tmp;
		}

		return rob2;
	}
}
