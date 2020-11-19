public class FirstExample {
	public static void main(String[] args) {
		int[] arr = new int[Integer.parseInt(args[0])];
		for(int i = 0; i < arr.length; ++i)
			arr[i] = i;
		
		
		Thread t1 = new Thread(new MyRunnable(arr));
		Thread t2 = new Thread(new MyRunnable(arr));

		t1.start(); // create a whole new stack.
		t2.start(); // create a whole new stack.
	}
}
