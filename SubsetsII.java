import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

// leetcode 90.
public class SubsetsII {
	public static void main(String[] args) {
		int[] nums1 = {1,2,2};
		showResults(nums1); // expect [
							//			  [2],
							//			  [1],
							//			  [1,2,2],
							//			  [2,2],
							//			  [1,2],
							//			  []]

		int[] nums2 = {1,1};
		showResults(nums2); // expect [[],[1],[1,1]]
	
		int[] nums3 = {4,1,4};
		showResults(nums3);
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		
		List<List<Integer>> rs = subsetsWithDup(nums);
		System.out.printf("\nTotal combinations: %d\n", rs.size());
		
		for(List<Integer> l: rs)
			System.out.println(l.toString());
		System.out.println();
	}

	private static List<List<Integer>> rs;
	
	// time complexity for recursive calls: 2^n
	// time for loop: n
	// Time: O(n * 2^n), space: O(n * 2^n)
	public static List<List<Integer>> subsetsWithDup(int[] nums) {
		rs = new ArrayList<>();
		if(nums.length == 0) return rs;

		Arrays.sort(nums); // VERY IMPORTANT
		
		List<Integer> holder = new ArrayList<>();
		backtrack(nums, holder, 0);
		return rs;
	}

	private static void backtrack(int[] nums, List<Integer> holder, int startIndex) {
		rs.add(new ArrayList<>(holder));
		if(startIndex == nums.length) return; // goal

		for(int j = startIndex; j < nums.length; ++j) {
			if(j > startIndex && nums[j] == nums[j-1]) continue; // constraints

			int val = nums[j];
			holder.add(val); // make choice
			backtrack(nums, holder, j + 1); // explore
			holder.remove(holder.size() - 1); // undo choice
		}
	}
}
