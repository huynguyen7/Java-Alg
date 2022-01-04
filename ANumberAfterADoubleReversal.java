// leetcode 2119.
public class ANumberAfterADoubleReversal {
    public static void main(String[] args) {
        assert(showResults(526)); // expect true
        assert(!showResults(1800)); // expect false
        assert(showResults(0)); // expect true
    }

    private static boolean showResults(int num) {
        System.out.println("\t----ShowResults----");
        boolean rs = isSameAfterReversals(num);
        System.out.printf("%d -> %b\n\n", num, rs);

        return rs;
    }

    private static int getNumDigits(int num) {
        return (int) Math.log10(num)+1;
    }

    // n is the number of digits in num.
    // Time: O(n), space: O(1)
    public static boolean isSameAfterReversals(int num) {
        int numDigitsBefore = getNumDigits(num);

        int reversedNum = 0;
        while(num != 0) {
            reversedNum *= 10;
            reversedNum += num%10;
            num /= 10;
        }

        int numDigitsAfter = getNumDigits(reversedNum);

        return numDigitsBefore == numDigitsAfter;
    }
}
