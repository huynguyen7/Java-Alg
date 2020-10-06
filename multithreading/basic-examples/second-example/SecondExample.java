// In this example, there is no synchronization, so output is inconsistent.
public class SecondExample {
	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);

		Thread t1 = new Thread(new MyRunnable(num));
		Thread t2 = new Thread(new MyRunnable(num));
		t1.start();
		t2.start();
	}
}
