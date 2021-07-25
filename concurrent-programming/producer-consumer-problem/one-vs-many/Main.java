import java.util.List;
import java.util.ArrayList;

/**
 *
 */
public class Main {
    public static void main(String[] args) {
        int count = 2; // Number of generating data.
        int MAX_SIZE = 2;
        long REQUEST_TIME = 1000; // ms
        long GENERATE_TIME = 400; // ms
        long POLL_TIMEOUT = 3000; // ms

        Buffer buff = new Buffer(MAX_SIZE, POLL_TIMEOUT);
        Producer prod = new Producer(GENERATE_TIME, count*2);
        prod.setBuffer(buff);

        Consumer cons1 = new Consumer(REQUEST_TIME, count);
        cons1.setBuffer(buff);
        Consumer cons2 = new Consumer(REQUEST_TIME, count);
        cons2.setBuffer(buff);

        try {
            List<Thread> threads = new ArrayList<>();
            Thread t1 = new Thread(prod);
            Thread t2 = new Thread(cons1);
            Thread t3 = new Thread(cons2);
            threads.add(t1);
            threads.add(t2);
            threads.add(t3);

            for(Thread t: threads)
                t.start();
            for(Thread t: threads)
                t.join();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
