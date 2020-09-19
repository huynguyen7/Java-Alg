import java.util.Arrays;

// leetcode 240, element-prog 12.6
public class Search2DMatrixII {
	public static void main(String[] args) {
		int[][] matrix1 = {
			{1,4,7,11,15},
			{2,5,8,12,19},
			{3,6,9,16,22},
			{10,13,14,17,24},
			{18,21,23,26,30}
		};
		showResults(matrix1, 5); // expect true
		showResults(matrix1, 20); // expect false
	}

	private static void showResults(int[][] matrix, int target) {
		System.out.println("----ShowResults----");
		for(int[] arr: matrix)
			System.out.println(Arrays.toString(arr));
		System.out.println();

		System.out.printf("Found %d in matrix: %b\n\n", target,
						searchMatrix(matrix, target));
	}

	public static boolean searchMatrix(int[][] matrix, int target) {
		if(matrix.length == 0) return false;
		return binarySearch1(matrix, target);
		// return binarySearch2(matrix, target);
	}

	//better approach
	//Time: O(m + n), space: O(1)
	private static boolean binarySearch1(int[][] matrix, int target) {
		int row = 0, col = matrix[0].length - 1; // start from up-right most side of the matrix
		
		while(row < matrix.length && col >= 0) {
			if(matrix[row][col] == target) return true;
			else if(matrix[row][col] > target) col--;
			else row++;
		}

		return false;
	}

	//naive approach
	// Time: O(nlogm), space: O(1)
	// n is total rows, m is total cols
	private static boolean binarySearch2(int[][] matrix, int target) {
		boolean isFound = false;
		for(int i = 0; i < matrix.length; ++i) {
			if(matrix[i][0] <= target && target <= matrix[i][matrix[0].length - 1]) {
				isFound = binarySearchIn1D(matrix[i], target);
				if(isFound) return true;
			} else continue;
		}
		
		return isFound;
	}

	private static boolean binarySearchIn1D(int[] row, int target) {
		int lo = 0, hi = row.length - 1;
		while(lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if(row[mid] == target) return true;
			else if(row[mid] > target) hi = mid - 1;
			else lo = mid + 1;
		}

		return false;
	}
}

// Constraints:
// Integers in each row are sorted
// Integers in each col are sorted.
