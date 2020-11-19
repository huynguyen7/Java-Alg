// this implementation use lambda expression.
public class ThirdExample2 {
	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		Table table = new Table(num); // only one table object.
		
		Thread t1 = new Thread(() -> table.printTable()); // implement runable nterface
		Thread t2 = new Thread(() -> table.printTable());
		t1.start();
		t2.start();
	}
}
