import java.util.Arrays;

public class MergeSort {
	public static void main(String[] args) {
		Integer[] arr1 = {1,4,2,0,7};
		showResults(arr1);

		Character[] arr2 = {'F','D','E'};
		showResults(arr2);
	}

	private static void showResults(Comparable[] arr) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(arr));
		sort(arr);
		System.out.println(Arrays.toString(arr) + "\n");
	}

	// Time: O(nlogn)
	public static void sort(Comparable[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	private static void sort(Comparable[] arr, int lo, int hi) {
		if(lo >= hi) return;

		int mid = (lo + hi) / 2;
		
		sort(arr, lo, mid);
		sort(arr, mid + 1, hi);
		merge(arr, lo, mid, hi);
	}

	private static void merge(Comparable[] arr, int lo, int mid, int hi) {
		int i = lo;
		int j = mid + 1;
		Comparable[] aux = arr.clone();
		
		for(int k = lo; k <= hi; ++k) {
			if(i > mid) arr[k] = aux[j++];
			else if(j > hi) arr[k] = aux[i++];
			else if(aux[i].compareTo(aux[j]) < 0) arr[k] = aux[i++];
			else arr[k] = aux[j++];
		}
	}
}
