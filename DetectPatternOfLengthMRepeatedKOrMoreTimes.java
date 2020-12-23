import java.util.Arrays;

// leetcode 1566.
public class DetectPatternOfLengthMRepeatedKOrMoreTimes {
    public static void main(String[] args) {
        int[] nums1 = {1,2,4,4,4,4};
        int m1 = 1, k1 = 3;
        showResults(nums1, m1, k1); // expect true

        int[] nums2 = {1,2,1,2,1,1,1,3};
        int m2 = 2, k2 = 2;
        showResults(nums2, m2, k2); // expect true

        int[] nums3 = {1,2,1,2,1,3};
        int m3 = 2, k3 = 3;
        showResults(nums3, m3, k3); // expect false

        int[] nums4 = {1,2,3,1,2};
        int m4 = 2, k4 = 2;
        showResults(nums4, m4, k4); // expect false

        int[] nums5 = {2,2,2,2};
        int m5 = 2, k5 = 3;
        showResults(nums5, m5, k5); // expect false
    }

    private static void showResults(int[] nums, int m, int k) {
        System.out.println("----ShowResults----");
        System.out.println(Arrays.toString(nums));
        System.out.printf("m = %d, k = %d\n", m, k);
        boolean rs = containsPattern(nums, m, k);
        System.out.printf("OUTPUT: %b\n\n", rs);
    }

    // Time: O(n), space: O(1)
    public static boolean containsPattern(int[] nums, int m, int k) {
        if(nums.length == 0) return false;

        int j = 0, maxCount = 0, count = m;
        for(int i = m; i < nums.length; ++i) {
            if(nums[i] == nums[j++]) {
                maxCount = Math.max(maxCount, ++count);
                if(maxCount >= k * m) return true;
            } else count = m;
        }

        return maxCount >= k * m;
    }
}