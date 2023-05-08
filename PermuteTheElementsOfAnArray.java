import java.util.Arrays;

// Elements of Programming Interview 6.9 .
public class PermuteTheElementsOfAnArray {
    public static void main(String[] args) {
        assert(isEqual(showResults(new int[] {2,0,1,3}, new int[] {0,1,2,3}), new int[] {1,2,0,3}));
        assert(isEqual(showResults(new int[] {1,2,3,0}, new int[] {0,1,2,3}), new int[] {3,0,1,2}));
        assert(isEqual(showResults(new int[] {0}, new int[] {0}), new int[] {0}));
        assert(isEqual(showResults(new int[] {}, new int[] {}), new int[] {}));
    }

    private static int[] showResults(int[] permutations, int[] nums) {
        assert(permutations != null && nums != null && permutations.length == nums.length);
        System.out.println("\t----ShowResults----");
        int[] copy = nums.clone();
        int[] rs = permuteTheElementsOfAnArrayI(permutations, copy);
        System.out.printf("%s\n%s\n%s\n\n", Arrays.toString(permutations), Arrays.toString(nums), Arrays.toString(rs));
        return rs;
    }
   
    // Time: O(n), space: O(1)
    public static int[] permuteTheElementsOfAnArrayI(int[] permutations, int[] nums) {
        final int n = nums.length;
        if(n <= 1) return nums;

        int counter = 0;
        int currIdx = 0;
        int curr = nums[currIdx];
        int prev = curr;

        while(counter < n) {
            curr = nums[permutations[currIdx]];
            nums[permutations[currIdx]] = prev;
            currIdx = permutations[currIdx];
            counter += 1;
            prev = curr;
        }

        return nums;
    }

    // Time: O(n), space: O(n)
    public static int[] permuteTheElementsOfAnArrayII(int[] permutations, int[] nums) {
        final int n = nums.length;
        int[] rs = new int[n];
        for(int i = 0; i < n; ++i)
            rs[permutations[i]] = nums[i];

        return rs;
    }

    private static boolean isEqual(int[] nums1, int[] nums2) {
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
