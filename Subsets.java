import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

// leetcode 78, element-prog 16.4
public class Subsets {
	public static void main(String[] args) {
		int[] nums1 = {0,1,2};
		showResults(nums1); // expect {{},{0},{1},{2},{0,1},{1,2},{0,2},
							// {0,1,2}}

		int[] nums2 = {0};
		showResults(nums2); // expect {{}, {0}}
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		List<Integer> listNums = new ArrayList<>();
		for(int i: nums)
			listNums.add(i);
		System.out.println(listNums.toString());
		
		
		System.out.println("\nOUTPUT: ");
		List<List<Integer>> rs = generatePowerSet(listNums);
		System.out.printf("Number of power set: %d\n", rs.size());
		for(List<Integer> list: rs)
			System.out.println(list.toString());
		System.out.println();
	}

	private static List<List<Integer>> rs;
	
	// Time: O(n * 2^n), space: O(n * 2^n)
	// we have 2^n elements(2^n recursive calls)
	// each recursive call have a loop with O(n) time complexity
	public static List<List<Integer>> generatePowerSet(List<Integer> nums) {
		rs = new ArrayList<>();
		if(nums.size() == 0) {
			rs.add(new ArrayList<>()); // empty subset
			return rs;
		}

		List<Integer> holder = new ArrayList<>();
		backtrack(nums, holder, 0);
		return rs;
	}

	private static void backtrack(List<Integer> nums, List<Integer> holder, int startIndex) {
		rs.add(new ArrayList<>(holder));
		if(startIndex == nums.size()) return; // goal
		
		for(int j = startIndex; j < nums.size(); ++j) { // Time: O(n), space: O(n)
			Integer val = nums.get(j); // -> cost space
			holder.add(val); // make choice
			backtrack(nums, holder, j + 1); // explore
			holder.remove(holder.size() - 1); // undo choice
		}
	}
}

// Constraints:
// all elements are UNIQUE.
