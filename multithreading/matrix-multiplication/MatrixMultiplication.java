import java.util.Arrays;
import java.util.Random;
import java.util.stream.*;
import java.util.concurrent.*;

public class  MatrixMultiplication {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[][] matrix1 = generateRandom(n);
		int[][] matrix2 = generateRandom(n);

		matrixMultSequential(matrix1, matrix2);
		matrixMultParallel(matrix1, matrix2);
		matrixMultParallelChunking(matrix1, matrix2);
		System.out.println();
	}

	public static void matrixMultSequential(int[][] matrix1, int[][] matrix2) {
		System.out.println("--------");
		double startTime = System.nanoTime();

		int n = matrix1.length;
		int[][] rs = new int[n][n];
		for(int i = 0; i < n; ++i) {
			for(int j = 0; j < n; ++j) {
				rs[i][j] = 0;
				for(int k = 0; k < n; ++k)
					rs[i][j] += matrix1[i][k] * matrix2[k][j];
			}
		}

		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;
		System.out.println("Time taken in SEQUENTIAL: " + (timeTaken / 1e9) + " seconds.");
		//for(int[] row: rs)
			//System.out.println(Arrays.toString(row));
	}

	public static void matrixMultParallel(int[][] matrix1, int[][] matrix2) {
		System.out.println("--------");
		double startTime = System.nanoTime();
		
		int n = matrix1.length;
		int[][] rs = new int[n][n];
		PCDP.forall2d(0, n - 1, 0, n - 1, (i, j) -> { // double parallel.for loops
			rs[i][j] = 0;
			for(int k = 0; k < n; ++k) // cannot parallelize this for loop.
				rs[i][j] += matrix1[i][k] * matrix2[k][j];
		});

		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;
		System.out.println("Time taken in PARALLEL: " + (timeTaken / 1e9) + " seconds.");

		//for(int[] row: rs)
			//System.out.println(Arrays.toString(row));
	}

	private static int[][] generateRandom(int n) {
		int[][] matrix = new int[n][n];
		Random rand = new Random();
		for(int i = 0; i < n; ++i) {
			for(int j = 0; j < n; ++j)
				matrix[i][j] = rand.nextInt(5);
		}

		return matrix;
	}

	public static void matrixMultParallelChunking(int[][] matrix1, int[][] matrix2) {
		System.out.println("--------");
		double startTime = System.nanoTime();
		
		int n = matrix1.length;
		int[][] rs = new int[n][n];
		final int CHUNKSIZE = 50; // change this value

		PCDP.forall2dChunked(0, n - 1, 0, n - 1, CHUNKSIZE, (i, j) -> {
			rs[i][j] = 0;
			for(int k = 0; k < n; ++k)
				rs[i][j] += matrix1[i][k] * matrix2[k][j];
		});

		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;
		System.out.println("Time taken in PARALLEL CHUNKING: " + (timeTaken / 1e9) + " seconds.");
	}
}
