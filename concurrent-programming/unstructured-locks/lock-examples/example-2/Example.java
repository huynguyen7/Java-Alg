import java.util.concurrent.locks.*;
import java.util.concurrent.*;

// GOOD EXPLANATION
// https://www.youtube.com/watch?v=N0mMm5PF5Ow&list=PLhfHPmPYPPRk6yMrcbfafFGSbE2EPK_A6&index=10

class Example {
	public static void main(String[] args) {
		Example example = new Example();
		new Thread(new MyRunnableI(example)).start(); // thread 1
		new Thread(new MyRunnableII(example)).start(); // thread 2
	}

	Lock lock;
	Condition conditionMet;

	public Example () {
		lock = new ReentrantLock();
		conditionMet = lock.newCondition();
	}

	static class MyRunnableI implements Runnable {
		Lock lock;
		Condition conditionMet;

		public MyRunnableI(Example example) {
			this.lock = example.lock;
			this.conditionMet = example.conditionMet;
		}

		@Override
		public void run() {
			lock.lock();

			try {
				conditionMet.await(); // wait for signal.. => Suspended until it gets signal.
				System.out.println("Im in thread I.");
			} catch(InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
	
	static class MyRunnableII implements Runnable {
		Lock lock;
		Condition conditionMet;

		public MyRunnableII(Example example) {
			this.lock = example.lock;
			conditionMet = example.conditionMet;
		}

		@Override
		public void run() {
			lock.lock();

			try {
				Thread.sleep(2000); // wait for 2 seconds
				conditionMet.signal(); // then signal the condition.
				System.out.println("Im in thread II.");
			} catch(InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
}
