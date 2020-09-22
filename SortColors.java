import java.util.Arrays;

// leetcode 75.
public class SortColors {
	public static void main(String[] args) {
		int[] nums1 = {2,0,2,1,1,0};
		showResults(nums1); // expect {0,0,1,1,2,2}
		
		int[] nums2 = {2,0,1};
		showResults(nums2); // expect {0,1,2}

		int[] nums3 = {0};
		showResults(nums3); // expect {0}
		
		int[] nums4 = {1,2,0};
		showResults(nums4); // expect {0,1,2}
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		sortColors(nums);
		System.out.println(Arrays.toString(nums));
	}

	// Time: O(n), space: O(1)
	public static void sortColors(int[] nums) {
		int left = 0, right = nums.length - 1;
		int i = 0;
		while(i < nums.length && left < right) {
			if(nums[i] == 0 && i > left) swap(nums, left++, i); // left < i < right is NECESSARY
																// to limit nums[i] == 1 range
			else if(nums[i] == 2 && i < right) swap(nums, right--, i);
			else i++;
		}
	}

	private static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}

// CONSTRAINTS:
// nums[i] = 0,1,2 only
