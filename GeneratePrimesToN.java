import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Elements of Programming Interview 6.8 .
 * Only accept n as positive integer.
 * Prime numbers are larger than 1 (>1).
*/
public class GeneratePrimesToN {
    public static void main(String[] args) {
        showResults(9); // expect [2,3,5,7]
        showResults(11); // expect [2,3,5,7,11]
    }
    
    private static void showResults(int n) {
        System.out.println("\t----ShowResults----");
        List<Integer> primes = generatePrimesToNII(n);
        System.out.printf("INPUT N: %d\n%s\n\n", n, primes.toString());
    }

    // Time: O( n * log(log(n)) ) aka n^(5/4), space: O(n)
    public static List<Integer> generatePrimesToN(int n) {
        assert(n >= 0);
        if (n <= 1) return Collections.emptyList();

        List<Integer> primes = new ArrayList<>();
        boolean[] isPrimeBool = new boolean[n+1];
        Arrays.fill(isPrimeBool, true);

        for(int i = 2; i <= n; ++i) {
            if(isPrimeBool[i]) {
                primes.add(i);
                for(int j = i+i; j <= n; j += i)
                    isPrimeBool[j] = false;
            }
        }

        return primes;
    }

    // Time: O(nlogn), space; O(n)
    public static List<Integer> generatePrimesToNII(int n) {
        assert(n >= 0);
        if (n <= 1) return Collections.emptyList();

        List<Integer> primes = new ArrayList<>();
        for(int i = 2; i <= n; ++i)
            if(isPrime(i)) primes.add(i);

        return primes;
    }

    private static boolean isPrime(int n) {
        final int sqrtN = (int) Math.sqrt(n);
        for(int i = 2; i <= sqrtN; ++i) {
            if(n % i == 0) return false;
        }

        return true;
    }
}
