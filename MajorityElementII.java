import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

// leetcode 229.
public class MajorityElementII {
	public static void main(String[] args) {
		int[] nums1 = {3,2,3};
		showResults(nums1); // expect {3}

		int[] nums2 = {1};
		showResults(nums2); // expect {1}

		int[] nums3 = {1,2};
		showResults(nums3); // expect {1,2}

		int[] nums4 = {1,1,2,3,4,1,1,5,6,7,1,1,8,9,10,1,11,12,13,14};
		showResults(nums4); // expect {1}
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		
		List<Integer> rs = majorityElement(nums);
		System.out.println(rs.toString() + "\n");
	}

	// GOOD EXPLANATION:
	// https://www.geeksforgeeks.org/n3-repeated-number-array-o1-space/

	// Find majority elements that appear more than nums.length / 3.
	// Trick: (n/3) * 2 = 2/3 => We can only find max 2 elements only.
	// We cannot find 3 elements.

	// Greedy approach.
	// Time: O(n), space: O(1)
	public static List<Integer> majorityElement(int[] nums) {
		int count1 = 0, count2 = 0;
		int currValue1 = Integer.MIN_VALUE, currValue2 = Integer.MAX_VALUE;

		for(int i: nums) { // get the 2 most appeared elements. 
			if(i == currValue1) {
				count1++;
			} else if(i == currValue2) {
				count2++;
			} else if(count1 == 0) {
				count1 = 1;
				currValue1 = i;
			} else if(count2 == 0) {
				count2 = 1;
				currValue2 = i;
			} else {
				count1--;
				count2--;
			}
		}

		System.out.printf("Value1: %d, Value2: %d\n", currValue1, currValue2);

		count1 = 0;
		count2 = 0;
		for(int i: nums) {
			if(i == currValue1) count1++;
			else if(i == currValue2) count2++;
		}

		List<Integer> rs = new ArrayList<>();
		if(count1 > nums.length / 3) rs.add(currValue1);
		if(count2 > nums.length / 3) rs.add(currValue2);

		return rs;
	}

	// Time: O(nlogn), space: O(1)
	public static List<Integer> majorityElementI(int[] nums) {
		List<Integer> rs = new ArrayList<>();
		
		Arrays.sort(nums); // time: O(nlogn)

		int currValue = nums[0];
		int countOccurrences = 1;

		if(countOccurrences > nums.length / 3)
			rs.add(currValue);
		
		for(int i = 1; i < nums.length; ++i) {
			if(nums[i] == currValue) countOccurrences++;
			else {
				countOccurrences = 1;
				currValue = nums[i];
			}

			if(countOccurrences > nums.length / 3)
				rs.add(currValue);
		}

		return rs;
	}

	// n = nums.length, m = number of UNIQUE values in nums[].
	// Time: O(n), space: O(m)
	public static List<Integer> majorityElementII(int[] nums) {
		List<Integer> rs = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		for(int i: nums)
			map.put(i, map.getOrDefault(i, 0) + 1);

		for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
			if(entry.getValue() > nums.length / 3)
				rs.add(entry.getKey());
		}

		return rs;
	}
}

// Constraints:
// nums.length >= 1
