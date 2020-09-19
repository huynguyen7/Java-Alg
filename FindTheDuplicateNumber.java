import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

// leetcode 287, element-prog 12.10
public class FindTheDuplicateNumber {
	public static void main(String[] args) {
		int[] nums1 = {1,3,4,2,2};
		showResults(nums1); // expect 2
		
		int[] nums2 = {3,1,3,4,2};
		showResults(nums2); // expect 3

		int[] nums3 = {1,1};
		showResults(nums3); // expect 1
		
		int[] nums4 = {1,1,2};
		showResults(nums4); // expect 1
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		
		int rs = findDuplicateI(nums);
		System.out.printf("Duplicate element: %d\n\n", rs);
	}

	//best approach, using cyclic traversing for inclusive value array
	// Time: O(n), space: O(1)
	public static int findDuplicateI(int[] nums) {
		int slow = nums[0];
		int fast = nums[0];
		slow = nums[slow];
		fast = nums[nums[fast]];
		
		while(slow != fast) { // finding starting cycle element
			slow = nums[slow];
			fast = nums[nums[fast]];
		}

		slow = nums[0];
		while(slow != fast) { // distance from starting ele to duplicate ele
							// equals to distance from starting cycle element to duplicate ele
			slow = nums[slow];
			fast = nums[fast];
		}

		return slow;
	}
	
	// naive approach
	// Time: O(n), space: O(n)
	public static int findDuplicateII(int[] nums) {
		Set<Integer> set = new HashSet<>();
		for(int i = 0; i < nums.length; ++i) {
			if(!set.contains(nums[i])) set.add(nums[i]);
			else return nums[i];
		}

		return -1;
	}

	// Time: O(nlogn), space: O(1)
	public static int findDuplicateIII(int[] nums) {
		Arrays.sort(nums);
		for(int i = 1; i < nums.length; ++i)
			if(nums[i] == nums[i - 1]) return nums[i];
		return -1;
	}
}

// Constraints:
// Array has only one duplicate element
// Value is from 0 -> n - 1; n is array's length
