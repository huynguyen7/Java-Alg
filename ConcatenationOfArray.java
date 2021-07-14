import java.util.Arrays;

// leetcode 1929.
public class ConcatenationOfArray {
    public static void main(String[] args) {
        int[] nums1 = {1,2,1};
        showResults(nums1); // expect [1,2,1,1,2,1]

        int[] nums2 = {1,3,2,1};
        showResults(nums2); // expect [1,3,2,1,1,3,2,1]
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        System.out.printf("%s\n\n", Arrays.toString(getConcatenation(nums)));
    }

    // Time: O(n), space: O(n)
    public static int[] getConcatenation(int[] nums) {
        if(nums == null || nums.length == 0) return new int[0];

        final int n = nums.length;
        int[] ans = new int[2*n];

        for(int i = 0; i < 2*n; ++i)
            ans[i] = nums[i%n];

        return ans;
    }
}
