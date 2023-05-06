import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

// Element of Programming Interview 6.5
public class DeleteDuplicatesFromASortedArray {
    public static void main(String[] args) {
        showResults(new int[] {1,2,2,3,3,4}); // expect {1,2,3,4,0,0}
        showResults(new int[] {1,2}); // expect {1,2}
        showResults(new int[] {1}); // expect {1}
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        int[] rs = solve(nums);
        System.out.println(Arrays.toString(rs));
    }

    // In place deletion.
    // Time: O(n), space: O(1)
    public static int[] solve(int[] nums) {
        final int n = nums.length;

        int idx = 1;
        for(int i = 1; i < n; ++i) {
            if(nums[i] != nums[i-1])
                nums[idx++] = nums[i];
        }

        for( ; idx < n; ++idx)
            nums[idx] = 0;

        return nums;
    }

    // Brute Force
    // Time: O(n), space: O(n)
    public static int[] solveII(int[] nums) {
        final int n = nums.length;
        int[] rs = new int[n];
        Set<Integer> seen = new HashSet<>();

        int idx = 0;
        for(int i = 0; i < n; ++i) {
            if(!seen.contains(nums[i])) {
                rs[idx++] = nums[i];
                seen.add(nums[i]);
            }
        }
        return rs;
    }
}
