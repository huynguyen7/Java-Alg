import java.util.concurrent.*;
import java.util.*;

// GOOD EXPLANATION:
// https://www.baeldung.com/java-cyclic-barrier

public class Example {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("NUM WORKERS = ");
		int NUM_WORKERS = scanner.nextInt();
		System.out.print("NUM PARTIAL RESULTS = ");
		int NUM_PARTIAL_RESULTS = scanner.nextInt();

		new Example(NUM_WORKERS, NUM_PARTIAL_RESULTS); // init program.
	}

	private CyclicBarrier cyclicBarrier;
	private List<List<Integer>> partialResults;
	private Random rand;
	private final int NUM_WORKERS;
	private final int NUM_PARTIAL_RESULTS;

	public Example(int NUM_WORKERS, int NUM_PARTIAL_RESULTS) {
		System.out.println("-----INIT-PROGRAM-----");
		cyclicBarrier = new CyclicBarrier(NUM_WORKERS, new Aggregator());
		rand = new Random();
		this.NUM_WORKERS = NUM_WORKERS;
		this.NUM_PARTIAL_RESULTS = NUM_PARTIAL_RESULTS;
		partialResults = Collections.synchronizedList(new ArrayList<List<Integer>>());

		System.out.printf("Spawning %d worker threads to compute %d partial results each..\n\n", NUM_WORKERS, NUM_PARTIAL_RESULTS);

		System.out.println("-----PROCESSING-----");
		for(int i = 0; i < NUM_WORKERS; ++i) {
			Thread worker = new Thread(new Worker());
			worker.setName(String.format("[Thread %d]", i));
			worker.start();
		}
	}

	class Worker implements Runnable {
		@Override
		public void run() {
			String tName = Thread.currentThread().getName();
			List<Integer> partialResult = new ArrayList<>();
			
			for(int i = 0; i < NUM_PARTIAL_RESULTS; ++i) {
				Integer num = rand.nextInt(10);
				//System.out.printf("%s: generates %d.\n", tName, num.intValue());
				partialResult.add(num);
			}

			partialResults.add(partialResult);

			try {
				System.out.println(tName + " waiting for others to reach barrier..");
				cyclicBarrier.await();
			} catch(InterruptedException e) {
				e.printStackTrace();
			} catch(BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

	// This thread object runs when the barrier has been stripped.
	class Aggregator implements Runnable {
		@Override
		public void run() {
			String tName = Thread.currentThread().getName();
			System.out.printf("%s: Computing sum of %d workers, having %d results each.\n", tName, NUM_WORKERS, NUM_PARTIAL_RESULTS);
			
			int sum = 0;
			for(List<Integer> partialResult: partialResults) {
				for(Integer val: partialResult)
					sum += val;
			}

			System.out.printf("\n%s: Final result = %d\n\n", tName, sum);
		}
	}
}
