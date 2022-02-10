import java.util.Arrays;

// leetcode 2165.
public class SmallestValueOfTheRearrangedNumber {
    public static void main(String[] args) {
        assert(showResults(310) == 103); // expect 103
        assert(showResults(-7605) == -7650); // expect -7650
        assert(showResults(94) == 49); // expect 49
        assert(showResults(95005) == 50059); // expect 50059
        assert(showResults(21702) == 10227); // expect 50059
        assert(showResults(2170596702L) == 1002256779L); // expect 1002256779
    }

    private static long showResults(long num) {
        System.out.println("\t----ShowResults----");
        long rs = smallestNumber(num);
        System.out.printf("%d -> %d\n\n", num, rs);
        return rs;
    }

    // Time: O(n), space: O(1)
    public static long smallestNumber(long num) {
        boolean isNegative = num < 0;
        int numDigits = (int) Math.log10(num)+1;
        int[] counts = new int[10];

        while(num != 0) {
            long digit = num%10L;
            if(digit < 0) digit *= -1L;
            counts[(int)digit] += 1L;
            num /= 10L;
        }

        long rs = 0;

        if(isNegative) {
            for(int i = 9; i >= 0; --i) {
                while(counts[i] != 0) {
                    rs *= 10;
                    rs += i;
                    counts[i] -= 1;
                }
            }
        } else {
            for(int i = 1; i <= 9; ++i) {
                while(counts[i] != 0) {
                    rs *= 10;
                    rs += i;
                    counts[i] -= 1;
                    while(counts[0] != 0) {
                        rs *= 10;
                        counts[0] -= 1;
                    }
                }
            }
        }

        return isNegative ? rs*(-1) : rs;
    }
}
