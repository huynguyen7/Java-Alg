public class Task implements Runnable{
	public void run() {
		System.out.println("Running at thread: " + Thread.currentThread().getName());
	}
}
