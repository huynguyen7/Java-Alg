import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Example {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[] nums = generateRandomArr(n);

		printTimeInSerial(nums);
		printTimeInParallel(nums);
	}

	private static void printTimeInSerial(int[] nums) {
		double startTime = System.nanoTime();
		int sum = 0;
		for(int i = 0; i < nums.length; ++i)
			sum += nums[i];
		double endTime = System.nanoTime();

		System.out.println("Time taken in serial: " + (endTime - startTime) + " ms");
		System.out.printf("Sum = %d\n\n", sum);
	}

	private static void printTimeInParallel(int[] nums) {
		ForkJoinPool pool = ForkJoinPool.commonPool();
		ForkJoinTask<Integer> arraySum = new ArraySum(nums, 0, nums.length - 1);

		double startTime = System.nanoTime();
		int rs = pool.invoke(arraySum);
		double endTime = System.nanoTime();

		System.out.println("Time taken in parallel: " + (endTime - startTime) + " ms");
		System.out.printf("Sum = %d\n\n", rs);
	}

	private static int[] generateRandomArr(int n) {
		int[] nums = new int[n];
		Random rand = new Random();
		for(int i = 0; i < nums.length; ++i)
			nums[i] = rand.nextInt(5);
		return nums;
	}
}
