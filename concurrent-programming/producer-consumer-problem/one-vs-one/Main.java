/**
 *
 */
public class Main {
    public static void main(String[] args) {
        int count = 4; // Number of generating data.
        int MAX_SIZE = 2;
        long REQUEST_TIME = 1000; // ms
        long GENERATE_TIME = 2000; // ms

        Buffer buff = new Buffer(MAX_SIZE);
        Producer prod = new Producer(GENERATE_TIME, count);
        prod.setBuffer(buff);
        Consumer cons = new Consumer(REQUEST_TIME, count);
        cons.setBuffer(buff);

        try {
            Thread t1 = new Thread(prod);
            Thread t2 = new Thread(cons);

            t1.start();
            t2.start();
            t1.join();
            t2.join();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
