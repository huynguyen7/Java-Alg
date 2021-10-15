import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable {
    private final int name; // Name of philosopher.
    private final int numRounds; // Number of eating rounds.
    private Chopstick leftC;
    private Chopstick rightC;
    private Semaphore sm;

    public Philosopher(int name, int numRounds, Chopstick leftC, Chopstick rightC, Semaphore sm) {
        this.name = name;
        this.numRounds = numRounds;
        this.leftC = leftC;
        this.rightC = rightC;
        this.sm = sm;
    }

    private void eat() {
        System.out.printf("%s eating..\n", toString());
    }

    @Override
    public void run() {
        for(int r = 0; r < numRounds; ++r) { // For each round.
            try {
                sm.acquire(); // Acquire the semaphore lock and decrease the permit.
                // Get the chopsticks.
                leftC.lock();
                rightC.lock();

                // Eat!
                eat();

                // Release the chopsticks.
                leftC.unlock();
                rightC.unlock();
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                sm.release();
            }
        }
    }

    @Override
    public String toString() {
        return String.format("{P%d: %s %s}", name, leftC.getName(), rightC.getName());
    }
}
