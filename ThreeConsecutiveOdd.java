import java.util.Arrays;

//leetcode 1550.
public class ThreeConsecutiveOdd {
	public static void main(String args[]) {
		int[] nums1 = {2,6,4,1}; //expect false
		int[] nums2 = {1,2,34,3,4,5,7,23,12}; //expect true
		int[] nums3 = {1,2,34,3,4,5,7,23,12}; //expect true

		showResults(nums1);
		showResults(nums2);
		showResults(nums3);
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println("INPUT:\t" + Arrays.toString(nums));
		System.out.printf("OUTPUT: %b\n\n", threeConsecutiveOdds(nums));
	}

	//time: O(n), space: O(1); n is nums.length
	public static boolean threeConsecutiveOdds(int[] nums) {
		int count = 0;
		for(int i = 0; i < nums.length; ++i) {
			if(nums[i] % 2 != 0) {
				if(++count == 3) return true;
			} else count = 0;
		}
		return false;
	}
}
