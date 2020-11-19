import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// This code will gives all the task 
// for defined number of cores to execute.

// Basic example, just show how to use ExecutorService with ThreadsPool.
public class Example {
	public static void main(String[] args) {
		// Get num of cores
		int numCores = Runtime.getRuntime().availableProcessors();

		ExecutorService service = Executors.newFixedThreadPool(numCores);
		
		// submit the tasks for execution
		for(int i = 0; i < 10; ++i)
			service.execute(new Task());
		System.out.println("Main Thread at: " + Thread.currentThread().getName());

		// If not shut down, the program will not close
		service.shutdown(); // allow previously submitted tasks to execute before terminating 
		// service.shutdownNow() // prevents waiting tasks from starting and attempts to stop currently executing tasks.
	}
}
