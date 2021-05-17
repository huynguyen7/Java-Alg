// leetcode 343.
public class IntegerBreak {
    public static void main(String[] args) {
        showResults(2); // expect 1
        showResults(10); // expect 36
    }

    private static void showResults(int n) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%d -> %d\n\n", n, integerBreak(n));
    }

    /**
     * DP approach.
     * Bottom-Up DP.
     * Time: O(n^2), space: O(n)
     */
    public static int integerBreak(int n) {
        int[] cache = new int[n+1];
        for(int i = 2; i <= 6 && i <= n; ++i) {
            if(i == 2) cache[i] = 1;
            else if(i == 3) cache[i] = 2;
            else if(i == 4) cache[i] = 4;
            else if(i == 5) cache[i] = 6;
            else if(i == 6) cache[i] = 9;
        }

        for(int i = 7; i <= n; ++i)
            cache[i] = 3*cache[i-3];

        return cache[n];
    }


    /**
     * DP approach.
     * Top-Down DP.
     * Time: O(n^2), space: O(n)
     */
    public static int integerBreakI(int n) {
        int[] cache = new int[n+1];
        for(int i = 2; i <= 6 && i <= n; ++i) {
            if(i == 2) cache[i] = 1;
            else if(i == 3) cache[i] = 2;
            else if(i == 4) cache[i] = 4;
            else if(i == 5) cache[i] = 6;
            else if(i == 6) cache[i] = 9;
        }

        return integerBreakHelperI(n, cache);
    }

    private static int integerBreakHelperI(int n, int[] cache) {
        if(cache[n] == 0)
            cache[n] = 3*integerBreakHelperI(n-3, cache);
        return cache[n];
    }

    /**
     * Recurrsive approach.
     * Time: O(2^n), space: O(2^n)
     */
    public static int integerBreakII(int n) {
        if(n == 2) return 1;
        else if(n == 3) return 2;
        else if(n == 4) return 4;
        else if(n == 5) return 6;
        else if(n == 6) return 9;
        else return 3*integerBreakII(n-3);
    }
}

/**
 * Contraints:
 * 2 <= n <= 58
 */
