import java.util.Random;
import java.util.concurrent.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Example {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		int n = Integer.parseInt(args[0]);
		
		int[] nums = generateRandomNumberArr(n);
		printTimeSerial(nums);
		printTimeParallel(nums);
	}

	private static void printTimeSerial(int[] nums) {
		double startTime = System.nanoTime();
		int sum1 = Arrays.stream(nums)
						.sum();
		int sum2 = Arrays.stream(nums)
						.sum();
		double endTime = System.nanoTime();
		System.out.println("Time taken with serial: " + ((endTime - startTime)/ 1e9) + " seconds.");
		System.out.println("Sum = " + (sum1 + sum2) + "\n");
	}

	// Fork another thread to run in parallel.
	private static void printTimeParallel(int[] nums) throws ExecutionException, InterruptedException {
		ExecutorService service = Executors.newSingleThreadExecutor();
		Callable<Integer> task = () -> {
			return Arrays.stream(nums)
						.sum();
		};

		double startTime = System.nanoTime();
		Future<Integer> future = service.submit(task);
		int sum1 = Arrays.stream(nums)
						.sum();
		int sum2 = future.get().intValue();
		double endTime = System.nanoTime();
		System.out.println("Time taken with parallel: " + ((endTime - startTime) / 1e9) + " seconds.");
		System.out.println("Sum = " + (sum1 + sum2) + "\n");

		service.shutdown();
	}

	private static int[] generateRandomNumberArr(int n) {
		int[] nums = new int[n];
		Random rand = new Random();
		for(int i = 0; i < nums.length; ++i)
			nums[i] = rand.nextInt(10);
		return nums;
	}
}
