import java.util.concurrent.RecursiveTask;

public class AccessPaymentTask extends RecursiveTask<Data> {
	
	private Data data;

	public AccessPaymentTask(Data data) {
		this.data = data;
	}

	@Override
	protected Data compute() {
		try {
			Thread.sleep(20); // simulation of doing task.
		} catch(InterruptedException e) {
			e.printStackTrace();
		}

		return data;
	}
}
