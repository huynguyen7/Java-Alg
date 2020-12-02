import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.function.IntConsumer;
import java.util.concurrent.atomic.AtomicInteger;

class PrintZeroEvenOdd {
	public static void main(String[] args) {
		int n  = Integer.parseInt(args[0]);
		PrintZeroEvenOdd app = new PrintZeroEvenOdd(n);		

		Thread t0 = new Thread(() -> {
			try {
				app.zero((x) -> System.out.print(x));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread t1 = new Thread(() -> {
			try {
				app.even((x) -> System.out.print(x));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				app.odd((x) -> System.out.print(x));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
	}

	private int n;
	private AtomicInteger counter;
	private boolean printZero;

	public PrintZeroEvenOdd(int n) {
		this.n = n;
		counter = new AtomicInteger(1);
		printZero = true; // true to print zero, false to print even or odd.
	}

	// printNumber.accept(x) outputs "x", where x is an integer.
	public void zero(IntConsumer printNumber) throws InterruptedException {
		while(counter.get() <= n) {
			synchronized(counter) { // lock object by using monitor
				if(counter.get() > n) return;
				else {
					if(printZero) {
						printNumber.accept(0);
						printZero = false;
						counter.notifyAll();
					} else {
						counter.wait();
					}
				}
			}
		}
	}

	public void even(IntConsumer printNumber) throws InterruptedException {
		while(counter.get() <= n) {
			synchronized(counter) { // lock object by using monitor
				if(counter.get() > n) return;
				else {
					if(!printZero && counter.get() % 2 == 0) {
						printNumber.accept(counter.getAndIncrement());
						printZero = true;
						counter.notifyAll();
					} else {
						counter.wait();
					}
				}
			}
		}
	}

	public void odd(IntConsumer printNumber) throws InterruptedException {
		while(counter.get() <= n) {
			synchronized(counter) { // acquire lock object by using monitor
				if(counter.get() > n) return;
				else {
					if(!printZero && counter.get() % 2 != 0) {
						printNumber.accept(counter.getAndIncrement());
						printZero = true;
						counter.notifyAll(); // notify all threads are waiting to acquire the lock.
					} else {
						counter.wait(); // wait to acquire the lock.
					}
				}
			}
		}
	}
}
