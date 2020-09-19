import java.util.Arrays;

// leetcode 153, element-prog 12.3
public class FindMinimumInRotatedSortedArray {
	public static void main(String[] args) {
		int[] nums1 = {378,478,550,631,103,203,220,234,279,368};
		showResults(nums1); // expect 4
		
		int[] nums2 = {3,4,5,1,2};
		showResults(nums2); // expect 3

		int[] nums3 = {4,5,6,7,0,1,2};
		showResults(nums3); // expect 4
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		int[] index = new int[nums.length];
		for(int i = 0; i < index.length; ++i)
			index[i] = i;
		System.out.println(Arrays.toString(index));
		System.out.println(Arrays.toString(nums));
		
		int rs = searchSmallest(nums);
		System.out.printf("Smallest: %d\n\n", rs);
	}

	// time: O(logn), space: O(1)
	public static int searchSmallest(int[] nums) {
		int lo = 0, hi = nums.length - 1;
		while(lo < hi) {
			int mid = lo + (hi - lo) / 2;
			if(nums[mid] > nums[hi]) lo = mid + 1;
			else hi = mid;
		}

		return lo;
	}
}

// constraints:
// All elements are UNIQUE 
