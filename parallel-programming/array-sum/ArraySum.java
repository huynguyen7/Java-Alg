import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.List;
import java.util.ArrayList;

public class ArraySum {
    public static void main(String[] args) {
        final int n = Integer.parseInt(args[0]);
        double[] nums = gen(n);

        printTimeInSequential(nums);
        printTimeInParallel(nums);
    }

    private static void printTimeInSequential(double[] nums) {
        double sum = 0;
        double startTime = System.nanoTime();
        for(double num: nums)
            sum += num;
        double timeTaken = (System.nanoTime()-startTime)*1e-6;
        System.out.printf("SUM = %.5f\tTIME TAKEN IN SEQUENTIAL = %.5f ms\n", sum, timeTaken);
    }

    private static final int MAX_THREADS = 16;

    private static void printTimeInParallel(double[] nums) {
        final int n = nums.length;
        List<Future<Double>> futures = new ArrayList<>();

        for(int numThreads = 1; numThreads <= MAX_THREADS; ++numThreads) {
            double sum = 0;
            final int chunkSize = (n + numThreads) / numThreads;
            ExecutorService pool = Executors.newFixedThreadPool(numThreads);

            double startTime = System.nanoTime();
            for(int thread = 0; thread < numThreads; ++thread) {
                final int startIndex = thread*chunkSize;
                int endIndex = (thread+1)*chunkSize-1;
                if(endIndex >= n) endIndex = n-1;
                futures.add(pool.submit(new ArraySumTask(nums, startIndex, endIndex)));
            }

            try {
                for(Future<Double> future: futures)
                    sum += future.get(); // Block operation.
            } catch(Exception e) {}

            double timeTaken = (System.nanoTime()-startTime)*1e-6;
            System.out.printf("SUM = %.5f\tTIME TAKEN WITH %d THREADS = %.5f ms\n", sum, numThreads, timeTaken);

            // Reset states.
            pool.shutdown();
            futures.clear();
        }

    }

        private static class ArraySumTask implements Callable<Double> {
            private int startIndex;
            private int endIndex;
            private double[] nums;

            private ArraySumTask() {}
            public ArraySumTask(double[] nums, int startIndex, int endIndex) {
                this.nums = nums;
                this.startIndex = startIndex;
                this.endIndex = endIndex;
            }

            // Time: O(endIndex - startIndex)
            public Double call() {
                double sum = 0;
                for(int i = startIndex; i <= endIndex; ++i)
                    sum += nums[i];

                return new Double(sum);
            }
        }

        private static double[] gen(final int n) {
            if(n <= 0) return new double[0];

            Random rand = new Random();
            double[] nums = new double[n];
            for(int i = 0; i < n; ++i)
                nums[i] = rand.nextDouble();

            return nums;
        }
}
