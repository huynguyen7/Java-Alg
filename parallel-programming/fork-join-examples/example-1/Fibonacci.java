import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Integer> {

    private int n;
    public Fibonacci(int n) {
        this.n = n;
    }

    @Override
	protected Integer compute() {
		return fib(n);
	}

    private int fib(int n) {
        if(n <= 1) return n;
        else if(n > 10 && getSurplusQueuedTaskCount() < 2) {
			Fibonacci f1 = new Fibonacci(n - 1);
			f1.fork(); // add new sub task to thread's deque.
			Fibonacci f2 = new Fibonacci(n - 2);
			//f2.fork(); // add new sub task to thread's deque.

			// join() waits for those two sub tasks to finish.
			return f2.compute() + f1.join();
        } else return fib(n - 1) + fib(n - 2);
    }
}

