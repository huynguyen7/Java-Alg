import java.util.List;
import java.util.ArrayList;

/**
 *
 */
public class Main {
    public static void main(String[] args) {
        int MAX_SIZE = 2;
        long REQUEST_TIME = 500; // ms
        long GENERATE_TIME = 400; // ms
        long ADD_TIMEOUT = 3000; // ms
        long POLL_TIMEOUT = 3000; // ms

        Producer prod1 = new Producer(GENERATE_TIME, 4);
        Producer prod2 = new Producer(GENERATE_TIME, 4);
        Consumer cons1 = new Consumer(REQUEST_TIME, 2);
        Consumer cons2 = new Consumer(REQUEST_TIME, 2);
        Consumer cons3 = new Consumer(REQUEST_TIME, 2);
        Consumer cons4 = new Consumer(REQUEST_TIME, 2);

        // Set buffer.
        Buffer buff = new Buffer(MAX_SIZE, ADD_TIMEOUT, POLL_TIMEOUT);
        prod1.setBuffer(buff);
        prod2.setBuffer(buff);
        cons1.setBuffer(buff);
        cons2.setBuffer(buff);
        cons3.setBuffer(buff);
        cons4.setBuffer(buff);

        try {
            List<Thread> threads = new ArrayList<>();
            Thread t1 = new Thread(prod1);
            Thread t2 = new Thread(prod2);
            Thread t3 = new Thread(cons1);
            Thread t4 = new Thread(cons2);
            Thread t5 = new Thread(cons3);
            Thread t6 = new Thread(cons4);
            threads.add(t1);
            threads.add(t2);
            threads.add(t3);
            threads.add(t4);
            threads.add(t5);
            threads.add(t6);

            for(Thread t: threads)
                t.start();
            for(Thread t: threads)
                t.join();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
