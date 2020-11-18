import java.util.concurrent.*;

// GOOD EXPLANATION
// https://www.coursera.org/learn/parallel-programming-in-java/lecture/9OMoh/4-4-pipeline-parallelism

public class Example {
	private static final long JOB_TIME = 10; // CHANGE THIS VALUE.

	/*
		------------------------> MAIN
		t0      t1      t2
		|       .       .
	10	|       .       .
		|       .       .
		x       |       .
		        |       .
		        |       .
		        x       |
		                |
		                |
		                x

	**/

	public static void main(String[] args) {
		int iterations = Integer.parseInt(args[0]); // recommend 100
		printTimeInSequential(iterations);
		printTimeInParallel(iterations);
	}

	private static void printTimeInSequential(final int iterations) {
		double startTime = System.nanoTime();

		for(int i = 0; i < iterations; ++i) {
			doWork(JOB_TIME); // first stage
			doWork(JOB_TIME); // second stage
			doWork(JOB_TIME); // third stage
		}

		double endTime = System.nanoTime();
		double timeTaken = (endTime - startTime) / 1e9;
		System.out.printf("Time taken in SEQUENTIAL: %8.3f seconds.\n", timeTaken);
	}

	private static void printTimeInParallel(final int iterations) {

		// Only need 2 phasers, since the third stage
		// does not using any pipelining.
		Phaser ph0 = new Phaser(1);
		Phaser ph1 = new Phaser(1);

		Thread t0 = new Thread(() -> {
			for(int i = 0; i < iterations; ++i) {
				doWork(JOB_TIME);
				ph0.arrive();
			}
		}); // first stage

		Thread t1 = new Thread(() -> {
			for(int i = 0; i < iterations; ++i) {
				ph0.awaitAdvance(i); // barrier
				doWork(JOB_TIME);
				ph1.arrive();
			}
		}); // second stage

		Thread t2 = new Thread(() -> {
			for(int i = 0; i < iterations; ++i) {
				ph1.awaitAdvance(i); // barrier
				doWork(JOB_TIME);
			}
		}); // third stage


		double startTime = System.nanoTime();

		t0.start();
		t1.start();
		t2.start();

		// Make sure all threads are finished their tasks
		// before returning to MAIN thread.
		// For the sake of Time measurement..
		try {
			t0.join();
			t1.join();
			t2.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}

		double endTime = System.nanoTime();
		double timeTaken = (endTime - startTime) / 1e9;
		System.out.printf("Time taken in PARALLEL: %8.3f seconds.\n", timeTaken);
	}

	private static void doWork(long timeTaken) {
		try {
			Thread.sleep(timeTaken);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
