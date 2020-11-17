import java.util.*;
import java.util.concurrent.*;

public class Example {
	public static void main(String[] args) {
		int numWorkers = Integer.parseInt(args[0]); // recommend 3
		new Example(numWorkers);
	}

	private Phaser phaser;
	private int numWorkers;
	
	public Example(int numWorkers) {
		this.numWorkers = numWorkers;
		phaser = new Phaser(1);
		phaser.bulkRegister(numWorkers);

		System.out.println("Phase in MAIN " + phaser.getPhase() + " started.");

		// Start first phase.
		for(int i = 0; i < numWorkers; ++i)
			new Thread(new Worker1("[Thread " + i + "]", phaser)).start();

		int currPhase = phaser.getPhase();
		// This makes main thread to wait (barrier).
		phaser.arriveAndAwaitAdvance();
		System.out.println("Phase in MAIN " + currPhase + " completed.");

		// Start second phase.
		for(int i = 0; i < numWorkers - 1; ++i)
			new Thread(new Worker2("[Thread " + i + "]", phaser)).start();
		
		phaser.arriveAndAwaitAdvance();
		System.out.println("Phase in MAIN-2 " + phaser.getPhase() + " completed.");
		phaser.arriveAndDeregister();
	}

	class Worker1 implements Runnable {
		private String tName;
		private Phaser phaser;

		// When worker object is instantiated,
		// it will be registed to the phaser.
		public Worker1(String tName, Phaser phaser) {
			this.tName = tName;
			this.phaser = phaser;
			//phaser.register();
		}

		@Override
		public void run() {
			System.out.printf("%s: In First Task..\n", tName);
			phaser.arriveAndAwaitAdvance(); // barrier
			System.out.println("Deregistering, Phase- " + phaser.getPhase() + " completed.");
			phaser.arriveAndDeregister(); // deregister the worker from phaser.
		}
	}

	class Worker2 implements Runnable {
		private String tName;
		private Phaser phaser;

		// When worker object is instantiated,
		// it will be registed to the phaser.
		public Worker2(String tName, Phaser phaser) {
			this.tName = tName;
			this.phaser = phaser;
			phaser.register();
		}

		@Override
		public void run() {
			System.out.printf("%s: In Second Task..\n", tName);
			phaser.arriveAndAwaitAdvance(); // barrier
			System.out.println(tName + ": In Second Task.. Phase- " + phaser.getPhase() + " completed.");
			phaser.arriveAndDeregister();
		}
	}
}
