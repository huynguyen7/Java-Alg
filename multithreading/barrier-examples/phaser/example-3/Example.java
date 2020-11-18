import java.util.concurrent.*;

// SOURCE:
// https://www.coursera.org/learn/parallel-programming-in-java/lecture/7N2iG/4-5-data-flow-parallelism

public class Example {
	// These are the amount of time 
	// for each job runs by t thread
	// in milliseconds.
	private static final long t0Job0 = 100;
	private static final long t0Job1 = 300;

	private static final long t1Job0 = 200;
	private static final long t1Job1 = 200;

	private static final long t2Job0 = 300;
	private static final long t2Job1 = 100;

	public static void main(String[] args) {
		double time1 = printTimeInSeq();
		double time2 = printTimeInParallel();

		double speedup = ((time1 - time2) / time1) * 100.0;
		System.out.println("SPEEDUP: " + speedup + "%");
	}

	private static double printTimeInSeq() {
		double startTime = System.nanoTime();

		doWork(t0Job0); // thread 0 - job 0
		doWork(t0Job1); // thread 0 - job 1
		
		doWork(t1Job0); // thread 1 - job 0
		doWork(t1Job1); // thread 1 - job 1

		doWork(t2Job0); // thread 2 - job 0
		doWork(t2Job1); // thread 2 - job 1

		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;
		System.out.println("Time taken in SEQUENTIAL: " + (timeTaken/1e9) + " seconds.");

		return timeTaken;
	}

	private static double printTimeInParallel() {
		Phaser ph0 = new Phaser(1);
		Phaser ph1 = new Phaser(1);
		Phaser ph2 = new Phaser(1);

		Thread t0 = new Thread(() -> {
			doWork(t0Job0);
			ph0.arrive(); // signal phaser 0
			ph1.awaitAdvance(0);
			doWork(t0Job1);
		});
		
		Thread t1 = new Thread(() -> {
			doWork(t1Job0);
			ph1.arrive();
			ph0.awaitAdvance(0);
			ph2.awaitAdvance(0);
			doWork(t1Job1);
		});

		Thread t2 = new Thread(() -> {
			doWork(t2Job0);
			ph2.arrive();
			ph1.awaitAdvance(0);
			doWork(t2Job1);
		});

		double startTime = System.nanoTime();

		t0.start();
		t1.start();
		t2.start();

		try { // Make sure all these threads finished their tasks before
			// going back to main thread..
			t0.join();
			t1.join();
			t2.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}

		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;

		// Stop phasers.
		ph0.forceTermination();
		ph1.forceTermination();
		ph2.forceTermination();

		System.out.println("Time taken in PARALLEL: " + (timeTaken/1e9) + " seconds.");

		return timeTaken;
	}

	private static void doWork(long timeTaken) {
		try {
			Thread.sleep(timeTaken);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
