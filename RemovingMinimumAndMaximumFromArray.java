import java.util.Arrays;

// leetcode 2091.
public class RemovingMinimumAndMaximumFromArray {
    public static void main(String[] args) {
        assert(showResults(new int[] {2,10,7,5,4,1,8,6}) == 5); // expect 5
        assert(showResults(new int[] {0,-4,19,1,8,-2,-3,5}) == 3); // expect 3
        assert(showResults(new int[] {101}) == 1); // expect 1
    }

    private static int showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        int rs = minimumDeletions(nums);
        System.out.printf("%s\n%d\n\n", Arrays.toString(nums), rs);
        return rs;
    }

    // Time: O(n), space: O(1)
    public static int minimumDeletions(int[] nums) {
        final int n = nums.length;
        if(n < 3) return n;

        int maxVal    = Integer.MIN_VALUE;
        int minVal    = Integer.MAX_VALUE;
        int minIndex  = -1;
        int maxIndex = -1;

        for(int i = 0; i < n; ++i) {
            if(nums[i] > maxVal) {
                maxVal = nums[i];
                maxIndex = i;
            }

            if(nums[i] < minVal) {
                minVal = nums[i];
                minIndex = i;
            }
        }


        int a = Math.max(minIndex, maxIndex) + 1; // both left.
        int b = minIndex+1 + n - maxIndex; // min left, max right.
        int c = maxIndex+1 + n - minIndex; // max left, min right.
        int d = n - Math.min(minIndex, maxIndex); // both right.

        int rs = Math.min(Math.min(a,b),
                Math.min(c,d));

        return rs;
    }
}
