import java.util.*;
import java.util.concurrent.*;

public class Example {
	public static void main(String[] args) {
		int numWorkers = Integer.parseInt(args[0]);
		if(numWorkers > 26) {
			System.out.println("INVALID INPUT..");
			System.out.println("0 <= NUM WORKERS < 26");
			return;
		}
			 
		new Example(numWorkers);
	}

	private Phaser phaser;
	private static Map<Integer, Character> mapId;
	private int numWorkers;
	
	public Example(int numWorkers) {
		Thread.currentThread().setName("[Thread MAIN]");
		System.out.printf("%s: HELLO.\n", Thread.currentThread().getName());

		this.numWorkers = numWorkers;
		mapId = initTable();
		phaser = new Phaser(1) { // 1 for main thread as a party
			@Override
			protected boolean onAdvance(int phase, int registeredParties) {
				System.out.println("----");
				System.out.println("Num of arrived parties after  barrier broken: " + phaser.getArrivedParties()); // just to confirm it is equal to registered parties (in order to break the barrier).
				System.out.println("Num of registered parties: " + phaser.getRegisteredParties());

				System.out.println("----");

				return true;
			}
		}; 		phaser.bulkRegister(numWorkers); // adding more parties for phaser, which now equal to 1 + numWorkers.


		for(int i = 0; i < numWorkers; ++i) {
			Thread t = new Thread(new Worker(phaser, i));
			t.setName(String.format("[Thread %d]", i));
			t.start();
		}

		phaser.arriveAndAwaitAdvance(); // barrier
		phaser.forceTermination();
	} 

	public class Worker implements Runnable {
		private Phaser phaser;
		private int numId;

		public Worker(Phaser phaser, int numId) {
			this.phaser = phaser;
			this.numId = numId;
		}

		@Override
		public void run() {
			System.out.printf("%s: HELLO.\n", Thread.currentThread().getName());
			phaser.arrive(); // increment arrived party for phaser, NOTE THAT THIS IS NOT A BARRIER.
			
			// While waiting for other threads to arrive, we can look up for ID.
			char id = mapId.get(numId);
			
			phaser.awaitAdvance(phaser.getPhase()); // barrier
			System.out.printf("%s: GOODBYE %c.\n", Thread.currentThread().getName(), id);
		}
	}

	// Alphabet table
	private static Map<Integer, Character> initTable() {
		Map<Integer, Character> mapId = new HashMap<>();
		for(int i = 0; i < 26; ++i)
			mapId.put(i, (char) (i + 'A'));

		return mapId;
	}
}
