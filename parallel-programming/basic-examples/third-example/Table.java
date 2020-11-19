public class Table {
	
	private int num;
	
	public Table(int num) {
		this.num = num;
	}

	// Synchronized method is used to lock an object for any shared resource.
	public synchronized void printTable() { // synchronized method.
		for(int i = 1; i <= num; ++i)
			System.out.println(i);
		
		try {
			Thread.sleep(3000); // 3 seconds
			System.out.println("Woke up.");
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
