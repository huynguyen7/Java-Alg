public class Test {
    public static void main(String[] args) {
        // Test 1, single-thread using a sinle-thread counter.
        AtomicCounter a1 = new AtomicCounter(1, 1000);
        a1.increment(10);
        a1.increment(12);
        assert(a1.get() == 22);

        // Test 2, single-thread using 1 thread on counter that over the threshold.
        AtomicCounter a2 = new AtomicCounter(1, 5);
        a2.increment(10);
        a2.increment(12);
        assert(a2.get() == 22);

        // Test 3, similar to test 1, but lets do a large amount of increment
        AtomicCounter a3 = new AtomicCounter(1, 100000);
        a3.increment(10000);
        a3.increment(102);
        assert(a3.get() == 10102);
        
        // Test 4, similar to test 2, but lets do a large amount of increment
        AtomicCounter a4 = new AtomicCounter(1, 500);
        a4.increment(1000000);
        a4.increment(102);
        assert(a4.get() == 1000102);

        // Test 5, single thread using 4 thread counter that does not exceed the threshold.
        AtomicCounter a5 = new AtomicCounter(4, 50000000);
        a5.increment(100000);
        a5.increment(100000);
        a5.increment(102);
        a5.increment(500000);
        assert(a5.get() == 700102);

        // Test 6, multi threads using single thread counter
        AtomicCounter a6 = new AtomicCounter(1, 50000000);
        Thread t1 = new Thread(() -> {
            a6.increment(100000);
            a6.increment(102);
        });
        Thread t2 = new Thread(() -> {
            a6.increment(100000);
            a6.increment(500000);
        });

        try {
            t1.run();
            t2.run();
            t1.join();
            t2.join();
            assert(a6.get() == 700102);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        // Test 7, multi threads using single thread counter exceeds the threshold
        AtomicCounter a7 = new AtomicCounter(1, 10000);
        Thread t3 = new Thread(() -> {
            a7.increment(100000);
            a7.increment(102);
        });
        Thread t4 = new Thread(() -> {
            a7.increment(100000);
            a7.increment(500000);
        });

        try {
            t3.run();
            t4.run();
            t3.join();
            t4.join();
            assert(a7.get() == 700102);
        } catch(Exception e) {
            e.printStackTrace();
        }

        // Test 8, multi threads using 3-threaded-counter exceeds the threshold
        AtomicCounter a8 = new AtomicCounter(3, 1000);
        Thread t5 = new Thread(() -> {
            a8.increment(100000);
            a8.increment(102);
        });
        Thread t6 = new Thread(() -> {
            a8.increment(100000);
            a8.increment(500000);
        });

        try {
            t5.run();
            t6.run();
            t5.join();
            t6.join();
            assert(a8.get() == 700102);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
