import java.util.concurrent.Semaphore;

/**
 * A simple implementation of barrier using semaphores.
 */

public class SimpleBarrier {
    private Semaphore sem;
    private int count;
    private int numParties;

    public SimpleBarrier(int numParties) { // Number of parties as input to the struct
        sem = new Semaphore(0);
        count = 0;
        this.numParties = numParties;
    }

    // Wait for other parties.
    public void await() {
        // Every party reach this point needs to increment the count atomically.
        synchronized(this) { // Critical section.
            count += 1;
        }

        if(count < numParties) {
            try {
                sem.acquire();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        sem.release();
    }

    // Return the number of parties have reached the barrier.
    public int getParties() {
        return count;
    }
}
