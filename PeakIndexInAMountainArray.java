import java.util.Arrays;

//leetcode 852.
public class PeakIndexInAMountainArray {
	public static void main(String args[]) {
		int[] nums1 = {0,1,0};
		showResults(nums1); //expect 1

		int[] nums2 = {0,2,1,0};
		showResults(nums2); //expect 1
		
		int[] nums3 = {0,10,5,2};
		showResults(nums3); //expect 1

		int[] nums4 = {3,4,5,1};
		showResults(nums4); //expect 2
		
		int[] nums5 = {24,69,100,99,79,78,67,36,26,19};
		showResults(nums5); //expect 2
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println("INPUT: " + Arrays.toString(nums));
		System.out.printf("OUTPUT: %d\n\n", peakIndexInMountainArray(nums));
	}

	//time: O(logn), space: O(1); n is nums.length
	public static int peakIndexInMountainArray(int[] nums) {
		return binarySearch(nums, 0, nums.length - 1);
	}

	//dfs
	private static int binarySearch(int[] nums, int lo, int hi) {
		if(lo >= hi) return lo;
		
		int mid = lo + (hi - lo) / 2;
		if(nums[mid] < nums[mid + 1]) return binarySearch(nums, mid + 1, hi);
		else return binarySearch(nums, lo, mid);
	}
}
