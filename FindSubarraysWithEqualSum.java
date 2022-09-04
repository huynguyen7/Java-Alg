import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

// leetcode 2395.
public class FindSubarraysWithEqualSum {
    public static void main(String[] args) {
        assert(showResults(new int[] {4,2,4})); // expect true
        assert(!showResults(new int[] {1,2,3,4,5})); // expect false
        assert(showResults(new int[] {0,0,0})); // expect true
    }

    private static boolean showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        boolean rs = findSubarrays(nums);
        System.out.printf("%s\n%b\n\n", Arrays.toString(nums), rs);
        return rs;
    }

    // Time: O(n), space: O(n)
    public static boolean findSubarrays(int[] nums) {
        final int n = nums.length;
        if(n < 3) return false;

        Set<Integer> seen = new HashSet<>();
        for(int i = 1; i < n; ++i) {
            int sum = nums[i] + nums[i-1];
            if(!seen.contains(sum)) seen.add(sum);
            else return true;
        }

        return false;
    }
}
