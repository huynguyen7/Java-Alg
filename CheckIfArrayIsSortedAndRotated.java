import java.util.Arrays;

// leetcode 1752.
public class CheckIfArrayIsSortedAndRotated {
    public static void main(String[] args) {
        int[] nums1 = {3,4,5,1,2};
        showResults(nums1); // expect true

        int[] nums2 = {2,1,3,4};
        showResults(nums2); // expect false

        int[] nums3 = {1,2,3};
        showResults(nums3); // expect true

        int[] nums4 = {1,1,1};
        showResults(nums4); // expect true

        int[] nums5 = {2,1};
        showResults(nums5); // expect true

    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        boolean rs = check(nums);
        System.out.printf("RESULTS: %b\n\n", rs);
    }

    // Time: O(n), space: O(1)
    public static boolean check(int[] nums) {
        if(nums == null || nums.length == 0) return true;

        int count = 0;
        for(int i = 1; i < nums.length; ++i) {
            if(nums[i-1] > nums[i])
                count++;
        }
        if(nums[nums.length-1] > nums[0]) count++;

        if(count > 1) return false;
        return true;
    }
}
