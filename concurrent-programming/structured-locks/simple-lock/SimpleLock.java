import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Simple Lock implementation using `synchronized` keyword in java for critical section.
 */

public class SimpleLock implements Lock {
    private String lockingThread = ""; // Name of the locking thread / Thread that is acquiring the lock.
    private AtomicBoolean isLocked;

    public SimpleLock() {
        isLocked = new AtomicBoolean(false);
    }

    @Override
    public int lock() { // Blocking op, if successful (the lock was acquired), return 0, else return -1.
        if(isLocked.get()) return -1;

        synchronized(this) { // only one thread can access this critical section at a time.

            while(isLocked.get()) {
                try {
                    wait(); // Put the current thread to sleep and release the lock, wait for a notification from another thread
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }

            isLocked.set(true);
            lockingThread = Thread.currentThread().getName();
            //System.out.printf("[%s] Lock acquired\n", lockingThread);
            return 0;
        }
    }

    @Override
    public int unlock() { // Blocking op, if successful (the lock was released), return 0, else return -1.
        if(!lockingThread.equals(Thread.currentThread().getName()) ||
                !isLocked.get()) return -1;

        synchronized(this) { // only one thread can access this critical section at a time.
            isLocked.set(false);
            //System.out.printf("[%s] Lock Released\n", lockingThread);
            notifyAll(); // Why do we need to use all()? Yes, there would be scenario that we would wake up the thread that is sleeping but does not need to use lock().
            lockingThread = "";
            return 0;
        }
    }
}
