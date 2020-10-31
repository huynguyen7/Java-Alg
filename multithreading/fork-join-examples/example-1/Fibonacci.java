import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Integer> {

    private int n;
    public Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if(n <= 1) return n;
        else {
            Fibonacci f1 = new Fibonacci(n - 1);
            f1.fork(); // add new sub task to thread's deque.
            Fibonacci f2 = new Fibonacci(n - 2);
            f2.fork(); // add new sub task to thread's deque.

			// join() waits for those two sub tasks to finish.
            return f1.join() + f2.join();
        }
    }
}

