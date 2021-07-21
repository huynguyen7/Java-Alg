public class Consumer implements Runnable {
    private static long REQUEST_TIME; // Just for simulating purpose.
    private int count;
    private Buffer buff;

    private Consumer() {}
    public Consumer(long requestTime, int count) {
        REQUEST_TIME = requestTime;
        this.count = count;
    }

    public void setBuffer(Buffer buff) {
        this.buff = buff;
    }

    @Override
    public void run() {
        try {
            while(count-- != 0) {
                Thread.sleep(REQUEST_TIME); // Just for simulating purpose.
                // Get data.
                int data = buff.poll();
                System.out.printf("[Consumer] Received: %d\n", data);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
