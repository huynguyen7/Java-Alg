import java.util.Arrays;

public class SumOfSquaresOfSpecialElements {
    public static void main(String[] args) {
        assert(showResults(new int[] {1,2,3,4}) == 21); // expect 21
        assert(showResults(new int[] {2,7,1,19,18,3}) == 63); // expect 63
    }

    private static int showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        int rs = sumOfSquares(nums);
        System.out.printf("%d\n\n", rs);
        return rs;
    }

    // Time: O(n), space: O(n)
    public static int sumOfSquares(int[] nums) {
        final int n = nums.length;

        int rs = 0;
        for(int i = 0; i < n; ++i)
            if(n % (i+1) == 0)
                rs += nums[i]*nums[i];

        return rs;
    }
}
