import java.util.Arrays;

// leetcode 1413.
public class MinimumValueToGetStepByStepSum {
    public static void main(String[] args) {
        int[] nums1 = {-3,2,-3,4,2};
        showResults(nums1); // expect 5
        int[] nums2 = {1,2};
        showResults(nums2); // expect 1
        int[] nums3 = {1,-2,-3};
        showResults(nums3); // expect 5
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        int rs = minStartValue(nums);
        System.out.printf("Min Value: %d\n\n", rs);
    }

    // Time: O(n), space: O(1)
    public static int minStartValue(int[] nums) {
        if(nums.length == 0) return 1;

        int min = 1;
        int sum = 0;
        for(int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            min = Math.max(min, 1 - sum);
        }

        return min;
    }
}
