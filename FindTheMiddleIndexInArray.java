import java.util.Arrays;

// leetcode 1991.
public class FindTheMiddleIndexInArray {
    public static void main(String[] args) {
        showResults(new int[] {2,3,-1,8,4}); // expect 3
        showResults(new int[] {1,-1,4}); // expect 2
        showResults(new int[] {2,5}); // expect -1
        showResults(new int[] {1}); // expect 0
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        System.out.printf("MID INDEX: %d\n\n", findMiddleIndex(nums));
    }

    // Time: O(n), space: O(1)
    public static int findMiddleIndex(int[] nums) {
        int midIndex=0, leftSum=0, rightSum=0;
        int i;

        for(i = 1; i < nums.length; ++i)
            rightSum += nums[i];

        if(rightSum == 0) return 0;

        for(i = 1; i < nums.length; ++i) {
            rightSum -= nums[i];
            leftSum += nums[i-1];
            if(leftSum == rightSum) break;
        }

        if(leftSum == rightSum) return i;
        else return -1;
    }
}
