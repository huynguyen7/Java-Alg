public class ThirdExample {
	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		Table table = new Table(num); // only one table.
		
		Thread t1 = new Thread(new MyRunnable1(table));
		Thread t2 = new Thread(new MyRunnable2(table));
		t1.start();
		t2.start();
	}
}
