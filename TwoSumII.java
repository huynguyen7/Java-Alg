import java.util.Arrays;

// leetcode 167.
public class TwoSumII {
	public static void main(String[] args) {
		int[] nums1 = {2,7,11,15};
		showResults(nums1, 9); // expect {1,2}
		
		int[] nums2 = {2,3,4};
		showResults(nums2, 6); // expect {1,3}
		
		int[] nums3 = {-1,0};
		showResults(nums3, -1); // expect {1,2}
	
		int[] nums4 = {1,2,4,6,12,17};
		showResults(nums4, 16); // expect {3,5}
	}

	private static void showResults(int[] nums, int target) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		System.out.printf("Target: %d -> ", target);
		
		int[] rs = twoSumI(nums, target);
		if(rs != null) System.out.print(Arrays.toString(rs) + "\n\n");
		else System.out.println("\n");
	}

	// Best approach.
	// Time: O(n), space: O(1)
	public static int[] twoSumI(int[] nums, int target) {
		int i = 0, j = nums.length - 1;
		
		while(i < j) {
			int currSum = nums[i] + nums[j];
			if(currSum == target) return new int[] {i + 1, j + 1};
			else if(currSum > target) j--;
			else i++;
			
		}

		return null;	
	}

	// Using binary search.
	// Time: O(nlogn), space: O(1)
	public static int[] twoSumII(int[] nums, int target) {
		for(int i = 0; i < nums.length; ++i) {
			int complement = target - nums[i];
			int findComplementIndex = binarySearch(nums, complement);
			if(findComplementIndex != -1)
				return new int[] {i + 1, findComplementIndex + 1};
		}

		return null;
	}

	// Time: O(logn), space: O()
	private static int binarySearch(int[] nums, int target) {
		int lo = 0, hi = nums.length - 1;
		while(lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if(nums[mid] == target) return mid;
			else if(nums[mid] > target) hi = mid - 1;
			else lo = mid + 1;
		}

		return -1; // -1 means cannot find such target
	}
}

// Constraints:
// Input array is sorted.
// It is guaranteed that the output will exactly have one solution.
// OUTPUT indexes array are BASED ONE.
