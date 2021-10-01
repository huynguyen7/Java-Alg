import java.util.Arrays;

// leetcode 2016.
public class MaximumDifferenceBetweenIncreasingElements {
    public static void main(String[] args) {
        showResults(new int[] {7,1,5,4}); // expect 4
        showResults(new int[] {9,4,3,2}); // expect -1
        showResults(new int[] {1,5,2,10}); // expect 9
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        System.out.printf("-> %d\n\n", maximumDifference(nums));
    }

    // Time: O(n^2), space: O(1)
    public static int maximumDifference(int[] nums) {
        int i,j;
        int maxDiff = -1;

        for(i = 0; i < nums.length; ++i) {
            for(j = i+1; j < nums.length; ++j) {
                if(nums[j] != nums[i])
                    maxDiff = Math.max(maxDiff, nums[j]-nums[i]);
            }
        }

        return maxDiff;
    }
}
