public class Test {
    public static void main(String[] args) {
        SimpleBarrier barrier = new SimpleBarrier(2);

        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch(Exception e) {
                e.printStackTrace();
            }

            System.out.println("[1] Hello");
            barrier.await();
            System.out.println("[1] Goodbye");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("[2] Hello");
            barrier.await();
            System.out.println("[2] Goodbye");
        });

        try {
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
