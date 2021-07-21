import java.util.concurrent.locks.Condition;
import java.util.Random;

public class Producer implements Runnable {
    private static long GENERATE_TIME; // Just for simulating purpose.
    private int count; // Number of generating data.
    private Buffer buff;
    private Random rand;

    private Producer() {}
    public Producer(long generateTime, int count) {
        GENERATE_TIME = generateTime;
        this.count = count;
        rand = new Random();
    }

    public void setBuffer(Buffer buff) {
        this.buff = buff;
    }

    private int generateData() {
        return rand.nextInt(10); // Uniform int.
    }
 
    @Override
    public void run() {
        while(count-- != 0) {
            try {
                Thread.sleep(GENERATE_TIME); // Just for simulating purpose.

                // Generate data.
                int data = generateData();

                // Add into the buffer.
                buff.add(data);
                System.out.printf("[Producer] Added: %d\n", data);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
