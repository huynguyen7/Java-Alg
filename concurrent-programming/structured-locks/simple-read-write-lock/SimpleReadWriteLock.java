import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * Simple Read-Write Lock implementation.
 * - Multiple READs at the same time.
 * - One WRITE at a time.
 * - WRITE and READ cannot operate at the same time. Remember to release all READs to use WRITE and vice versa.
 * - No FAIRNESS in this implementation (However, you can use the fair lock implementation in another folder)!
 *
 */

public class SimpleReadWriteLock {
    private int numReaders;
    private Lock lock;
    private Lock writeLock;

    public SimpleReadWriteLock() {
        numReaders = 0;
        lock = new ReentrantLock(); // Just for modifying numReaders value.
        writeLock = new ReentrantLock(); // Binary lock, make sure there is only ONE WRITE op.
    }

    public void readAcquire() {
        lock.lock();
        try {
            numReaders += 1;
            if(numReaders == 1) // Simply, the first reader will hold both write lock and the shared lock.
                writeLock.lock();
        } finally {
            lock.unlock();
        }
    }

    public void readRelease() {
        lock.lock();
        try {
            numReaders -= 1;
            if(numReaders == 0)
                writeLock.unlock();
        } finally {
            lock.unlock();
        }
    }

    public void writeAcquire() {
        writeLock.lock();
    }

    public void writeRelease() {
        writeLock.unlock();
    }

    public int getNumReaders() {
        lock.lock();
        try {
            return numReaders;
        } finally {
            lock.unlock();
        }
    }
}
