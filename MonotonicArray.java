import java.util.Arrays;

//leetcode 896.
public class MonotonicArray {
	public static void main(String args[]) {
		int[] nums1 = {1,2,2,3}; //expect true
		int[] nums2 = {6,5,4,4}; //expect true
		int[] nums3 = {1,3,2}; //false
		int[] nums4 = {1,2,4,5}; //expect true
		int[] nums5 = {1,1,1}; //expect true
	
		showResults(nums1);
		showResults(nums2);
		showResults(nums3);
		showResults(nums4);
		showResults(nums5);
	}
	
	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		System.out.printf("Array is monotonic: %b\n\n", isMonotonic(nums));
	}

	public static boolean isMonotonic(int[] nums) {
		if(nums.length == 0 || nums == null) return false;
		
		boolean firstEncounter = true, isIncreasing = false, isDecreasing = false;
		for(int i = 0; i < nums.length - 1; ++i) {
			if(nums[i] < nums[i+1] && !isDecreasing) isIncreasing = true;
			else if(nums[i] < nums[i+1] && isDecreasing) return false; 
			else if(nums[i] > nums[i+1] && !isIncreasing) isDecreasing = true;
			else if(nums[i] > nums[i+1] && isIncreasing) return false;
		}

		return true;
	}
}
