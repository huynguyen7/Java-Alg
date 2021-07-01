import java.util.Arrays;

// leetcode 1909.
public class RemoveOneElementToMakeTheArrayStrictlyIncreasing {
    public static void main(String[] args) {
        int[] nums1 = {1,2,10,5,7};
        showResults(nums1); // expect true

        int[] nums2 = {2,3,1,2};
        showResults(nums2); // expect false

        int[] nums3 = {1,1,1};
        showResults(nums3); // expect false

        int[] nums4 = {1,2,3};
        showResults(nums4); // expect true

        int[] nums5 = {1,1};
        showResults(nums5); // expect true

        int[] nums6 = {100,21,100};
        showResults(nums6); // expect true

        int[] nums7 = {105,924,32,968};
        showResults(nums7); // expect true

        int[] nums8 = {541,783,433,744};
        showResults(nums8); // expect false
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        System.out.printf("RESULT: %b\n\n", canBeIncreasing(nums));
    }

    // Time: O(n), space: O(1)
    public static boolean canBeIncreasing(int[] nums) {
        if(nums.length <= 2) return true;

        int count = 0; // Count number of leaps to make change.
        for(int i = 1;  i < nums.length && count < 2; ++i) {
            if(nums[i] <= nums[i-1]) {
                count++;
                if(i != 1 && nums[i] <= nums[i-2])
                    nums[i] = nums[i-1];
            }
        }

        return count < 2; // If less than two, we can remove at most 1 time to make the array strictly increasing.
    }
}
