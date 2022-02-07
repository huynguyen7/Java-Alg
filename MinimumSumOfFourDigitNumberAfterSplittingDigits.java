import java.util.Arrays;

// leetcode 2160.
public class MinimumSumOfFourDigitNumberAfterSplittingDigits {
    public static void main(String[] args) {
        assert(showResults(2932) == 52); // expect 52
        assert(showResults(4009) == 13); // expect 13
    }

    private static int showResults(int num) {
        System.out.println("\t----ShowResults----");
        int rs = minimumSum(num);
        System.out.printf("%d -> %d\n\n", num, rs);
        return rs;
    }

    // Since those operations always yield in 4 digits. Thus, we have constant time complexity.
    // Time: O(1), space: O(1)
    public static int minimumSum(int num) {
        int[] digits = new int[4];

        // Find each digit and put to digits array.
        final int BASE = 10;
        for(int i = 0; i < 4; ++i) {
            digits[i] = num % BASE;
            num /= BASE;
        }
        
        Arrays.sort(digits);
        int minSum = digits[0]*10 + digits[3] +
                     digits[1]*10 + digits[2];

        return minSum;
    }
}
