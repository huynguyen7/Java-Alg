import java.util.concurrent.Callable;

public class Task implements Callable<Integer> {
	
	private int number;

	public Task(int number) {
		this.number = number;
	}

	public Integer call() throws Exception {
		Thread.sleep(500);
		int factorial = 1;
		for(int count = number; count > 1; count--)
			factorial = factorial * count;
		return factorial;
	}
}
