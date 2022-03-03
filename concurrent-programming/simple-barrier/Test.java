import java.util.Random;

public class Test {
    public static void main(String[] args) {
        final int numThreads = Integer.parseInt(args[0]);
        Random rand = new Random();
        SimpleBarrier barrier = new SimpleBarrier(numThreads);
        
        Thread[] threads = new Thread[numThreads];

        for(int i = 0; i < numThreads; ++i) {
            final int index = i;
            Thread t = new Thread(() -> {
                try {
                    Thread.sleep(rand.nextInt(1000)+500);
                } catch(Exception e) {
                    e.printStackTrace();
                }

                System.out.printf("[%d] Hello\n", index);
                barrier.await();
                System.out.printf("[%d] Goodbye\n", index);
            });
            threads[i] = t;;
        }

        for(int i = 0; i < numThreads; ++i) {
            Thread t = threads[i];
            t.start();
        }

        for(int i = 0; i < numThreads; ++i) {
            Thread t = threads[i];
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
