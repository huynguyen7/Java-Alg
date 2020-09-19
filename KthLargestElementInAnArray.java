import java.util.Arrays;

//leetcode 215, element-prog 12.8.
public class KthLargestElementInAnArray {
	public static void main(String args[]) {
		int[] nums1 = {3,2,1,5,6,4};
		int k1 = 2;
		showResults(nums1, k1); //expect 5
		
		int[] nums2 = {3,2,3,1,2,4,5,5,6};
		int k2 = 4;
		showResults(nums2, k2); //expect 4
	}

	private static void showResults(int[] nums, int k) {
		System.out.println("---ShowResults----");
		System.out.println(Arrays.toString(nums));
		System.out.printf("%d'th largest element: %d\n\n", k,
						findKthLargest(nums, k));
	}

	//time: O(nlogn), space: O(1)
	public static int findKthLargest(int[] nums, int k) {
		k--;
		
		int lo = 0, hi = nums.length - 1;
		while(lo < hi) {
			int pivotIndex = partition(nums, lo, hi);
			if(pivotIndex == k) return nums[pivotIndex];
			else if(pivotIndex > k) hi = pivotIndex - 1;
			else if(pivotIndex < k) lo = pivotIndex + 1;
		}

		return nums[k];
	}

	//time: O(logn)
	private static int partition(int[] nums, int lo, int hi) {
		int i = lo;
		int j = hi;
		int pivotValue = nums[lo];
		
		while(i <= j) {
			while(i <= hi  && nums[i] >= pivotValue) i++;
			while(j >= lo && nums[j] < pivotValue) j--;
			if(i > j) break;
			swap(nums, i++, j--);
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
