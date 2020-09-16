import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] nums1 = {1,2,3,4,5,6,7,8,9};
		System.out.println(Arrays.toString(nums1));

    	boolean recursive = binarySearch1(nums1, Integer.parseInt(args[0]), 0, nums1.length - 1);
		System.out.printf("From Recursive: %b\n", recursive);		

		boolean iterative = binarySearch2(nums1, Integer.parseInt(args[0]));
		System.out.printf("From iterative: %b\n", iterative);
    }

	//time: O(logn)
    public static boolean binarySearch1(int[] nums, int target, int lo, int hi) {
		if(lo > hi) return false;
		int mid = lo + (hi - lo) / 2;
		if(nums[mid] == target) return true;
		else if(nums[mid] > target) return binarySearch1(nums, target, lo, mid - 1);
		else return binarySearch1(nums, target, mid + 1, hi);
    }

	//time: O(logn)
	public static boolean binarySearch2(int[] nums, int target) {
		int lo = 0, hi = nums.length - 1;
		while(lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if(nums[mid] == target) return true;
			else if(nums[mid] > target) hi = mid - 1;
			else lo = mid + 1;
		}

		return false;
	}
}
