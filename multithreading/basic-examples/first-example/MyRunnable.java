public class MyRunnable implements Runnable {
	
	private int[] arr;
	
	public MyRunnable(int[] arr) {
		this.arr = arr;
	}
	
	public void run() {
		try {
			for(int i = 0; i < arr.length; ++i)
				System.out.println(arr[i]);
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
