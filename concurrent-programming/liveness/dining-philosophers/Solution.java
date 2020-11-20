import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.List;
import java.util.ArrayList;

// This code removes both Deadlocks and Livelocks:
// - Using Unstructured locks -> Remove Deadlocks.
// - By modification on person E, picking right chopstick first instead -> Remove Livelocks.
public class Solution {
	public static void main(String[] args) {
		// Init 5 chopsticks.
		ChopStick c1 = new ChopStick(1);
		ChopStick c2 = new ChopStick(2);
		ChopStick c3 = new ChopStick(3);
		ChopStick c4 = new ChopStick(4);
		ChopStick c5 = new ChopStick(5);
		
		// Init 5 people
		List<PhilosopherEatingLoop> tasks = new ArrayList<>();
		
		tasks.add(new PhilosopherEatingLoop("A", c2, c1));
		tasks.add(new PhilosopherEatingLoop("B", c3, c2));
		tasks.add(new PhilosopherEatingLoop("C", c4, c3));
		tasks.add(new PhilosopherEatingLoop("D", c5, c4));

		// VERY IMPORTANT MODIFICATION:
		// Person E should pick right first instead of left first..
		tasks.add(new PhilosopherEatingLoop("E", c5, c1));

		ExecutorService service = Executors.newFixedThreadPool(5); // 5 hyper threads ~ 5 people.

		try {
			service.invokeAll(tasks);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}

		service.shutdown();
	}

	private static class PhilosopherEatingLoop implements Callable<Void> {
		public String name;
		public ChopStick left;
		public ChopStick right;

		public PhilosopherEatingLoop(String name, ChopStick left, ChopStick right) {
			this.name = name;
			this.left = left;
			this.right = right;
		}

		@Override
		public Void call() {
			while(true) {
				boolean gotLeftChopstick = left.tryLock();
				if(!gotLeftChopstick) continue;
				
				boolean gotRightChopstick = right.tryLock();
				if(!gotLeftChopstick) {
					left.unlock();
					continue;
				}

				eat();
			}
		}

		public void eat() {
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("%s: Eating..\n", name);
			left.unlock();
			right.unlock();
		}
	}

	private static class ChopStick extends ReentrantLock {
		int name;

		public ChopStick(int name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return String.format("[%d]", name);
		}
	}
}
