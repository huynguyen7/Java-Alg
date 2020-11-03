import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Callable;

public class ArraySum {
	public static void main(String[] args) throws ExecutionException, InterruptedException{
		int n = Integer.parseInt(args[0]);
		double[] nums = generateRandomIntArr(n);
		printTimeInSequential(nums);
		printTimeInParallel(nums);
	} 

	private static void printTimeInSequential(double[] nums) {
		double sum = 0;

		double startTime = System.nanoTime();
		for(int i = 0; i < nums.length; ++i)
			sum += nums[i];
		double endTime = System.nanoTime();

		System.out.println("Time taken in sequential: " + (endTime - startTime) + " ms.");
		System.out.println("Sum = " + sum + "\n");
	}

	// Note that this code is not fully optimized since .get() might block the main thread.
	private static void printTimeInParallel(final double[] nums) throws InterruptedException, ExecutionException {
		double sum = 0;
		int numThreads = 8;
		Future[] futures = new Future[numThreads];

		ExecutorService service = Executors.newFixedThreadPool(numThreads);
		int inclusive = 0;
		int exclusive = nums.length;
		int range = (exclusive - inclusive) / numThreads;

		double startTime = System.nanoTime();

		for(int p = 0; p < numThreads; ++p) {
			int startCol = p * range + inclusive;
			int endCol = (p == numThreads - 1) ? exclusive : startCol + range;
			
			futures[p] = service.submit(new MyCallable(startCol, endCol, nums));
		}

		for(int i = 0; i < numThreads; ++i) {
			Double x = (Double) futures[i].get();
			sum += x.doubleValue();
		}
		
		double endTime = System.nanoTime();

		System.out.println("Time taken in parallel: " + (endTime - startTime) + " ms.");
		System.out.println("Sum = " + sum + "\n");

		service.shutdown();		
	}

	private static double[] generateRandomIntArr(int n) {
		double[] nums = new double[n];
		Random rand = new Random();
		for(int i = 0; i < nums.length; ++i)
			nums[i] = rand.nextDouble();
		return nums;
	}
}
