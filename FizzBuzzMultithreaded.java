import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

// leetcode 1195.
public class FizzBuzzMultithreaded {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		FizzBuzzMultithreaded app = new FizzBuzzMultithreaded(n);
		
		Thread t0 = new Thread(() -> {
			try {
				app.fizz(() -> {
					System.out.print("fizz ");
				});
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread t1 = new Thread(() -> {
			try {
				app.buzz(() -> {
					System.out.print("buzz ");
				});
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(() -> {
			try {
				app.fizzbuzz(() -> {
					System.out.print("fizzbuzz ");
				});
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		});
		Thread t3 = new Thread(() -> {
			try {
				app.number((x) -> {
					System.out.printf("%d ", x);
				});
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		});

		try {
			t0.start();
			t1.start();
			t2.start();
			t3.start();

			t0.join();
			t1.join();
			t2.join();
			t3.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("\n");
	}

	/*

	Program that outputs the string representation of numbers from 1 to n, however: 
		- If the number is divisible by 3, output "fizz".
		- If the number is divisible by 5, output "buzz".
		- If the number is divisible by both 3 and 5, output "fizzbuzz".

	**/

	private int n;
	private AtomicInteger counter;
	private Lock lock;

	private Condition condition;

	
	public FizzBuzzMultithreaded(int n) {
		this.n = n;
		counter = new AtomicInteger(1); // start at 1.
		lock = new ReentrantLock();

		condition = lock.newCondition();
	}

	// printFizz.run() outputs "fizz".
	public void fizz(Runnable printFizz) throws InterruptedException {
		lock.lock();
		try {
			while(counter.get() <= n) {
				if(counter.get() % 3 == 0 && counter.get() % 5 > 0) {
					printFizz.run();
					counter.getAndIncrement();
					condition.signalAll();
				} else condition.await();
			}
		} finally {
			lock.unlock();
		}
	}

	// printBuzz.run() outputs "buzz".
	public void buzz(Runnable printBuzz) throws InterruptedException {
		lock.lock();
		try {
			while(counter.get() <= n) {
				if(counter.get() % 3 != 0 && counter.get() % 5 == 0) {
					printBuzz.run();
					counter.getAndIncrement();
					condition.signalAll();
				} else condition.await();
			}
		} finally {
			lock.unlock();
		}
	}

	// printFizzBuzz.run() outputs "fizzbuzz".
	public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
		lock.lock();
		try {
			while(counter.get() <= n) {
				if(counter.get() % 3 == 0 && counter.get() % 5 == 0) {
					printFizzBuzz.run();
					counter.getAndIncrement();
					condition.signalAll();
				} else condition.await();
			}
		} finally {
			lock.unlock();
		}
	}

	// printNumber.accept(x) outputs "x", where x is an integer.
	public void number(IntConsumer printNumber) throws InterruptedException {
		lock.lock();
		try {
			while(counter.get() <= n) {
				if(counter.get() % 3 != 0 && counter.get() % 5 != 0) {
					printNumber.accept(counter.getAndIncrement());
					condition.signalAll();
				} else condition.await();
			}
		} finally {
			lock.unlock();
		}
	}
}
