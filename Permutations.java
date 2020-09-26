import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.LinkedHashSet;

// leetcode 46, element-prog 16.3
public class Permutations {
	public static void main(String[] args) {
		int[] nums1 = {1,2,3};
		showResults(nums1); // expect [
							//	  [1,2,3],
							//	  [1,3,2],
							//	  [2,1,3],
							//	  [2,3,1],
							//	  [3,1,2],
							//	  [3,2,1]]
		
		int[] nums2 = {2,3,5,7};
		showResults(nums2);
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		
		System.out.println("OUTPUT");
		List<List<Integer>> rs = permuteI(nums);
		System.out.printf("\nTotal combinations: %d\n", rs.size());
		for(List<Integer> l: rs)
			System.out.println(l.toString());
		System.out.println();
	}
    private static List<List<Integer>> rs;
    
    // best approach
    // Time: O(n * n!), space: O(n!)
    // swapping elements in the list
    public static List<List<Integer>> permuteI(int[] nums) {
        rs = new ArrayList<>();
        if(nums.length == 0) return rs;
        
        List<Integer> listNums = new ArrayList<>();
        for(int i = 0; i < nums.length; ++i)
            listNums.add(nums[i]);
        
        backtrack(listNums, 0);
        return rs;
    }
    
    private static void backtrack(List<Integer> nums, int startIndex) {
        if(startIndex == nums.size() - 1) { // goal
            rs.add(new ArrayList<>(nums));
            return;
        }
        
        for(int j = startIndex; j < nums.size(); ++j) {
            Collections.swap(nums, startIndex, j); // make choice
            backtrack(nums, startIndex + 1); // explore
            Collections.swap(nums, startIndex, j); // undo choice
        }
    }
    
    // better approach
    // using set instead of list -> contains() takes O(1) time complexity
    public static List<List<Integer>> permuteII(int[] nums) {
        rs = new ArrayList<>();
        if(nums.length == 0) return rs;
        
        Set<Integer> holder = new LinkedHashSet<>(); // using linked hashset to keep the order of adding/removing
        backtrackII(nums, holder, 0);
        
        return rs;
    }
    
    private static void backtrackII(int[] nums, Set<Integer> holder, int startIndex) {
        if(holder.size() == nums.length) { // goal
            rs.add(new ArrayList<>(holder));
            return;
        }
        
        for(int i = 0; i < nums.length; ++i) {
            int val = nums[i];
            if(!holder.contains(nums[i])) { // Time: O(1)
                holder.add(val); // make choice
                backtrackII(nums, holder, startIndex + 1); // explore
                holder.remove(val); // undo choice
            }
        }
    }
    
    // naive approach since this needs to call contains() everytime
    // and create a temporary holder list.
	public static List<List<Integer>> permuteIII(int[] nums) {
		if(nums.length == 0) return new ArrayList<List<Integer>>();
        
		List<List<Integer>> rs = new ArrayList<>();
		List<Integer> permutation = new ArrayList<>();
		permuteHelper(nums, rs, permutation);
		
		return rs;
	}

	private static void permuteHelper(int[] nums, List<List<Integer>> rs, List<Integer> permutation) {
		if(permutation.size() == nums.length) {
			List<Integer> tmp = new ArrayList<>();
			tmp.addAll(permutation);
			rs.add(tmp);
			return;
		}
		
		for(int i = 0; i < nums.length; ++i) {
			if(!permutation.contains(nums[i])) { // Time: O(n)
				permutation.add(nums[i]);
				permuteHelper(nums, rs, permutation);
				permutation.remove((Integer) nums[i]);
			}
		}
	}
}
