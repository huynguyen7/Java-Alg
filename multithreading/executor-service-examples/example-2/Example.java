import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;

// Great example for FixedThreadPool with Callable.
public class Example {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// Get num cores
		int numCores = Runtime.getRuntime().availableProcessors();

		ExecutorService service = Executors.newFixedThreadPool(numCores);
		
		int from = 1, to = 10;
		List<Integer> factorials = getPrecalculatedFactorial(from, to);
		
		int i = 0;

		// This code will run sequentially if we use future.get()
		while(from <= to) {
			Future<Integer> future = service.submit(new Task(from));
			
			// This get() execution will wait until the thread
			// finished it call() method, then continue
			Integer rs = future.get(); // blocking operation

			System.out.printf("%d == %d : %b\n", factorials.get(i), rs, Integer.compare(factorials.get(i), rs) == 0);
			from++;
			i++;
		}

		System.out.println();

		// This code will run in parallelism
		// since we don't use future.get() method
		from = 1;
		List<Future<Integer>> list = new ArrayList<>();
		while(from <= to) {
			list.add(service.submit(new Task(from)));
			from++;
		}
		
		for(i = 0; i < list.size(); ++i) {
			Future<Integer> future = list.get(i);
			Integer rs = future.get();
			System.out.printf("%d == %d : %b\n", factorials.get(i), rs, Integer.compare(factorials.get(i), rs) == 0);
		}

		service.shutdown();
		System.out.println();
	}
	
	private static List<Integer> getPrecalculatedFactorial(int from, int to) {
		List<Integer> rs = new ArrayList<>();
		if(from > to) return rs;
		while(from <= to)
			rs.add(factorial(from++));

		return rs;
	}

	public static int factorial(int num) {
		int rs = 1;
		for(int i = 1; i <= num; ++i)
			rs *= i;
		return rs;
	}
}
