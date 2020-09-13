import java.util.Arrays;

//leetcode 1572.
public class MatrixDiagonalSum {
	public static void main(String args[]) {
		int[][] m1 = {
					{1,2,3},
					{4,5,6},
					{7,8,9}
					};
		showResults(m1); //expect 25

		int[][] m2 = {
					{1,1,1,1},
					{1,1,1,1},
					{1,1,1,1},
					{1,1,1,1}
					};
		showResults(m2); //expect 8
	}

	private static void showResults(int[][] matrix) {
		System.out.println("-----ShowResults-----");
		for(int[] row: matrix)
			System.out.println(Arrays.toString(row));
		System.out.printf("\nSUM: %d\n", diagonalSum(matrix));
	}

	public static int diagonalSum(int[][] matrix) {
		int sum = 0;
		
		for(int i = 0; i < matrix.length; ++i) {
			sum += matrix[i][i];
			sum += matrix[matrix.length - 1 - i][i];
		}

		if(matrix.length % 2 != 0) {
			int midIndex = matrix.length / 2;
			sum -= matrix[midIndex][midIndex];
		}
		
		return sum;
	}
}
