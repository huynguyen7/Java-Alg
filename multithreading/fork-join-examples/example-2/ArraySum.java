import java.util.concurrent.RecursiveTask;

public class ArraySum extends RecursiveTask<Integer> {
	
	private static final int SIZE_THRESHOLD = 10;
	private int[] nums;
	private int from;
	private int to;

	public ArraySum(int[] nums, int from, int to) {
		this.nums = nums;
		this.from = from;
		this.to = to;
	}

	protected Integer compute() {
		if(to - from <= SIZE_THRESHOLD) {
			int sum = 0;
			for(int i = from; i <= to; ++i)
				sum += nums[i];
			return sum;
		} else {
			int mid = (from + to) / 2;
			ArraySum left = new ArraySum(nums, from, mid);
			ArraySum right = new ArraySum(nums, mid + 1, to);
			right.fork();
			
			return left.compute() + right.join();
		}
	}
}
