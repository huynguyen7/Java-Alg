public class Example {
	public static void main(String[] args) {
		MyThread t0 = new MyThread();
		MyThread t1 = new MyThread();

		t0.that = t1; // try to comment this line.
		t1.that = t0; // try to comment this line.

		t0.start();
		t1.start();
	}

	public static class MyThread extends Thread {
		public Thread that;
		
		public MyThread() {
			this.that = null;
		}

		@Override
		public void run() {
			try {
				if(that != null)
					that.join();
				doWork();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		private void doWork() {
			System.out.println("FINISHED TASK.");
		}
	}
}
