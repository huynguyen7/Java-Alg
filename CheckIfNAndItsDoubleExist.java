import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

// leetcode 1346.
public class CheckIfNAndItsDoubleExist {
	public static void main(String[] args) {
		int[] nums1 = {10,2,5,3};
		showResults(nums1); // expect true

		int[] nums2 = {7,1,14,11};
		showResults(nums2); // expect true

		int[] nums3 = {3,1,7,11};
		showResults(nums3); // expect false

		int[] nums4 = {8,3,2,6,10};
		showResults(nums4); // expect true
		
		int[] nums5 = {-2,0,10,-19,4,6,-8};
		showResults(nums5); // expect false
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		System.out.printf("Result: %b\n\n", checkIfExistII(nums));
	}

	// better approach
	// Time: O(n), space: O(n)
	public static boolean checkIfExist(int[] arr) {
		Set<Integer> set = new HashSet<>(); // space: O(n) 
		for(int i: arr) { // time: O(n)
			if(set.contains(i * 2) || 
				(set.contains(i / 2) && i % 2 == 0)) // i % 2 == 0 is needed
													// since it make sure to divide evenly.
													// example: 5/2=2 -> not even, but 4/2=2 -> OK.
				return true;
			else set.add(i);
		}

		return false;
	}

	// naive approach
	// Time: O(nlogn), space: O(1)
	public static boolean checkIfExistII(int[] arr) {
		Arrays.sort(arr); // Time: O(nlogn)
		
		boolean hasZero = false;
		for(int i = 0; i < arr.length; ++i) { // Time: O(n)
			if(arr[i] == 0 && !hasZero) hasZero = true;
			else if(arr[i] == 0 && hasZero) return true;
			else if(binarySearch(arr, arr[i] * 2, 0, arr.length - 1)) // Time: O(logn)
				return true;
		}

		return false;
	}

	private static boolean binarySearch(int[] nums, int target, int lo, int hi) {
		if(lo > hi) return false;
		
		int mid = lo + (hi - lo) / 2;
		if(nums[mid] == target) return true;
		else if(nums[mid] < target) return binarySearch(nums, target, mid + 1, hi);
		else  return binarySearch(nums, target, lo, mid - 1);
	}
}
