import java.util.*;
import java.util.concurrent.*;

// This program uses barriers to make all workers
// print "HELLO" first, then print "GOODBYE".
public class Example {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("NUM WORKERS = ");
		int numWorkers = scanner.nextInt();

		new Example(numWorkers); // init the program
	}

	private CyclicBarrier cyclicBarrier;

	public Example(int numWorkers) {
		cyclicBarrier = new CyclicBarrier(numWorkers);

		for(int i = 0; i < numWorkers; ++i) {
			Thread worker = new Thread(new Worker());
			worker.setName(String.format("[Thread %d]", i));
			worker.start();
		}
	}

	class Worker implements Runnable {
		@Override
		public void run() {
			String tName = Thread.currentThread().getName();
			System.out.printf("HELLO FROM %s\n", tName);
			
			try {
				cyclicBarrier.await();
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.printf("GOODBYE FROM %s\n", tName);
		}
	}
}
