import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

// leetcode 523.
public class ContinuousSubarraySum {
    public static void main(String[] args) {
        assert(showResults(new int[] {23,2,4,6,7}, 6)); // expect true
        assert(showResults(new int[] {23,2,6,4,7}, 6)); // expect true
        assert(!showResults(new int[] {23,2,6,4,7}, 13)); // expect false
        assert(showResults(new int[] {5,0,0,0}, 3)); // expect true
        assert(showResults(new int[] {1,2,3}, 5)); // expect true
        assert(!showResults(new int[] {1,0}, 2)); // expect false
        assert(!showResults(new int[] {0,1}, 2)); // expect false
        assert(!showResults(new int[] {1,2,12}, 6)); // expect false
    }

    private static boolean showResults(int[] nums, int k) {
        System.out.println("\t----ShowResults----");
        boolean rs = checkSubarraySum(nums, k);
        System.out.printf("%d -> %s\n%b\n\n", k, Arrays.toString(nums),
                rs);
        return rs;
    }

    /**
     * Tricky problem with modular ops.
     * Constraints:
     *   nums[i] >= 0 
     */

    // n = nums.length
    // Time: O(n), space: O(k)
    public static boolean checkSubarraySum(int[] nums, int k) {
        if(nums.length < 2) return false;

        Set<Integer> modSums = new HashSet<>();
        int currSum = 0, prevSum = 0;

        for(int num: nums) {
            currSum += num;
            currSum %= k;

            if(modSums.contains(currSum))
                return true;

            modSums.add(prevSum);
            prevSum = currSum;
        }

        return false;
    }
}
