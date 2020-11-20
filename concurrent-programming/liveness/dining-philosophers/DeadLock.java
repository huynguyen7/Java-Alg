import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;

// CAREFUL WHEN RUN THIS CLASS => DEADLOCK.
// Run this code.
// Note that the code will stuck and stop running (Deadlocks)..
public class DeadLock {
	public static void main(String[] args) {
		// Init 5 chopsticks.
		ChopStick c1 = new ChopStick(1);
		ChopStick c2 = new ChopStick(2);
		ChopStick c3 = new ChopStick(3);
		ChopStick c4 = new ChopStick(4);
		ChopStick c5 = new ChopStick(5);

		// Init 5 people eating 's loop
		List<PhilosopherEatingLoop> tasks = new ArrayList<>();
		
		tasks.add(new PhilosopherEatingLoop("A", c2, c1));
		tasks.add(new PhilosopherEatingLoop("B", c3, c2));
		tasks.add(new PhilosopherEatingLoop("C", c4, c3));
		tasks.add(new PhilosopherEatingLoop("D", c5, c4));
		tasks.add(new PhilosopherEatingLoop("E", c1, c5));

		ExecutorService service = Executors.newFixedThreadPool(5); // 5 hyper threads ~ 5 people.

		try {
			service.invokeAll(tasks);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}

		service.shutdown();
	}

	// A person needs to pick both left and right chopsticks in order to eat.

	private static class PhilosopherEatingLoop implements Callable<Void> {
		public String name;
		public ChopStick left;
		public ChopStick right;
		
		public PhilosopherEatingLoop(String name, ChopStick left, ChopStick right) {
			this.name = name;
			this.left = left;
			this.right = right;
		}

		// Use structured locks => Deadlock might be available.
		@Override
		public Void call() {
			while(true) { // infinite loop.
				synchronized(this.left) {
					synchronized(this.right) {
						eat();
					}
				}
			}
		}

		public void eat() {
			try {
				Thread.sleep(100);
				System.out.printf("%s: Eating..\n", this.name);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static class ChopStick {
		public int name;

		public ChopStick(int name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return String.format("[%d]", name);
		}
	}
}
