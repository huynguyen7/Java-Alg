import java.util.concurrent.*;
import java.util.*;

public class MergeSort {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		Integer[] arr = generateRandom(n);
		showResults(arr);
	}

	private static void showResults(Comparable[] arr) {
		Comparable[] copy = arr.clone();
		printTimeInSequential(arr);
		printTimeInParallel(copy);
		System.out.println();
	}

	private static void printTimeInSequential(Comparable[] arr) {
		System.out.println("----SEQUENTIAL----");
		//System.out.println(Arrays.toString(arr));

		double startTime = System.nanoTime();
		
		mergeSortSequential(arr);
		
		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime / 1e9;

		//System.out.println(Arrays.toString(arr));
		System.out.println("Time taken in SEQUENTIAL: " + timeTaken + " seconds.");
	}

	private static void printTimeInParallel(Comparable[] arr) {
		System.out.println("----PARALLEL----");
		//System.out.println(Arrays.toString(arr));

		double startTime = System.nanoTime();
		
		RecursiveAction mergeSortTask = new MergeSortTask(arr);
		mergeSortTask.invoke();
		
		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime / 1e9;

		//System.out.println(Arrays.toString(arr));
		System.out.println("Time taken in PARALLEL: " + timeTaken + " seconds.");
	}

	private static class MergeSortTask extends RecursiveAction {
		Comparable[] arr;

		public MergeSortTask(Comparable[] arr) {
			this.arr = arr;
		}

		@Override
		protected void compute() {
			mergeSortParallel(arr);
		}

		private void mergeSortParallel(Comparable[] arr) {
			if(arr.length > 100 && getSurplusQueuedTaskCount() < 2) {
				final int mid = arr.length / 2;
				
				Comparable[] firstHalf = new Comparable[mid];
				System.arraycopy(arr, 0, firstHalf, 0, mid);
				Comparable[] secondHalf = new Comparable[arr.length - mid];
				System.arraycopy(arr, mid, secondHalf, 0, arr.length - mid);

				MergeSortTask sortLeft = new MergeSortTask(firstHalf);
				MergeSortTask sortRight = new MergeSortTask(secondHalf);
				invokeAll(sortLeft, sortRight);

				MergeSort.mergeSequential(firstHalf, secondHalf, arr);
			} else MergeSort.mergeSortSequential(arr);
		}
	}

	// Time: O(nlogn)
	public static void mergeSortSequential(Comparable[] arr) {
		if(arr.length > 1) {
			final int mid = arr.length / 2;

			Comparable[] firstHalf = new Comparable[mid];
			System.arraycopy(arr, 0, firstHalf, 0, mid);
			Comparable[] secondHalf = new Comparable[arr.length - mid];
			System.arraycopy(arr, mid, secondHalf, 0, arr.length - mid);

			mergeSortSequential(firstHalf);
			mergeSortSequential(secondHalf);
			mergeSequential(firstHalf, secondHalf, arr);
		}
	}

	private static void mergeSequential(Comparable[] firstHalf, Comparable[] secondHalf, Comparable[] arr) {
		int i = 0;
		int j = 0;
		
		for(int k = 0; k < arr.length; ++k) {
			if(i >= firstHalf.length) arr[k] = secondHalf[j++];
			else if(j >= secondHalf.length) arr[k] = firstHalf[i++];
			else if(firstHalf[i].compareTo(secondHalf[j]) < 0) arr[k] = firstHalf[i++];
			else arr[k] = secondHalf[j++];
		}
	}

	private static Integer[] generateRandom(int n) {
		Random rand = new Random();
		Integer[] arr = new Integer[n];
		for(int i = 0; i < n; ++i)
			arr[i] = Integer.valueOf(rand.nextInt(100));
		return arr;
	}
}
