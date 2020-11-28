/*
	Use the array sum problem (Test the AtomicInteger class implemented by Optimistic concurrency).
*/

import java.util.concurrent.*;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);		

		System.out.println("\t----ATOMIC-INTEGER----");
		System.out.print("Enter the number of threads: ");
		int maxNumThreads = scnr.nextInt();
		System.out.print("Enter array's size: ");
		int size = scnr.nextInt();
		
		if(maxNumThreads < 1 || size < 0) {
			System.out.println("INVALID INPUT.");
			return;
		}

		System.out.println();
		Main program = new Main(maxNumThreads, size);
		program.start();
		System.out.println();
	}

	private final int MAX_NUM_THREADS;
	private final int SIZE;
	private final int[] nums;
	private AtomicInteger num;

	public Main(int maxNumThreads, int size) {
		this.MAX_NUM_THREADS = maxNumThreads;
		this.SIZE = size;
		this.nums = Main.createRandomArr(size);
	}

	public void start() {
		double startTime = System.nanoTime();
		
		int sum = 0;
		for(int i = 0; i < nums.length; ++i)
			sum += nums[i];

		double endTime = System.nanoTime();
		double timeTaken = (endTime - startTime) / 1e6; // milliseconds.
		System.out.printf("\t->TIME TAKEN IN SEQUENTIAL: %8.3f ms\tSUM = %d\n\n", timeTaken, sum);

		System.out.println("\t->TIME TAKEN IN PARALLEL:");
		for(int numThreads = 1; numThreads <= MAX_NUM_THREADS; numThreads++) {
			ExecutorService service = Executors.newFixedThreadPool(numThreads);
			AtomicInteger num = new AtomicInteger(0); // default value is 0.
			List<Callable<Integer>> myTasks = new ArrayList<>();
			
			startTime = System.nanoTime();

			final int chunkSize = (nums.length + numThreads - 1) / numThreads;
			
			for(int threadId = 0; threadId < numThreads; ++threadId) {
				int lo = (threadId * chunkSize);
				int hi = ((threadId + 1) * chunkSize) - 1;
				if(hi >= nums.length) // prevent out of bound.
					hi = nums.length - 1;

				//System.out.printf("\tThread %d: [%d,%d]\n\n", threadId, lo, hi);
				myTasks.add(new MyCallable(num, nums, lo, hi));
			}

			try {
				List<Future<Integer>> rs = service.invokeAll(myTasks);
				for(Future<Integer> future: rs)
					future.get(); // wait for the task to complete.
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

			endTime = System.nanoTime();
			service.shutdown(); // terminate the pool.
			timeTaken = (endTime - startTime) / 1e6; // milliseconds.
			System.out.printf("\t\tNUM_THREADS = %d\tTIME_TAKEN = %8.3f ms\tSUM = %d\n", numThreads, timeTaken, num.get());
		}
	}

	private static class MyCallable implements Callable<Integer> {
		private AtomicInteger num;
		private final int[] nums;
		private final int lo;
		private final int hi;
		
		public MyCallable(AtomicInteger num, int[] nums, int lo, int hi) {
			this.num = num;
			this.nums = nums;
			this.lo = lo;
			this.hi = hi;
		}

		@Override
		public Integer call() {
			for(int i = lo; i <= hi; ++i)
				num.getAndAdd(nums[i]);
			return num.get();
		}
	}

	private static int[] createRandomArr(int size) {
		Random rand = new Random();
		int[] nums = new int[size];
		for(int i = 0; i < nums.length; ++i)
			nums[i] = rand.nextInt(10);
		return nums;
	} 
}
