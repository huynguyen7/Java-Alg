import java.util.Arrays;

// leetcode 268, element-prog 12.10
public class MissingNumber {
	public static void main(String[] args) {
		int[] nums1 = {3,0,1};
		showResults(nums1); // expect 2
		
		int[] nums2 = {9,6,4,2,3,5,7,0,1};
		showResults(nums2); // expect 8
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		int rs = missingNumber(nums);
		System.out.printf("Missing element: %d\n\n", rs);
	}
	
	// time: O(n), space: O(1)
	public static int missingNumber(int[] nums) {
		int sum = 0;
		
		for(int i = 0; i < nums.length; ++i)
			sum += nums[i];
		

		int expectedSum = nums.length * (nums.length + 1) / 2;
		return expectedSum - sum;
	}
}

// Constraints:
// Array has element from 0 -> n inclusively
// All elements are UNIQUE
