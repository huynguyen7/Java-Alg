import java.util.Arrays;

// leetcode 33
public class SearchInRotatedSortedArray {
	public static void main(String[] args) {
		int[] nums1 = {4,5,6,7,0,1,2};
		int target1 = 0;
		showResults(nums1, target1); // expect 4
		
		int[] nums2 = {4,5,6,7,0,1,2};
		int target2 = 3;
		showResults(nums2, target2); // expect -1
		showResults(nums2, 2); // expect 6

		int[] nums3 = {1};
		int target3 = 0;
		showResults(nums3, target3); // expect -1

		int[] nums4 = {5,6,0,1,2,3};
		showResults(nums4, 5); // expect 0
	}

	private static void showResults(int[] nums, int target) {
		System.out.println("----ShowResults----");
		int[] index = new int[nums.length];
		for(int i = 0; i < index.length; ++i)
			index[i] = i;
		System.out.println(Arrays.toString(index));
		System.out.println(Arrays.toString(nums));
		
		int rs = search(nums, target);
		System.out.printf("Found %d at index: %d\n\n", target, rs);
	}

	// time: O(logn), space: O(1)
	public static int search(int[] nums, int target) {
		if(nums.length == 0) return -1;
		return searchRotated(nums, target, 0, nums.length - 1);
	}

	private static int searchRotated(int[] nums, int target, int lo, int hi) {
		if(lo > hi) return -1;
		
		int mid = lo + (hi - lo) / 2;
		if(nums[mid] == target) return mid;
		
		// Decide which range to search
		if(nums[lo] < nums[mid]) { // check if left side is normally sorted 
			if(target >= nums[lo] && target < nums[mid])
				return searchRotated(nums, target, lo, mid - 1);
			else return searchRotated(nums, target, mid + 1, hi);
		} else if(nums[mid] < nums[hi]) { // check if right side is normally sorted
			if(target > nums[mid] && target <= nums[hi])
				return searchRotated(nums, target, mid + 1, hi);
			else return searchRotated(nums, target, lo, mid - 1);
		} else if(lo == mid && mid != hi) 
			return searchRotated(nums, target, mid + 1, hi);

		return -1;
	}
}

// constraints:
// All elements are UNIQUE 
