import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.Arrays;

// leetcode 1114.
public class PrintInOrder {

	private final Lock lock;
	private Condition condition2;
	private Condition condition3;
	
	volatile int COUNTER = 1;

	public PrintInOrder() {
		lock = new ReentrantLock();
		condition2 = lock.newCondition();
		condition3 = lock.newCondition();
	}

    public void first(Runnable printFirst) throws InterruptedException {
        
        // printFirst.run() outputs "first". Do not change or remove this line.
		lock.lock();
		try {
        	printFirst.run();
			COUNTER++;
			condition2.signal();
		} finally {
			lock.unlock();
		}
    }

    public void second(Runnable printSecond) throws InterruptedException {
        
        // printSecond.run() outputs "second". Do not change or remove this line.
		lock.lock();
		try {
			while(COUNTER != 2)
				condition2.await(); // wait till COUNTER = 2
        	printSecond.run();
			COUNTER++;
			condition3.signal();
		} finally {
			lock.unlock();
		}
    }

    public void third(Runnable printThird) throws InterruptedException {
        
        // printThird.run() outputs "third". Do not change or remove this line.
		lock.lock();
		try {
			while(COUNTER != 3)
				condition3.await();
        	printThird.run();
		} finally {
			lock.unlock();
		}
    }
}
