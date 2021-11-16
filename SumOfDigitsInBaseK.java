// leetcode 1837.
public class SumOfDigitsInBaseK {
    public static void main(String[] args) {
        showResults(34,6); // expect 9
        showResults(10,10); // expect 1
    }

    private static void showResults(int n, int k) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%d %d\n%d\n\n", n, k, sumBase(n, k));
    }

    /**
     * Input n is base 10 integer.
     */

    // Time: O(n/k), space: O(1)
    public static int sumBase(int n, int k) {
        int newN = 0;
        int sumDigit = 0;

        // Convert to n in base k.
        int currN = n;
        int base = 1;
        while(currN > 0) {
            int digit = currN % k;
            newN = newN + digit*base;
            base *= 10;
            currN /= k;
        }

        // Get the sum digit.
        while(newN > 0) {
            int digit = newN % 10;
            sumDigit += digit;
            newN /= 10;
        }

        return sumDigit;
    }
}
