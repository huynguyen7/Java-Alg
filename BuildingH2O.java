import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;

// leetcode 1117.
public class BuildingH2O {
	public static void main(String[] args) {
		String input = args[0];

		BuildingH2O app = new BuildingH2O(input);

		Thread t0 = new Thread(() -> {
			try {
				app.hydrogen(() -> {
					System.out.print("H");
				});
			} catch(InterruptedException e) {}
		});

		Thread t1 = new Thread(() -> {
			try {
				app.hydrogen(() -> {
					System.out.print("H");
				});
			} catch(InterruptedException e) {}
		});

		Thread t2 = new Thread(() -> {
			try {
				app.oxygen(() -> {
					System.out.print("O");
				});
			} catch(InterruptedException e) {}
		});

		try {
			t0.start();
			t1.start();
			t2.start();
			t0.join();
			t1.join();
			t2.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println();
	}

	private final AtomicInteger length;
	private ReentrantLock lock;
	private Condition twoHydros;
	private int numHydro;

	public BuildingH2O(String input) {
		length = new AtomicInteger(input.length());
		numHydro = 0;
		lock = new ReentrantLock();
		twoHydros = lock.newCondition();
	}

	public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
		while(true) {
			lock.lock();
			try {
				while(numHydro == 2) {
					twoHydros.await();
					if(length.get() <= 0) return;
				}

				numHydro++;
				length.getAndDecrement();
				releaseHydrogen.run();

				twoHydros.signalAll();
				if(length.get() <= 0) return;
			} finally {
				lock.unlock();
			}
		}
	}

	public void oxygen(Runnable releaseOxygen) throws InterruptedException {
		while(true) {
			lock.lock();
			try {
				while(numHydro < 2) {
					twoHydros.await();
					if(length.get() <= 0) return;
				}

				numHydro = 0;
				length.getAndDecrement();
				releaseOxygen.run();
				twoHydros.signalAll();
				if(length.get() <= 0) return;
			} finally {
				lock.unlock();
			}
		}
	}
}
