import java.util.Arrays;

// leetcode 1608.
public class SpecialArrayWithXElementsGreaterThanOrEqualX {
    public static void main(String[] args) {
        int[] nums1 = {3,5};
        showResults(nums1); // expect 2

        int[] nums2 = {0,0};
        showResults(nums2); // expect -1

        int[] nums3 = {0,4,3,0,4};
        showResults(nums3); // expect 3

        int[] nums4 = {3,6,7,7,0};
        showResults(nums4); // expect -1
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        int rs = specialArray(nums);
        System.out.printf("-> OUTPUT: %d\n\n", rs);
    }

    // Time: O(n), space: O(n)
    public static int specialArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        final int N = nums.length;
        int[] counts = new int[N+1];

        for(int i = 0; i < N; ++i) {
            if(nums[i] < N) counts[nums[i]]++;
            else counts[N]++;
        }

        System.out.println(Arrays.toString(counts));
        for(int i = N; i > 0; --i) {
            if(counts[i] == i) return i;
            else counts[i-1] += counts[i];
        }

        return -1; // Not a special array, return -1.
    }
}
