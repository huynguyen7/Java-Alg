import java.util.Arrays;

// leetcode 1984.
public class MinimumDifferenceBetweenHighestAndLowestOfKScores {
    public static void main(String[] args) {
        showResults(new int[] {90}, 1); // expect 0
        showResults(new int[] {9,4,1,7}, 2); // expect 2
    }

    private static void showResults(int[] nums, int k) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        System.out.printf("%d -> %d\n\n", k, minimumDifference(nums, k));
    }

    // Time: O(nlogn), space: O(1)
    public static int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums); // time: O(nlogn)

        int start = 0, end = k-1; // inclusive bound for k students.
        int ans = Integer.MAX_VALUE;

        while(end < nums.length) {
            ans = Math.min(ans, nums[end]-nums[start]);
            start++;
            end++;
        }

        return ans;
    }
}
