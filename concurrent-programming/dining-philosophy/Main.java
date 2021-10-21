import java.util.concurrent.Semaphore;
import java.util.List;
import java.util.ArrayList;

/**
 *
 *  @author: Huy Nguyen
 *  Chopsticks will be named from a range of [0,numPhilosophers).
 *  Philosophers will be named from a range of [0,numPhilosophers).
 *
 */ 

public class Main {
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("[Error] Need input args.\n" +
                               "First arg <number of philosophers>\n" + 
                               "Second arg <number of eating rounds>");
            return;
        }

        final int numPhilosophers = Integer.parseInt(args[0]);
        final int numRounds = Integer.parseInt(args[1]); // Number of eating rounds.
        if(numPhilosophers <= 1 || numRounds <= 0) {
            System.out.println("[Error] Invalid input.");
            return;
        }

        // Allow up to numPhilosophers-1 philosophers to access the dining table.
        Semaphore sm = new Semaphore(numPhilosophers-1, true);

        List<Chopstick> chopsticks = new ArrayList<>();
        for(int i = 0; i < numPhilosophers; ++i)
            chopsticks.add(new Chopstick(i));

        List<Thread> philosophers = new ArrayList<>();
        for(int i = 0; i < numPhilosophers; ++i) {
            int leftC = i-1 < 0 ? numPhilosophers+(i-1) : i-1;
            int rightC = i+1 >= numPhilosophers ? i%(numPhilosophers-1) : i+1;
            philosophers.add(new Thread(new Philosopher(
                        i,
                        numRounds,
                        chopsticks.get(leftC),
                        chopsticks.get(rightC),
                        sm)));
        }

        try {
            for(int i = 0; i < numPhilosophers; ++i)
                philosophers.get(i).start();
            for(int i = 0; i < numPhilosophers; ++i)
                philosophers.get(i).join();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
