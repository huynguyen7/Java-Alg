import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

// leetcode 34, element-prog 12.1
public class FindFirstAndLastPositionInSortedArray {
    public static void main(String[] args) {
        int[] nums1 = { 1, 2, 3, 3, 4, 5, 7, 7, 7, 7, 9 };
        int k1 = 7;
        showResults(nums1, k1); // expect {6, 9}

        int[] nums2 = { 5, 7, 7, 8, 8, 10 };
        int k2 = 8;
        showResults(nums2, k2); // expect {3, 4}

        int[] nums3 = { 5, 7, 7, 8, 8, 10 };
        int k3 = 6;
        showResults(nums3, k3); // expect {-1, -1}

		int[] nums4 = {1,1,1,1,2,2,2,2,3,3,3,3};
		int k4 = 3;
		showResults(nums4, k4); // expect {8, 11}

		int[] nums5 = {1};
		int k5 = 0;
		showResults(nums5, k5); // expect {-1, -1}

		int[] nums6 = {2,2};
		int k6 = 1;
		showResults(nums6, k6); // expect {-1, -1}
    }

    private static void showResults(int[] nums, int k) {
        System.out.println("----ShowwResults----");
        List<Integer> input = new ArrayList<>();
        for (int i : nums)
            input.add(i);

		System.out.printf("Target: %d\n", k);
        int[] index = new int[nums.length];
        for (int i = 0; i < nums.length; ++i)
            index[i] = i;
        System.out.println(Arrays.toString(index));
        System.out.println(input.toString());
		
		int[] rs = findFirstLastOccurence(input, k);
			
        int first = rs[0];
        int last = rs[1]; 

        System.out.printf("First: %d, Last: %d\n\n", first, last);
    }

	public static int[] findFirstLastOccurence(List<Integer> input, int k) {
		if(input.size() == 0) return new int[] {-1,-1};
		else if(input.size() == 1 && input.get(0) == k) return new int[] {0, 0};
		else if(input.size() == 1) return new int[] {-1, -1};

		int[] rs = new int[2];
		rs[0] = searchFirstOfK(input, k);
		rs[1] = searchLastOfK(input, k);

		return rs;
	}

    // time: O(logn), space: O(1)
    public static int searchFirstOfK(List<Integer> nums, int k) {
        if (nums.size() == 0) return -1;
        return binarySearchFirstElement(nums, k, 0, nums.size() - 1);
    }

    private static int binarySearchFirstElement(List<Integer> nums, int k, int lo, int hi) {
        if (lo > hi) return -1;
        else if (lo == hi && nums.get(lo) == k) return lo;

        int mid = lo + (hi - lo) / 2;
		if(nums.get(mid) == k) return binarySearchFirstElement(nums, k, lo, mid);
		else if(nums.get(mid) > k) return binarySearchFirstElement(nums, k, lo, mid - 1);
		else return binarySearchFirstElement(nums, k, mid + 1, hi);
    }

    // time: O(logn), space: O(1)
    public static int searchLastOfK(List<Integer> nums, int k) {
		if(nums.size() == 0) return -1;
		return binarySearchLastElement(nums, k, 0, nums.size() - 1);
    }

	private static int binarySearchLastElement(List<Integer> nums, int k, int lo, int hi) {
		if(lo > hi) {
			if(hi >= 0 && nums.get(hi) == k) return hi;
			else return -1;
		}
		else if(lo == hi && nums.get(lo) == k) return lo;
		
		int mid = lo + (hi - lo) / 2;
		if(nums.get(mid) == k) return binarySearchLastElement(nums, k, mid + 1, hi);
		else if(nums.get(mid) > k) return binarySearchLastElement(nums, k, lo, mid - 1);
		else return binarySearchLastElement(nums, k, mid + 1, hi);
	}
}
