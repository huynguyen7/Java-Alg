import java.util.Arrays;

// leetcode 1848.
public class MinimumDistanceToTheTargetElement {
    public static void main(String[] args) {
        int[] nums1 = {1,2,3,4,5};
        int target1 = 5;
        int start1 = 3;
        showResults(nums1, target1, start1); // expect 1

        int[] nums2 = {1};
        int target2 = 1;
        int start2 = 0;
        showResults(nums2, target2, start2); // expect 0

        int[] nums3 = {1,1,1,1,1,1,1,1,1,1};
        int target3 = 1;
        int start3 = 0;
        showResults(nums3, target3, start3); // expect 0


        int[] nums4 = {1,1,1,1,1,1,1,1,1,1};
        int target4 = 1;
        int start4 = 9;
        showResults(nums4, target4, start4); // expect 0
    }

    private static void showResults(int[] nums, int target, int start) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        System.out.printf("TARGET: %d, START: %d\n", target, start);
        System.out.printf("OUTPUT: %d\n\n", getMinDistance(nums, target, start));
    }

    // Time: O(n), space: O(1)
    public static int getMinDistance(int[] nums, int target, int start) {
        int minIndex = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; ++i) {
            if(nums[i] == target)
                minIndex = Math.min(minIndex, Math.abs(i-start));
        }
        return minIndex;
    }
}
