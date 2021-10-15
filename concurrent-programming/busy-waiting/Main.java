/**
 * This program demonstrate how a busy-waiting works.
 * A very primitive way in concurrent programming.
 * However, this approach cost a lot of instructions for cyclical checking the flag.
 * Instead, another technique called `mutex` has been created.
 */

public class Main {
    private static int flag; // Global variable for thread accessing.
    public static void main(String[] args) {
        flag = 0; // Busy-waiting flag, matching with thread ids.
        int numThreads = Integer.parseInt(args[0]);

        Thread[] t = new Thread[numThreads];

        for(int i = 0; i < numThreads; ++i) {
            final int id = i;
            t[i] = new Thread(() -> {
                while(id != flag);
                flag += 1;
                System.out.printf("[%d] Hello\n", id);
            });
            t[i].start();
        }

        try {

            for(int i = 0; i < numThreads; ++i)
                t[i].join();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
