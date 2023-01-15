import java.util.Arrays;

// leetcode 2535.
public class DifferenceBetweenElementSumAndDigitSumOfAnArray {
    public static void main(String[] args) {
        assert(showResults(new int[] {1,15,6,3}) == 9); // expect 9
        assert(showResults(new int[] {1,2,3,4}) == 0); // expect 0
    }

    private static int showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        int rs = differenceOfSum(nums);
        System.out.println(Arrays.toString(nums));
        System.out.printf("-> %d\n\n", rs);
        return rs;
    }

    // Time: O(n), space: O(1)
    public static int differenceOfSum(int[] nums) {
        int sumArray = Arrays.stream(nums).reduce(0, (int a, int b) -> a + b);
        int sumDigits = 0;
        for(int num: nums)
            sumDigits += calcSumDigits(num);
        
        return Math.abs(sumArray-sumDigits);
    }

    private static int calcSumDigits(int num) {
        int numDigits = (int) Math.log10(num)+1;
        int sum = 0;
        
        while(numDigits-- > 0) {
            sum += num%10;
            num /= 10;
        }

        return sum;
    }
}
