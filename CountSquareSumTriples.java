public class CountSquareSumTriples {
    public static void main(String[] args) {
        showResults(5); // expect 2
        showResults(10); // expect 4
    }

    private static void showResults(int n) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%d -> %d\n", n, countTriples(n));
    }

    /**
     * Constraints:
     * 1 <= n <= 250
     */

    // Time: O(n^2), space: O(1)
    public static int countTriples(int n) {
        int count = 0;
        for(int a = 1; a < n; ++a) {
            for(int b = 1; b < n; ++b) {
                double sqrt_c = Math.sqrt((double) (a*a+b*b));
                if(sqrt_c > n) break;
                if(isPerfectSquareRoot(sqrt_c))
                    count++;
            }
        }

        return count;
    }

    private static boolean isPerfectSquareRoot(double x) {
        return x*10 - ((int) x)*10 == 0;
    }
}
