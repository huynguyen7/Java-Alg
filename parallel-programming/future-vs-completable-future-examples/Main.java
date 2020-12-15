import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinTask;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static final int TASK_EXECUTION_TIME = 1; // milliseconds.

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);

		System.out.print("NUM ORDERS = ");
		final int numOrders = scnr.nextInt();

		printTimeInSequential(numOrders);
		printTimeInParallelFuture(numOrders);
		printTimeInParallelCompletableFuture(numOrders);
		printTimeInParallelCompletableFutureAsync(numOrders);
	}

	private static void printTimeInSequential(final int numOrders) {
		double startTime = System.nanoTime();
		
		for(int i = 0; i < numOrders; ++i) {
			Data data = new Data("Order_Data");

			// get order.
			data = simulationOfDoingTask(data);

			// access payment.
			data = simulationOfDoingTask(data);

			// ship order.
			simulationOfDoingTask(data);
		}

		double endTime = System.nanoTime();
		double timeTaken = (endTime - startTime) / 1e6; // milliseconds.
		System.out.printf("TIME TAKEN IN SEQUENTIAL = %10.3f ms.\n", timeTaken);
	}

	private static void printTimeInParallelFuture(final int numOrders) {
		ForkJoinPool pool = ForkJoinPool.commonPool();
		ForkJoinTask<Data> task1 = null, task2 = null;

		List<ForkJoinTask<Data>> task3List = new ArrayList<>();
		double startTime = System.nanoTime();
		for(int i = 0; i < numOrders; ++i) {
			Data data = new Data("Order_Data");
			
			try {
				// get order.
				task1 = pool.submit(new GetOrderTask(data));
				data = task1.get(); // blocking operation.

				// access payment.
				task2 = pool.submit(new AccessPaymentTask(data));
				data = task2.get(); // blocking operation.

				// ship order.
				task3List.add(pool.submit(new ShipOrderTask(data)));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		try {
			for(ForkJoinTask<Data> task3: task3List)
				task3.get(); // wait for task 3 to finish -> MORE ACCURATE FOR TIME MEASUREMENT.
		} catch(Exception e) {
			e.printStackTrace();
		}

		double endTime = System.nanoTime();
		double timeTaken = (endTime - startTime) / 1e6; // milliseconds.
		System.out.printf("TIME TAKEN IN PARALLEL FUTURE = %10.3f ms.\n", timeTaken);
	}

	private static void printTimeInParallelCompletableFuture(final int numOrders) {
		List<CompletableFuture<Data>> rs = new ArrayList<>();

		double startTime = System.nanoTime();
		
		for(int i = 0; i < numOrders; ++i) {
			rs.add(CompletableFuture.supplyAsync(() -> simulationOfDoingTask(new Data("Order_Data"))) // get order
				.thenApply(data -> simulationOfDoingTask(data)) // access payment.
				.thenApply(data -> simulationOfDoingTask(data))); // ship order.
		}

		try {
			for(CompletableFuture<Data> c: rs)
				c.get(); // wait till the completable tasks finished -> MORE ACCURATE FOR TIME MEASURING.
		} catch(Exception e) {
			e.printStackTrace();
		}

		double endTime = System.nanoTime();
		double timeTaken = (endTime - startTime) / 1e6; // milliseconds.
		System.out.printf("TIME TAKEN IN PARALLEL COMPLETABLE-FUTURE = %10.3f ms.\n", timeTaken);
	}

	private static void printTimeInParallelCompletableFutureAsync(final int numOrders) {
		List<CompletableFuture<Data>> rs = new ArrayList<>();

		double startTime = System.nanoTime();
		
		for(int i = 0; i < numOrders; ++i) {
			rs.add(CompletableFuture.supplyAsync(() -> simulationOfDoingTask(new Data("Order_Data"))) // get order
				.thenApplyAsync(data -> simulationOfDoingTask(data)) // access payment.
				.thenApplyAsync(data -> simulationOfDoingTask(data))); // ship order.
		}

		try {
			for(CompletableFuture<Data> c: rs)
				c.get(); // wait till the completable tasks finished -> MORE ACCURATE FOR TIME MEASURING.
		} catch(Exception e) {
			e.printStackTrace();
		}

		double endTime = System.nanoTime();
		double timeTaken = (endTime - startTime) / 1e6; // milliseconds.
		System.out.printf("TIME TAKEN IN PARALLEL COMPLETABLE-FUTURE ASYNC = %10.3f ms.\n", timeTaken);
	}

	// This is used for SEQUENTIAL and CompletableFuture version.
	private static Data simulationOfDoingTask(Data data) {
		try {
			Thread.sleep(TASK_EXECUTION_TIME);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}

		return data;
	}
}
