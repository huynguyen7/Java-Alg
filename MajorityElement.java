import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

// leetcode 169, element-prog 18.5
public class MajorityElement {
	public static void main(String[] args) {
		int[] nums1 = {3,2,3};
		showResults(nums1); // expect 3

		int[] nums2 = {2,2,1,1,1,2,2};
		showResults(nums2); // expect 2
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));

		int rs = majorityElement(nums);
		System.out.printf("Majority Element: %d\n\n", rs);
	}

	// Majority element is the element that appears more than
	// n / 2 times, n = nums.length

	// Greedy approach.
	// Time: O(n), space: O(1)
	public static int majorityElement(int[] nums) {
		int currValue = nums[0], countOccurences = 0; // init dummy values.
		
		for(int i = 0; i < nums.length; ++i) {
			if(countOccurences == 0) {
				currValue = nums[i];
				countOccurences = 1;
			} else if(currValue == nums[i]) ++countOccurences;
			else --countOccurences;
		}

		return currValue;
	}

	// Time: O(nlogn), space: O(1)
	public static int majorityElementI(int[] nums) {
		Arrays.sort(nums); // time: O(nlogn)
		
		int countOccurences = 1;
		int currValue = nums[0];
		for(int i = 1; i < nums.length; ++i) { // time: O(n)
			if(nums[i] == currValue) countOccurences++;
			else {
				countOccurences = 1;
				currValue = nums[i];
			}

			if(countOccurences > nums.length / 2) return currValue;
		}

		return nums[0]; // 0 means there is no element that is majority element.
	}

	// n = nums.length.
	// m is the number of UNIQUE values in nums[].
	// Time: O(n), space: O(m)
	public static int majorityElementII(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>(); // <value-freq> pair
		for(int i: nums)
			map.put(i, map.getOrDefault(i, 0) + 1);

		for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
			if(entry.getValue() > nums.length / 2)
				return entry.getKey();
		}

		return nums[0]; // 0 means there is no element that is majority element.
	}
}

// Constraints:
// nums.length >= 1
