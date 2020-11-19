// Synchronized method is used to lock an object for any shared resource.
public class MyRunnable2 implements Runnable {
	private Table table;
	
	public MyRunnable2(Table table) {
		this.table = table;
	}

	public void run() {
		table.printTable();
	}
}
