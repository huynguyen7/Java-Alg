import java.util.Arrays;
import java.util.stream.*;
import java.util.concurrent.*;
import java.util.Random;

public class ArrayAverage {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int iterations = Integer.parseInt(args[1]);
		double[] nums= generateRandomArr(n);
		
		printTimeInSequential(nums.clone(), iterations);
		printTimeInParallelFor(nums, iterations);
		printTimeInParallelForChunking(nums, iterations);
		printTimeInParallelBarrier(nums, iterations);
		printTimeInParallelTaskingBarrier(nums, iterations);
		printTimeInParallelTaskingFuzzyBarrier(nums, iterations);
	}

	final static int CHUNK_SIZE = 1000; // CHANGE THIS VALUE.
	final static int NUM_TASKS = 8; // CHANGE THIS VALUE.

	private static void printTimeInSequential(double[] nums, final int iterations) {
		if(nums == null || nums.length == 0) return;

		int n = nums.length - 1;
		double[] rs = new double[n + 1];
		double startTime = System.nanoTime();

		for(int i = 0; i < iterations; ++i) {
			for(int j = 1; j < n; ++j)
				rs[j] = (nums[j - 1] + nums[j + 1]) / 2.0;
			double[] tmp = nums;
			nums = rs;
			rs = tmp;
		}

		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;
		System.out.println("Time taken in SEQUENTIAL: " + (timeTaken / 1e9) + " seconds.");
	}

	private static double[] rs1;
	private static double[] nums1;

	private static void printTimeInParallelFor(final double[] nums, final int iterations) {
		if(nums == null || nums.length == 0) return;

		int n = nums.length - 1;
		rs1 = new double[n + 1];
		nums1 = cloneArr(nums);
		double startTime = System.nanoTime();

		for(int i = 0; i < iterations; ++i) {
			PCDP.forall(1, n - 1, (j) -> {
				rs1[j] = (nums1[j - 1] + nums1[j + 1]) / 2.0;
			});
			double[] tmp = nums1;
			nums1 = rs1;
			rs1 = tmp;
		}

		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;
		System.out.println("Time taken in PARALLEL FOR: " + (timeTaken / 1e9) + " seconds.");
	}

	private static void printTimeInParallelForChunking(final double[] nums, final int iterations) {
		if(nums == null || nums.length == 0) return;

		int n = nums.length - 1;
		rs1 = new double[n + 1];
		nums1 = cloneArr(nums);
		double startTime = System.nanoTime();
		
		for(int i = 0; i < iterations; ++i) {
			PCDP.forallChunked(1, n - 1, CHUNK_SIZE, (j) -> {
				rs1[j] = (nums1[j - 1] + nums1[j + 1]) / 2.0;
			});
			double[] tmp = nums1;
			nums1 = rs1;
			rs1 = tmp;
		}

		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;
		System.out.println("Time taken in PARALLEL FOR CHUNKING: " + (timeTaken / 1e9) + " seconds.");
	}

	private static void printTimeInParallelBarrier(double[] nums, final int iterations) {
		if(nums == null || nums.length == 0) return;

		Phaser[] phasers = new Phaser[nums.length];
		for(int i = 0; i < phasers.length; ++i)
			phasers[i] = new Phaser(1); // assign only 1 party.

		int n = nums.length - 1;
		rs1 = new double[n + 1];
		nums1 = cloneArr(nums);

		double startTime = System.nanoTime();

		PCDP.forall(1, n - 1, (int j) -> {
			
			for(int i = 0; i < iterations; ++i) {
				rs1[j] = (nums1[j - 1] + nums1[j + 1]) / 2.0;

				// Signal arrival on phaser
				phasers[j].arrive();
				if(j > 1) phasers[j - 1].awaitAdvance(i); // barrier
				if(j < n - 2) phasers[j + 1].awaitAdvance(i); // barrier

				double[] tmp = nums1;
				nums1 = rs1;
				rs1 = tmp;
			}
		});

		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;
		for(Phaser ph: phasers)
			ph.forceTermination();
		System.out.println("Time taken in PARALLEL BARRIER: " + (timeTaken / 1e9) + " seconds.");
	}

	private static void printTimeInParallelTaskingBarrier(double[] nums, final int iterations) {
		if(nums == null || nums.length == 0) return;
		
		Phaser ph = new Phaser(0);
		ph.bulkRegister(NUM_TASKS);

		Thread[] threads = new Thread[NUM_TASKS];

		int n = nums.length - 1;
		rs1 = new double[n + 1];
		nums1 = cloneArr(nums);

		double startTime = System.nanoTime();

		for(int i = 0; i < NUM_TASKS; ++i) {
			int j = i;
			threads[i] = new Thread(() -> {
				for(int iter = 0; iter < iterations; ++iter) {
					int left = j * (n / NUM_TASKS) + 1;
					rs1[left] = nums1[left - 1] + nums1[left + 1] / 2.0;

					int right = (j + 1) * (n / NUM_TASKS);
					rs1[right] = nums1[right - 1] + nums1[right + 1] / 2.0;

					for(int k = left + 1; k <= right - 1; ++k)
						rs1[k] = (rs1[k - 1] + rs1[k + 1]) / 2.0;

					// Signal arrival on phaser
					ph.arriveAndAwaitAdvance(); // barrier
					
					double[] tmp = nums1;
					nums1 = rs1;
					rs1 = tmp;
				}
			});
			threads[i].start();
		}

		try {
			for(Thread t: threads)
				t.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}

		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;
		ph.forceTermination();
		System.out.println("Time taken in PARALLEL TASKING BARRIER: " + (timeTaken / 1e9) + " seconds.");
	}

	private static void printTimeInParallelTaskingFuzzyBarrier(double[] nums, final int iterations) {
		if(nums == null || nums.length == 0) return;
		
		Phaser ph = new Phaser(0);
		ph.bulkRegister(NUM_TASKS);

		Thread[] threads = new Thread[NUM_TASKS];

		int n = nums.length - 1;
		rs1 = new double[n + 1];
		nums1 = cloneArr(nums);

		double startTime = System.nanoTime();

		for(int i = 0; i < NUM_TASKS; ++i) {
			int j = i;
			threads[i] = new Thread(() -> {
				for(int iter = 0; iter < iterations; ++iter) {
					int left = j * (n / NUM_TASKS) + 1;
					rs1[left] = nums1[left - 1] + nums1[left + 1] / 2.0;

					int right = (j + 1) * (n / NUM_TASKS);
					rs1[right] = nums1[right - 1] + nums1[right + 1] / 2.0;

					// Signal arrival on phaser
					int currentPhase = ph.arrive();
					// FUZZY PART
					// Fuzzy means arrive and do something, then wait for others.
					for(int k = left + 1; k <= right - 1; ++k)
						rs1[k] = (rs1[k - 1] + rs1[k + 1]) / 2.0;

					ph.awaitAdvance(currentPhase); // barrier
					
					double[] tmp = nums1;
					nums1 = rs1;
					rs1 = tmp;
				}
			});
			threads[i].start();
		}

		try {
			for(Thread t: threads)
				t.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}

		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;
		ph.forceTermination();
		System.out.println("Time taken in PARALLEL TASKING FUZZY BARRIER: " + (timeTaken / 1e9) + " seconds.");
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
		return nums.clone();
	}
}
