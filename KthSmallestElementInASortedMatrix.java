import java.util.Arrays;
import java.util.PriorityQueue;

//INPUT must be sorted matrix.
//leetcode 378.
public class KthSmallestElementInASortedMatrix {
	public static void main(String args[]) {
		int[][] matrix1 = {
			{1,5,9},
			{10,11,13},
			{12,13,15}
		};
		int k1 = 8;
		showResults(matrix1, k1); //expect 13
	}
	
	private static void showResults(int[][] matrix, int k) {
		System.out.println("----ShowResults----");
		for(int[] arr: matrix)
			System.out.println(Arrays.toString(arr));
		System.out.printf("%d'th smallest element: %d\n\n", k,
						kthSmallest(matrix, k));
	}

	//time: O(nlogk), space: O(k)
	public static int kthSmallest(int[][] matrix, int k) {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
			k + 1, //init heap's max size
			(a, b) -> Integer.compare(b, a)
		);

		int numRows = matrix.length;
		int numCols = matrix[0].length;
		for(int i = 0; i < numRows * numCols; ++i) {
			maxHeap.add(matrix[i / numCols][i % numCols]);
			if(maxHeap.size() == k + 1)
				maxHeap.poll(); //filter
		}

		return maxHeap.peek();
	}
}
