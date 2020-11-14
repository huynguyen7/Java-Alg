import java.util.Arrays;
import java.util.stream.*;
import java.util.concurrent.*;
import java.util.Random;

public class ArrayAverage {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int iterations = Integer.parseInt(args[1]);
		double[] nums = generateRandomArr(n);
		
		printTimeInSequential(nums.clone(), iterations);
		printTimeInParallelFor(nums, iterations);
		printTimeInParallelForChunking(nums, iterations);
	}

	private static void printTimeInSequential(double[] nums, final int iterations) {
		if(nums == null || nums.length == 0) return;

		int n = nums.length;
		double[] rs = new double[n];
		double startTime = System.nanoTime();

		for(int i = 0; i < iterations; ++i) {
			for(int j = 1; j < n - 1; ++j)
				rs[j] = (nums[j - 1] + nums[j + 1]) / 2.0;
			double[] tmp = nums;
			nums = rs;
			rs = tmp;
		}

		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;
		System.out.println("Time taken in SEQUENTIAL: " + timeTaken + " ms.");
	}

	private static double[] rs1;
	private static double[] nums1;

	private static void printTimeInParallelFor(final double[] nums, final int iterations) {
		if(nums == null || nums.length == 0) return;

		int n = nums.length;
		rs1 = new double[n];
		nums1 = cloneArr(nums);
		double startTime = System.nanoTime();

		for(int i = 0; i < iterations; ++i) {
			PCDP.forall(1, n - 2, (j) -> {
				rs1[j] = (nums1[j - 1] + nums1[j + 1]) / 2.0;
			});
			double[] tmp = nums1;
			nums1 = rs1;
			rs1 = tmp;
		}

		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;
		System.out.println("Time taken in PARALLEL FOR: " + timeTaken + " ms.");
	}

	private static void printTimeInParallelForChunking(final double[] nums, final int iterations) {
		if(nums == null || nums.length == 0) return;

		final int CHUNK_SIZE = 1000; // CHANGE THIS VALUE.
		int n = nums.length;
		rs1 = new double[n];
		nums1 = cloneArr(nums);
		double startTime = System.nanoTime();
		
		for(int i = 0; i < iterations; ++i) {
			PCDP.forallChunked(1, n - 2, CHUNK_SIZE, (j) -> {
				rs1[j] = (nums1[j - 1] + nums1[j + 1]) / 2.0;
			});
			double[] tmp = nums1;
			nums1 = rs1;
			rs1 = tmp;
		}

		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;
		System.out.println("Time taken in PARALLEL FOR CHUNKING: " + timeTaken + " ms.");
	}

	private static void printTimeInParallelBarrier(double[] nums, final int iterations) {
		if(nums == null || nums.length == 0) return;

		double startTime = System.nanoTime();
		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;
		System.out.println("Time taken in PARALLEL BARRIER: " + timeTaken + " ms.");
	}

	private static double[] generateRandomArr(int n) {
		if(n <= 0) return new double[0];

		Random rand = new Random();
		double[] nums = new double[n];
		for(int i = 0; i < n; ++i)
			nums[i] = rand.nextDouble();

		return nums;
	}

	private static double[] cloneArr(final double[] nums) {
		double[] rs = new double[nums.length];
		for(int i = 0; i < rs.length; ++i)
			rs[i] = nums[i];
		return rs;
	}
}
