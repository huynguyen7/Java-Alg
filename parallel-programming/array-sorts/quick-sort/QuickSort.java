import java.util.*;
import java.util.concurrent.*;

public class QuickSort {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		Integer[] arr = generateRandom(n);
		showResults(arr);
	}

	private static void showResults(Comparable[] arr) {
		Comparable[] clone = arr.clone();
		printTimeSequential(arr);
		printTimeParallel(clone);
		System.out.println();
	}

	private static void printTimeSequential(Comparable[] arr) {
		System.out.println("----SEQUENTIAL----");
		//System.out.println(Arrays.toString(arr));

		double startTime = System.nanoTime();

		quickSortSequential(arr, 0, arr.length - 1);

		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime / 1e9;
		//System.out.println(Arrays.toString(arr));
		//assertion(arr);
		System.out.println("Time taken in SEQUENTIAL: " + timeTaken + " seconds.");
	} 

	private static void printTimeParallel(Comparable[] arr) {
		System.out.println("----PARALLEL----");
		//System.out.println(Arrays.toString(arr));

		double startTime = System.nanoTime();
		
		RecursiveAction quickSortTask = new QuickSortTask(arr, 0, arr.length - 1);
		quickSortTask.invoke();

		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime / 1e9;
		//System.out.println(Arrays.toString(arr));
		//assertion(arr);
		System.out.println("Time taken in PARALLEL: " + timeTaken + " seconds.");
	}

	private static class QuickSortTask extends RecursiveAction {
		Comparable[] arr;
		int lo;
		int hi;
		
		public QuickSortTask(Comparable[] arr, int lo, int hi) {
			this.arr = arr;
			this.lo = lo;
			this.hi = hi;
		}

		@Override
		protected void compute() {
			quickSortParallel(arr, lo, hi);
		}

		private void quickSortParallel(Comparable[] arr, int lo, int hi) {
			if(lo >= hi) return;

			if(arr.length > 100 && getSurplusQueuedTaskCount() < 2) {
				int pivotIndex = QuickSort.partition(arr, lo, hi); // run in sequential.
				QuickSortTask sortLeft = new QuickSortTask(arr, lo, pivotIndex - 1);
				QuickSortTask sortRight = new QuickSortTask(arr, pivotIndex + 1, hi);
				invokeAll(sortLeft, sortRight);
			} else QuickSort.quickSortSequential(arr, lo, hi);
		}
	}

	// Time: O(nlogn)
	public static void quickSortSequential(Comparable[] arr, int lo, int hi) {
		if(lo >= hi) return;
		
		int pivotIndex = partition(arr, lo, hi);
		quickSortSequential(arr, lo, pivotIndex - 1);
		quickSortSequential(arr, pivotIndex + 1, hi);
	}

	private static int partition(Comparable[] arr, int lo, int hi) {
		int i = lo;
		int j = hi;
		Comparable pivotValue = arr[lo];

		while(i < j) {
			while(i < arr.length && arr[i].compareTo(pivotValue) <= 0) i++;
			while(j >= 0 && arr[j].compareTo(pivotValue) > 0) j--;
			if(i >= j) break;
			swap(arr, i, j);
		}
		swap(arr, lo, j);

		return j;
	}

	private static void swap(Comparable[] arr, int i, int j) {
		Comparable tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	private static Integer[] generateRandom(int n) {
		Random rand = new Random();
		Integer[] arr = new Integer[n];
		for(int i = 0; i < n; ++i)
			arr[i] = Integer.valueOf(rand.nextInt(100));
		return arr;
	}

	private static void assertion(Comparable[] arr) {
		for(int i = 1; i < arr.length; ++i) {
			if(arr[i].compareTo(arr[i - 1]) < 0) {
				System.out.println("WRONG!!!");
				break;
			}
		}
	}
}
