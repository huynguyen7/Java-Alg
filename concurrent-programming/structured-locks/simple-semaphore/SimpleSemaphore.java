import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * Implementation of semaphore using Mutex and Conditional Variable.
 *
 */
public class SimpleSemaphore {
    private Lock mutex;
    private Condition cond;
    private int value;

    public SimpleSemaphore(int value) {
        mutex = new ReentrantLock(true); // true for fairness.
        cond = mutex.newCondition();
        this.value = value;
    }

    public void semWait() { // Blocking op, decrement value by 1; if value > 0, the wait exit, else, it should wait for post() to increment.
        mutex.lock();
        try {
            while(value <= 0)
                cond.await();
            value -= 1;
        } catch(InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.unlock();
        }
    }

    public void semPost() { // Increment value by 1.
        mutex.lock();
        try {
            value += 1;
            cond.signal();
        } finally {
            mutex.unlock();
        }
    }

    public int getValue() {
        mutex.lock();
        try {
            return value;
        } finally {
            mutex.unlock();
        }
    }
}
