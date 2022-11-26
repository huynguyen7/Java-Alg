import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

// leetcode 2475.
public class NumberOfUnequalTripletsInArray {
    public static void main(String[] args) {
        assert(showResults(new int[] {4,4,2,4,3}) == 3); // expect 3
        assert(showResults(new int[] {1,1,1,1,1}) == 0); // expect 0
    }

    private static int showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        int rs = unequalTriplets(nums);
        System.out.printf("%s\n-> %d\n\n", Arrays.toString(nums), rs);
        return rs;
    }

    /**
     * n = nums.length
     * Time: O(n^3), space: O(1)
     */
    public static int unequalTriplets(int[] nums) {
        final int n = nums.length;
        int count = 0;

        for(int i = 0; i < n; ++i) {
            for(int j = i+1; j < n; ++j) {
                if(nums[i] == nums[j]) continue;
                for(int k = j+1; k < n; ++k) {
                    if(nums[i] == nums[k] ||
                        nums[j] == nums[k]) continue;
                    count += 1;
                }
            }
        }

        return count;
    }
}
