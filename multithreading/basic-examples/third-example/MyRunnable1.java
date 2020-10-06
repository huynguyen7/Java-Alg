// Synchronized method is used to lock an object for any shared resource.
public class MyRunnable1 implements Runnable {
	private Table table;
	
	public MyRunnable1(Table table) {
		this.table = table;
	}

	public void run() {
		table.printTable();
	}
}
