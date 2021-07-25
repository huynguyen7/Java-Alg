import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.TimeUnit;

// Integer buffer, just for simplicity.
public class Buffer {
    private final int MAX_SIZE;
    private Queue<Integer> queue;
    public Lock lock;
    public Condition isFull;
    public Condition isEmpty;
    private long POLL_TIMEOUT;
    private long ADD_TIMEOUT;

    private Buffer() {
        MAX_SIZE = 0;
    }

    public Buffer(int maxSize, long pollTimeout, long addTimeout) {
        MAX_SIZE = maxSize;
        POLL_TIMEOUT = pollTimeout;
        ADD_TIMEOUT = addTimeout;
        queue = new LinkedList<>();
        lock = new ReentrantLock();
        isFull = lock.newCondition();
        isEmpty = lock.newCondition();
    }

    public void add(int item) throws InterruptedException {
        lock.tryLock(ADD_TIMEOUT, TimeUnit.MILLISECONDS);
        try {
            while(getSize() >= getMaxSize())
                isFull.await();
            queue.add(item);

            // Signal consumer.
            isEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int poll() throws InterruptedException {
        lock.tryLock(POLL_TIMEOUT, TimeUnit.MILLISECONDS);
        int data;
        try {
            while(getSize() == 0)
                isEmpty.await();
            data = queue.poll();
            
            // Signal producer.
            if(getSize() < getMaxSize())
                isFull.signal();
        } finally {
            lock.unlock();
        }

        return data;
    }

    public int getSize() {
        return queue.size();
    }

    public int getMaxSize() {
        return MAX_SIZE;
    }
}
