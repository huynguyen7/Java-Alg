import java.util.List;
import java.util.ArrayList;

/**
 *
 */
public class Main {
    public static void main(String[] args) {
        int count = 2; // Number of generating data.
        int MAX_SIZE = 2;
        long REQUEST_TIME = 500; // ms
        long GENERATE_TIME = 1200; // ms
        long ADD_TIMEOUT = 3000; // ms

        Producer prod1 = new Producer(GENERATE_TIME, count);
        Producer prod2 = new Producer(GENERATE_TIME, count);
        Consumer cons = new Consumer(REQUEST_TIME, count*2);
        
        // Set buffer.
        Buffer buff = new Buffer(MAX_SIZE, ADD_TIMEOUT);
        prod1.setBuffer(buff);
        prod2.setBuffer(buff);
        cons.setBuffer(buff);

        try {
            List<Thread> threads = new ArrayList<>();
            Thread t1 = new Thread(prod1);
            Thread t2 = new Thread(prod2);
            Thread t3 = new Thread(cons);
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
