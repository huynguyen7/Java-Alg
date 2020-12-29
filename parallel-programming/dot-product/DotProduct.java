import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class DotProduct {
	private static final Random rand = new Random();
	private static final Scanner scnr = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("VECTOR SIZE: ");
		final int n = scnr.nextInt();

		System.out.print("NUM TASK IN PARALLEL: ");
		final int numTasks = scnr.nextInt();

		scnr.close(); // close stdin stream.

		if(n <= 0 || numTasks <= 0) {
			System.out.println("INVALID INPUT.");
			return;
		}

		double[] v1 = generateRandomVector(n);
		double[] v2 = generateRandomVector(n);

		// Benchmark.
		printTimeInSequential(v1, v2);
		printTimeInParallelTasking(v1, v2, numTasks);
	}

	private static void printTimeInSequential(double[] v1, double[] v2) {
		if(v1.length != v2.length) return;
		
		double dotProduct = 0;
		double startTime = System.nanoTime();

		for(int i = 0; i < v2.length; ++i)
			dotProduct = dotProduct + Math.pow(v1[i], 2) + Math.pow(v2[i], 2);
		dotProduct = Math.sqrt(dotProduct);

		double endTime = System.nanoTime();
		double timeTaken = (endTime - startTime) / 1e6; // milliseconds.
		System.out.printf("Time taken in SEQUENTIAL: %8.3f ms with DOT_PRODUCT = %8.3f\n", timeTaken, dotProduct);
	}

	private static void printTimeInParallelTasking(double[] v1, double[] v2, final int numTasks) {
		if(v1.length != v2.length) return;

		List<CompletableFuture<Double>> taskList = new ArrayList<>();

		double dotProduct = 0;
		double startTime = System.nanoTime();

		final int chunkSize = (numTasks + v1.length) / numTasks;
		for(int i = 0; i < numTasks; ++i) {
			final int startRow = i * chunkSize;
			int tmpEndRow = (i + 1) * chunkSize - 1;
			if(tmpEndRow >= v1.length) tmpEndRow = v1.length - 1;
			final int endRow = tmpEndRow;

			taskList.add(CompletableFuture.supplyAsync(() -> {
				double partialDotProduct = 0;
				for(int j = startRow; j <= endRow; ++j)
					partialDotProduct = partialDotProduct + Math.pow(v1[j], 2) + Math.pow(v2[j], 2);
				return Double.valueOf(partialDotProduct);
			}));
		}

		for(CompletableFuture<Double> futureTask: taskList) {
			try {
				dotProduct += futureTask.get();
			} catch(ExecutionException e) {
				e.printStackTrace();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		dotProduct = Math.sqrt(dotProduct);

		double endTime = System.nanoTime();
		double timeTaken = (endTime - startTime) / 1e6; // milliseconds.
		System.out.printf("Time taken in PARALLEL TASKING: %8.3f ms with DOT_PRODUCT = %8.3f\n", timeTaken, dotProduct);
	}

	private static double[] generateRandomVector(int n) {
		double[] v = new double[n];
		for(int i = 0; i < v.length; ++i)
			v[i] = rand.nextDouble();
		return v;
	}
}
