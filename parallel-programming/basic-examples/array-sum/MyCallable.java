import java.util.concurrent.*;

public class MyCallable implements Callable<Double> {
	private double sum;
	private int startCol;
	private int endCol;
	private double[] nums;

	public MyCallable(int startCol, int endCol, double[] nums) {
		this.startCol = startCol;
		this.endCol = endCol;
		this.nums = nums;
	}

	public Double call() throws Exception{
		for(int i = startCol; i < endCol; ++i)
			sum += nums[i];
		return Double.valueOf(sum);
	}
}
