import java.util.concurrent.*;
import java.util.concurrent.locks.*;

// leetcode 1115.
class PrintFooBarAlternately {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		
		PrintFooBarAlternately foobar = new PrintFooBarAlternately(n);
		Thread t0 = new Thread(
			() -> {
				try {
					foobar.foo(() -> {
						System.out.print("foo");
					});
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		);
		Thread t1 = new Thread(
			() -> {
				try {
					foobar.bar(() -> {
						System.out.print("bar\n");
					});
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		);

		t0.start();
		t1.start();
	}

    private int n;
	private Phaser ph; // Using phaser as barrier to check if already printed 'foobar' (to advance to next stage).
    private Lock lock; // Using lock to get the order of printing 'foo' and 'bar'.
    private Condition conditionFoo;
    private Condition conditionBar;
    volatile int COUNTER = 0;

    public PrintFooBarAlternately(int n) {
        this.n = n;
        ph = new Phaser(2); // one task for foo(), another for bar()
        lock = new ReentrantLock();
        conditionFoo = lock.newCondition();
        conditionBar = lock.newCondition();
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            
        	// printFoo.run() outputs "foo". Do not change or remove this line.
            lock.lock();
            try {
                while(COUNTER != 0)
                    conditionFoo.await();
                
        	    printFoo.run();

                COUNTER = (COUNTER + 1) % 2;
                conditionBar.signal();
            } finally {
                lock.unlock();
            }
            ph.arriveAndAwaitAdvance();
        }
        
        ph.arriveAndDeregister();
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            
            // printBar.run() outputs "bar". Do not change or remove this line.
            lock.lock();
            try {
                while(COUNTER != 1)
                    conditionBar.await();
                
        	    printBar.run();
                
                COUNTER = (COUNTER + 1) % 2;
                conditionFoo.signal();
            } finally {
                lock.unlock();
            }
            ph.arriveAndAwaitAdvance();
        }
        
        ph.arriveAndDeregister();
    }
}
