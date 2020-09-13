import java.util.Arrays;

public class QuickSort {
	public static void main(String args[]) {
		int[] nums1 = {2,5,1,34,5,8,4,9,6,67};
		showResults(nums1);

		int[] nums2 = {7,4,5,8,9,3};
		showResults(nums2);
	}
	
	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		quickSort(nums);
		System.out.println(Arrays.toString(nums));
	}

	public static void quickSort(int[] nums) {
		divideAndConquer(nums, 0, nums.length - 1);
	}

	private static void divideAndConquer(int[] nums, int lo, int hi) {
		if(lo >= hi) return;
		
		int pivotIndex = partition(nums, lo, hi);
		divideAndConquer(nums, lo, pivotIndex - 1);
		divideAndConquer(nums, pivotIndex + 1, hi);
	}

	private static int partition(int[] nums, int lo, int hi) {
		int i = lo;
		int j = hi;
		int pivotValue = nums[lo];

		while(i < j) {
			while(i < nums.length && nums[i] <= pivotValue) i++;
			while(j >= 0 && nums[j] > pivotValue) j--;
			if(i >= j) break;
			swap(nums, i, j);
		}
		swap(nums, lo, j);
		
		return j;
	}

	private static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}
