import java.util.Arrays;

// element-prog 12.2
public class SearchASortedArrayForEntryEqualsToItsIndex {
    public static void main(String[] args) {
        int[] nums1 = { -1, 1, 3, 4, 4, 5, 5 };
        showResults(nums1); // expect 4 or 5

		int[] nums2 = {-2,-1,0,0,2,2,3};
		showResults(nums3); // expect -1
		
		int[] nums3 = {-2,0,2,3,6,7,9};
		showResults(nums4); // expect 2 or 3
    }

    private static void showResults(int[] nums) {
        System.out.println("----ShowResults----");
		
		int[] index = new int[nums.length];
		for(int i = 0; i < index.length; ++i)
			index[i] = i;
		System.out.println(Arrays.toString(index));
        System.out.println(Arrays.toString(nums));

        int rs = searchValueEqualsIndex(nums);
        System.out.printf("Result: %d\n\n", rs);
    }

    public static int searchValueEqualsIndex(int[] nums) {
        //return binarySearch(nums, 0, nums.length - 1);
		return loopSearch(nums);
    }

	//time: O(logn)
    private static int binarySearch(int[] nums, int lo, int hi) {
        if (lo > hi) return -1;
		
		int mid = lo + (hi - lo) / 2;
		if(nums[mid] == mid) return mid;
		else if(nums[mid] > mid) return binarySearch(nums, lo, mid - 1);
		else return binarySearch(nums, mid + 1, hi);
    }

	//time: O(logn)
	private static int loopSearch(int[] nums) {
		int lo = 0, hi = nums.length - 1;
		while(lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if(nums[mid] == mid) return mid;
			else if(nums[mid] > mid) hi = mid - 1;
			else lo = mid + 1;
		}

		return -1;
	}
}
