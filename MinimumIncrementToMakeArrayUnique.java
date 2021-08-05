import java.util.Arrays;

// leetcode 945.
public class MinimumIncrementToMakeArrayUnique {
    public static void main(String[] args) {
        showResults(new int[] {1,2,2}); // expect 1
        showResults(new int[] {3,2,1,2,1,7}); // expect 6
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        System.out.printf("OUTPUT: %d\n\n", minIncrementForUnique(nums));
    }
    
    // Time: O(nlogn), space: O(1)
    public static int minIncrementForUnique(int[] nums) {
        if(nums == null || nums.length == 1) return 0;

        int count = 0;
        Arrays.sort(nums);

        for(int i = 1; i < nums.length; ++i) {
            if(nums[i] <= nums[i-1]) {
                int diff = Math.abs(nums[i]-nums[i-1])+1;
                nums[i] += diff;
                count += diff;
            }
        }

        return count;
    }
}
