import java.util.Arrays;

// leetcode 2057.
public class SmallestIndexWithEqualValue {
    public static void main(String[] args) {
        showResults(new int[] {0,1,2}); // expect 0
        showResults(new int[] {4,3,2,1}); // expect 2
        showResults(new int[] {1,2,3,4,5,6,7,8,9,0}); // expect -1
        showResults(new int[] {2,1,3,5,2}); // expect 1
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        System.out.printf("%d\n\n", smallestEqual(nums));
    }

    // Time: O(n), space: O(1)
    public static int smallestEqual(int[] nums) {
        for(int i = 0; i < nums.length; ++i) {
            if(i%10 == nums[i])
                return i;
        }
        return -1;
    }
}
