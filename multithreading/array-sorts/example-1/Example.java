import java.util.Arrays;
import java.util.Random;

public class Example {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[] nums = generateRandom(n);
		int[] clone = nums.clone();

		printTimeInSequential(nums);
		printTimeInParallel(clone);
	}

	private static void printTimeInSequential(int[] nums) {
		double startTime = System.nanoTime();
		Arrays.sort(nums);
		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;

		System.out.println("Time taken in SEQUENTIAL: " + timeTaken + " ms.");
	}
	
	private static void printTimeInParallel(int[] nums) {
		double startTime = System.nanoTime();
		Arrays.parallelSort(nums);
		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;

		System.out.println("Time taken in PARALLEL: " + timeTaken + " ms.");
	}

	private static int[] generateRandom(int n) {
		Random rand = new Random();
		int[] nums = new int[n];
		for(int i = 0; i < n; ++i)
			nums[i] = rand.nextInt(100);
		return nums;
	}
}
