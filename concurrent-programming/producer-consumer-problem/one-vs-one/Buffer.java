import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

// Integer buffer, just for simplicity.
public class Buffer {
    private final int MAX_SIZE;
    private Queue<Integer> queue;
    public Lock lock;
    public Condition isFull;
    public Condition isEmpty;

    private Buffer() {
        MAX_SIZE = 0;
    }

    public Buffer(int maxSize) {
        MAX_SIZE = maxSize;
        queue = new LinkedList<>();
        lock = new ReentrantLock();
        isFull = lock.newCondition();
        isEmpty = lock.newCondition();
    }

    public void add(int item) throws InterruptedException {
        lock.tryLock();
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
        lock.tryLock();
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
