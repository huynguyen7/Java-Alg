import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

// leetcode 2357.
public class MakeArrayZeroBySubtractingEqualAmounts {
    public static void main(String[] args) {
        assert(showResults(new int[] {1,5,0,3,5}) == 3); // expect 3
        assert(showResults(new int[] {0}) == 0); // expect 0
    }

    private static int showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        int rs = minimumOperations(nums);
        System.out.printf("%s\n-> %d\n\n", Arrays.toString(nums), rs);
        return rs;
    }

    // Time: O(n), space: O(n)
    public static int minimumOperations(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for(int num: nums)
            if(num != 0) seen.add(num);
        return seen.size();
    }
}
