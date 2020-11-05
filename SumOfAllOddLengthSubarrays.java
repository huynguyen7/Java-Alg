import java.util.Arrays;

// leetcode 1588.
public class SumOfAllOddLengthSubarrays {
	public static void main(String[] args) {
		int[] nums1 = {1,4,2,5,3};
		showResults(nums1); // expect 58
		
		int[] nums2 = {1,2};
		showResults(nums2); // expect 3

		int[] nums3 = {10,11,12};
		showResults(nums3); // expect 66
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		
		int rs = sumOddLengthSubarrays(nums);
		System.out.printf("Sum = %d\n\n", rs);
	}

	// Time: O(n^2), space: O(1)
	public static int sumOddLengthSubarrays(int[] nums) {
		for(int i = 1; i < nums.length; ++i)
			nums[i] += nums[i - 1];

		int sum = 0;
		for(int i = 0; i < nums.length; ++i) {
			for(int j = i; j < nums.length; j += 2) {
				if(i != 0) sum += (nums[j] - nums[i - 1]);
				else sum += nums[j];
			}
		}

		return sum;
	}
}
