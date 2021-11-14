import java.util.Arrays;

// leetcode 645.
public class SetMismatch {
    public static void main(String[] args) {
        showResults(new int[] {1,2,2,4}); // expect [2,3]
        showResults(new int[] {1,1}); // expect [1,2]
        showResults(new int[] {2,2}); // expect [2,1]
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s\n%s\n\n", Arrays.toString(nums), Arrays.toString(findErrorNums(nums)));
    }

    // Time: O(n), space: O(n)
    public static int[] findErrorNums(int[] nums) {
        final int n = nums.length;
        int[] flag = new int[n];

        for(int i = 0; i < n; ++i)
            flag[nums[i]-1]++;

        int numDups = 1;
        int misMatch = 1;
        for(int i = 0; i < n; ++i) {
            if(flag[i] == 2) numDups = i+1;
            if(flag[i] == 0) misMatch = i+1;
        }

        return new int[] {numDups, misMatch};
    }
}
