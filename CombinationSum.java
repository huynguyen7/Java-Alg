import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// leetcode 39.
public class CombinationSum {
    public static void main(String[] args) {
        showResults(new int[] {2,3,6,7}, 7); // expect [[2,2,3],[7]]
        showResults(new int[] {2,3,5}, 8); // expect [[2,2,2,2],[2,3,3],[3,5]]
        showResults(new int[] {2}, 1); // expect []
    }

    private static void showResults(int[] candidates, int target) {
        System.out.println("\t----ShowResults----");
        List<List<Integer>> rs = combinationSum(candidates, target);
        System.out.printf("Target: %d\n%s\n%s\n\n", target, Arrays.toString(candidates),
                                                rs.toString());
    }

    // Time: O(n^n), space: O(n)
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> rs = new ArrayList<>();
        List<Integer> holder = new ArrayList<>();
        backtrack(candidates, target, rs, holder, 0, 0);
        return rs;
    }
    
    private static void backtrack(int[] candidates, int target, List<List<Integer>> rs, List<Integer> holder, int currSum, int startIndex) {
        if(currSum > target) return; // Limit
        else if(currSum == target) { // Goal
             rs.add(new ArrayList<>(holder)); // Add to resulting set.
        } else {
            for(int i = startIndex; i < candidates.length; ++i) {
                int val = candidates[i];
                // Make choice
                currSum += val;
                holder.add(val);
                
                // Explore
                backtrack(candidates, target, rs, holder, currSum, i);
                
                // Undo choice
                currSum -= val;
                holder.remove(holder.size()-1);
            }
        }
    } 
}
