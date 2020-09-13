import java.util.Arrays;

//leetcode 1337.
public class TheKWeakestRowsInAMatrix {
	public static void main(String[] args) {
		int[][] nums1 = {
			{1,1,0,0,0},
			{1,1,1,1,0},
			{1,0,0,0,0},
			{1,1,0,0,0},
			{1,1,1,1,1}	
		};
		int k1 = 3;
		showResults(nums1, k1); //expect {2,0,3}

		int[][] nums2 = {
			{1,0,0,0},
			{1,1,1,1},
			{1,0,0,0},
			{1,0,0,0}
		};
		int k2 = 2;
		showResults(nums2, k2); //expect {0,2}
	}
	
	private static void showResults(int[][] nums, int k) {
		System.out.println("----ShowResults----");
		System.out.println("INPUT");
		for(int[] n: nums)
			System.out.println(Arrays.toString(n));
		System.out.printf("k: %d\n", k);
		System.out.println("OUTPUT");
		System.out.println(Arrays.toString(kWeakestRows(nums, k)) + "\n");
	}

	//time: O(m*logn), space: O(m); m is the number of rows, n is the number of cols.
	public static int[] kWeakestRows(int[][] matrix, int k) {
		int numRows = matrix.length;
		int numCols = matrix[0].length;

		//space: O(m)
		int[][] count = new int[numRows][2]; //fist col is stored indexes, second col is store values
		for(int i = 0; i < matrix.length; ++i) { //time: O(m)
			count[i][0] = i;
			count[i][1] = binarySearch(matrix[i], 0, numRows - 1); //time: O(logn)
		}
		//lambda expression implements a comparator interface
		Arrays.sort(count, (index1, index2) -> (index1[1] != index2[1] ? index1[1] - index2[1] : index1[0] - index2[0]));
		
		int[] rs = new int[k];
		for(int i = 0; i < k; ++i)
			rs[i] = count[i][0];
		return rs;
	}

	//time: O(logn)
	private static int binarySearch(int[] row, int lo, int hi) {
		if(lo > hi) return lo;
		
		int mid = lo + (hi - lo) / 2;
		if(row[mid] == 0) return binarySearch(row, lo, mid - 1);
		else return binarySearch(row, mid + 1, hi);
	}
}
