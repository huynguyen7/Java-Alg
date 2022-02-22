import java.util.concurrent.locks.*;

public class Hello {
    static final Lock lock = new ReentrantLock();
    static int done = 0;
    static final Condition notDone = lock.newCondition(); 

    public static void main(String[] args) {
        System.out.println("[Parent] Hello");
        Runnable child = new Child();
        try {
            child.run(); // Run the child thread.

            lock.lock();
            // Wait for child. Does not necessarily use join()
            try {
                while(done == 0)
                    notDone.await();
            } finally {
                lock.unlock();
            }

            System.out.println("[Parent] End");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    static class Child implements Runnable {
        public void run() {
            lock.lock();
            try {
                System.out.println("[Child]  Hello");
                done = 1;
                notDone.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
