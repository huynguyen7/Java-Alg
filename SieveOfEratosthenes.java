import java.util.Arrays;

/**
 * Source: https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
 */

public class SieveOfEratosthenes {
    public static void main(String[] args) {
        showResults(10); // expect 4
        showResults(0); // expect 0
        showResults(1); // expect 0
        showResults(13); // expect 6
    }

    private static void showResults(int n) {
        System.out.println("\t----ShowResults----");
        System.out.printf("INPUT: %d\n", n);
        System.out.printf("OUTPUT: %d\n", sieve(n));
    }

    // Time: O(n*sqrt(n)), space: O(n)
    public static int sieve(int n) {
        if(n <= 1) return 0;

        int[] buffer = new int[n+1];
        int i,j,mult;

        for(i = 2; i <= n; ++i) {
            j = 2;
            mult = i*j;
            while(mult <= n) {
                buffer[mult] = 1;
                j++;
                mult = i*j;
            }
        }

        int count = 0;
        for(i = 2; i <= n; ++i)
            count += buffer[i] == 1 ? 0 : 1;

        /** Uncomment this block to see the list of prime numbers.
        List<Integer> primes = new ArrayList<>();
        for(int i = 2; i <= n; ++i) {
            if(buffer[i] == 0)
                primes.add(i);
        }
        System.out.println(primes.toString());
        */

        return count;
    }
}
