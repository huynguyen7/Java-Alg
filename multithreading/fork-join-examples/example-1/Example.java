import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Example {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        printTimeInSerial(n);
        printTimeInParallel(n);
    }

    private static void printTimeInSerial(int n) {
        double start = System.nanoTime();
        int rs = fibonacci(n);
        double end = System.nanoTime();
        System.out.println("Time taken for serialization with recursion: " + (end - start) + " ms");

        start = System.nanoTime();
        rs = fibonacciDP(n);
        end = System.nanoTime();
        System.out.println("Time taken for serialization with DP: " + (end - start) + " ms");
    }

    private static void printTimeInParallel(int n) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> f = new Fibonacci(n);

        double start = System.nanoTime();
        int rs = pool.invoke(f);
        double end = System.nanoTime();
        System.out.println("Time taken for parallelism: " + (end - start) + " ms");
    }

    // Time: O(2^n), space: O(2^n)
    private static int fibonacci(int n) {
        if(n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // DP approach.
    // bottom-up DP.
    // Time: O(n), space: O(1)
    private static int fibonacciDP(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,0);
        map.put(1,1);

        int num = 2;
        while(num <= n) {
            map.put(num % 2, map.get((num - 1) % 2) + map.get((num) % 2));
            num++;
        }

        return map.get(n % 2);
    }
}
