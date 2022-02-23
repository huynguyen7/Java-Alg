import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Huy Nguyen
 * Atomic Counter for multithreading (shared-memory model).
 * This implementation solves the problem of linearity from the original approach (using single lock).
 * Instead, we can use multi locks and local variables within threads. Then set a joint computation
 * to achieve a global result.
 */

public class AtomicCounter {
    private final int numThreads;
    private final int threshold;
    private static int globalVal;
    private static Lock globalLock;
    private LocalCounter localCounters[];

    public AtomicCounter(int numThreads, int threshold) {
        globalVal = 0;
        this.numThreads = numThreads;
        this.threshold = threshold;

        globalLock = new ReentrantLock();
        localCounters = new LocalCounter[numThreads];

        // Init threads.
        for(int i = 0; i < numThreads; ++i)
            localCounters[i] = new LocalCounter(threshold);
        // Init locks.
    }

    public void increment() {
        globalLock.lock();
        try {
            globalVal += 1;
        } finally {
            globalLock.unlock();
        }
    }

    public void decrement() {
        globalLock.lock();
        try {
            globalVal -= 1;
        } finally {
            globalLock.unlock();
        }
    }

    public void increment(int x) {
        final int chunkSize = (x+numThreads)/numThreads;
        for(int i = 0; i < numThreads; ++i) {
            int start = i*chunkSize;
            int end = (i+1)*chunkSize-1;
            if(end >= x) end = x-1;
            int size = end-start+1;
            localCounters[i].setMode(0);
            localCounters[i].setFreq(size);
            localCounters[i].run();
        }
    }

    public int get() { // Blocking OP, if run, wait for child threads to finish.
        try {
            for(int i = 0; i < numThreads; ++i)
                localCounters[i].join(); // Blocking op.
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        // Make sure to gather all other threads value to our global value.
        for(int i = 0; i < numThreads; ++i) {
            int localVal = localCounters[i].get();
            if(localVal != 0) {
                localCounters[i].resetVal();
                globalLock.lock();
                try {
                    globalVal += localVal;
                } finally {
                    globalLock.unlock();
                }
            }
        }
        
        return globalVal;
    }

    private static class LocalCounter extends Thread {
        private int val;
        private int count;
        private int freq;
        private final int threshold;
        private int mode; // 0 is increment, 1 is decrement

        public void run() {
            if(mode == 0) {
                for(int i = 0; i < freq; ++i)
                    increment();
            } else {
                for(int i = 0; i < freq; ++i)
                    decrement();
            }
        }
        
        public LocalCounter(int threshold) {
            this.threshold = threshold;
            count = 0;
            val = 0;
        }

        public void increment() {
            val += 1;
            count += 1;
            if(count >= threshold)
                jointAdd(); // Add to the global value.
        }

        public void decrement() {
            val -= 1;
            count += 1;
            freq -= 1;
            if(count >= threshold)
                jointAdd(); // Add to the global value.
        }

        public void setMode(int x) {
            mode = x;
        }

        private void jointAdd() {
            globalLock.lock();
            try {
                globalVal += val;
                val = 0;
                count = 0;
            } finally {
                globalLock.unlock();
            }
        }

        public void setFreq(int freq) {
            this.freq = freq;
        }

        public void resetVal() {
            val = 0;
        }

        public int get() {
            return val;
        }
    }

    public int getNumThreads() {
        return numThreads;
    }
}
