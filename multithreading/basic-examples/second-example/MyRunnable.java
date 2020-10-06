public class MyRunnable implements Runnable {
	
	private int num;
	
	public MyRunnable(int num) {
		this.num = num;
	}

	private void printTable() { // non synchronized method.
		for(int i = 0; i < num; ++i)
			System.out.println(i);
		try {
			Thread.sleep(3000); // 3 second
			System.out.println("Woke up.");
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	public void run() {
		printTable();
	}
}
