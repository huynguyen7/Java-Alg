import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Arrays;
import java.util.stream.*;
import java.util.function.IntBinaryOperator;


/*
	This example explains why volatile cannot be used for READ-WRITE or WRITE-WRITE operations, so use synchronized (critical sections) or atomic variable instead.
	=> Using array sum problem.
**/

public class Example {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]); // Use large integer as input.
		int[] nums = generateIntArr(n);
		int sum = getArrSum(nums);

		// Split array to half and do calculations on each half on each thread.
		
		ExecutorService service = Executors.newFixedThreadPool(8); // 8 hyper threads ~ OS threads.
		Callable volatileExample = new VolatileExample(nums);
		Callable synchronizeExample = new SynchronizeExample(nums);
		Callable atomicExample = new AtomicExample(nums);

		// Start tasks.
		Future<Integer> volatileFuture = service.submit(volatileExample);
		Future<Integer> synchronizeFuture = service.submit(synchronizeExample);
		Future<Integer> atomicFuture = service.submit(atomicExample);

		System.out.println("----ShowResults----");
		System.out.printf("SUM = %d\n", sum);
		try {
			int rs1 = volatileFuture.get();
			int rs2 = synchronizeFuture.get();
			int rs3 = atomicFuture.get();
			System.out.printf("VOLATILE CALC = %d\n", rs1);
			System.out.printf("SYNCHRONIZED CALC = %d\n", rs2);
			System.out.printf("ATOMIC CALC = %d\n\n", rs3);
		} catch(InterruptedException e) {
			e.printStackTrace();
		} catch(ExecutionException e) {
			e.printStackTrace();
		}

		service.shutdown(); // terminate the pool.
	}

	private static class VolatileExample implements Callable<Integer> {
		private volatile int sum = 0;
		private int[] nums;
		
		public VolatileExample(int[] nums) {
			this.nums = nums;
		}

		@Override
		public Integer call() {
			int mid = nums.length / 2;
			Thread t = new Thread(() -> {
				for(int i = 0; i <= mid; ++i)
					sum += nums[i];
			});
			t.start();
			for(int i = mid + 1; i < nums.length; ++i)
				sum += nums[i];

			try {
				t.join();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}

			return Integer.valueOf(sum);
		}
	}

	private static class SynchronizeExample implements Callable<Integer> {
		private int sum = 0;
		private int[] nums;

		public SynchronizeExample(int[] nums) {
			this.nums = nums;
		}

		@Override
		public Integer call() {
			int mid = nums.length / 2;
			Thread t = new Thread(() -> {
				for(int i = 0; i <= mid; ++i) {
					synchronized(this) {
						sum +=  nums[i];
					}
				}
			});
			t.start();
			for(int i = mid + 1; i < nums.length; ++i) {
				synchronized(this) {
					sum +=  nums[i];
				}
			}

			try {
				t.join();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			return Integer.valueOf(sum);
		}
	}

	private static class AtomicExample implements Callable<Integer> {
		private AtomicInteger sum;
		private int[] nums;
		private IntBinaryOperator operation;
		
		public AtomicExample(int[] nums) {
			this.nums = nums;
			sum  = new AtomicInteger(0); // init value 0.
			operation = (x, y) -> x + y; // lambda expression to add the value to current sum
		}

		@Override
		public Integer call() {
			int mid = nums.length / 2;
			Thread t = new Thread(() -> {
				for(int i = 0; i <= mid; ++i)
					sum.accumulateAndGet(nums[i], operation);
			});
			t.start();
			for(int i = mid + 1; i < nums.length; ++i)
				sum.accumulateAndGet(nums[i], operation);

			try {
				t.join();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			return sum.get();
		}
	}

	private static int[] generateIntArr(int n) {
		int[] nums = new int[n];
		Arrays.fill(nums, 1); // all elements are 1.
		return nums;
	}

	private static int getArrSum(int[] nums) {
		int sum = Arrays.stream(nums)
					.parallel()
					.sum();

		return sum;
	}
}
