import java.util.concurrent.RecursiveTask;

public class GetOrderTask extends RecursiveTask<Data> {
	
	private Data data;

	public GetOrderTask(Data data) {
		this.data = data;
	}

	@Override
	protected Data compute() {
		try {
			Thread.sleep(1); // simulation of doing task.
		} catch(InterruptedException e) {
			e.printStackTrace();
		}

		return data;
	}
}
