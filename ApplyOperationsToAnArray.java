import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

// leetcode 2460.
public class ApplyOperationsToAnArray {
    public static void main(String[] args) {
        assert(equals(showResults(new int[] {1,2,2,1,1,0}), new int[] {1,4,2,0,0,0}));
        assert(equals(showResults(new int[] {0,1}), new int[] {1,0}));
    }

    private static int[] showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        int[] rs = applyOperations(Arrays.copyOf(nums, nums.length));
        System.out.printf("%s\n%s\n",
                Arrays.toString(nums),
                Arrays.toString(rs));
        return rs;
    }

    // Time: O(n), space: O(n)
    public static int[] applyOperations(int[] nums) {
        final int n = nums.length;

        for(int i = 0; i < n; ++i) {
            if(i+1 < n && nums[i] == nums[i+1]) {
                nums[i] *= 2;
                nums[i+1] = 0;
            }
        }

        int[] rs = new int[n];
        int tmp = 0;
        for(int i = 0; i < n; ++i) {
            if(nums[i] != 0)
                rs[tmp++] = nums[i];
        }

        return rs;
    }

    private static boolean equals(int[] nums1, int[] nums2) {
        assert(nums1 != null && nums2 != null);
        assert(nums1.length == nums2.length);

        final int n = nums1.length;
        for(int i = 0; i < n; ++i)
            if(nums1[i] != nums2[i]) return false;
        return true;
    }
}
