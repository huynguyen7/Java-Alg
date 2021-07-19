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

    // Time: O(n*loglog(n)), space: O(n)
    public static int sieve(int n) {
        if(n <= 1) return 0;

        int[] buffer = new int[n+1];
        int i,j;

        int sqrt_n = (int) Math.sqrt(n);
        for(i = 2; i <= sqrt_n; ++i) {
            if(buffer[i] == 0) {
                for(j = i*i; j <= n; j += i)
                    buffer[j] = 1;
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

        System.out.println(Arrays.toString(buffer));

        return count;
    }
}
