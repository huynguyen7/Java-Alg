import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Queue;
import java.util.LinkedList;

/**
 *
 * Simple Fairness Lock implementation with FIFO policy.
 *
 */

public class SimpleFairLock implements Lock {
    private String lockingThread = "";
    private AtomicBoolean isLocked;
    private Queue<LockRequest> queue; // FIFO data structure.

    public SimpleFairLock() {
        isLocked = new AtomicBoolean(false);
        queue = new LinkedList<>();
    }

    @Override
    public int lock() { // Blocking op, if successful (the lock was acquired), return 0, else return -1.
        if(isLocked.get()) return -1;

        LockRequest currRequest = new LockRequest();

        // Lock request created.
        synchronized(this) { // Critical section.
            queue.offer(currRequest);
            System.out.printf("[%s] Lock Request created.\n", Thread.currentThread().getName());
        }

        // If locked or not satisfying the policy, request need to wait.
        while(isLocked.get() || queue.peek() != currRequest)
            currRequest.doWait();

        // If it is not locked or the request is appropriate with the policy.
        synchronized(this) { // Critical section.
            isLocked.set(true);
            queue.poll(); // Remove the request from the policy wait line.
            lockingThread = Thread.currentThread().getName();
            //System.out.printf("[%s] Lock acquired\n", lockingThread);
        }

        return 0;
    }

    @Override
    public int unlock() { // Blocking op, if successful (the lock was released), return 0, else return -1.
        if(!lockingThread.equals(Thread.currentThread().getName()) ||
            !isLocked.get()) return -1;

        synchronized(this) { // Critical section.
            isLocked.set(false);
            lockingThread = "";
            //System.out.printf("[%s] Lock released\n", lockingThread);
            if(!queue.isEmpty())
                queue.peek().doNotify();
        }

        return 0;
    }

    /*
     * Lock Request object skeleton.
     */
    private class LockRequest {
        private boolean alreadyNotified;
        public LockRequest() {
            alreadyNotified = false;
        }

        public synchronized void doWait() {
            while(!alreadyNotified) {
                try {
                    wait();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void doNotify() {
            alreadyNotified = true;
            notify(); // Notify the current thread holding its LockRequest object. No need to notifyAll()
        }
    }
}
