import java.util.Arrays;

// Elements of Programming Interview 6.10 .
public class NextPermutation {
    public static void main(String[] args) {
        assert(isEquals(showResutls(new int[] {1,2,3}),
                                    new int[] {1,3,2}));

        assert(isEquals(showResutls(new int[] {6,2,1,5,4,3,0}),
                                    new int[] {6,2,3,0,1,4,5}));

        assert(isEquals(showResutls(new int[] {}),
                                    new int[] {}));

        assert(isEquals(showResutls(new int[] {0}),
                                    new int[] {0}));

        assert(isEquals(showResutls(new int[] {2,1}),
                                    new int[] {}));

        assert(isEquals(showResutls(new int[] {1,2}),
                                    new int[] {2,1}));
    }

    private static int[] showResutls(int[] nums) {
        System.out.println("\t----ShowResults----");
        int[] copy = nums.clone();
        int[] rs = nextPermutation(copy);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(rs));
        return rs;
    }

    /** Time: O(n), space: O(1) .
     * int[] nums has unique values.
     * (1.) Find i such that p[i] < p[i + 1] and entries after index i appear in decreasing order.
     * (2.) Find the smallest p[j] such that p[j] > p[i] (such an / must exist since p[i] < p[i+j]).
     * (3.) Swap p[j] and p[i] (note that the sequence after position i remains in decreasing order).
     * (4.) Reverse the sequence after position i.
     */
    public static int[] nextPermutation(int[] nums) {
        if(nums == null) return nums;
        final int n = nums.length;
        if(n <= 1) return nums;

        int i = n-1;
        while(i > 0) {
            if(nums[i] > nums[i-1]) break;
            i -= 1;
        }

        if(i == 0) return new int[] {}; // input is max, no next permutation.
        
        int j = i;
        i -= 1;
        for(; j < n; ++j) {
            if(nums[j] < nums[i])
                break;
        }
        j -= 1;
        
        swap(nums, i, j);
        
        // Reverse from i+1 to n.
        int low = i+1, high = n-1;
        while(low < high) {
            swap(nums, low, high);
            low += 1;
            high -= 1;
        }

        return nums;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }

    private static boolean isEquals(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null || nums1.length != nums2.length)
            return false;

        final int n = nums1.length;
        for(int i = 0; i < n; ++i) {
            if(nums1[i] != nums2[i])
                return false;
        }

        return true;
    }
}
