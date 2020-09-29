import java.util.Arrays;

// leetcode 1277.
public class CountSquareSubmatricesWithAllOnes {
	public static void main(String[] args) {
		int[][] matrix1 = {
			{0,1,1,1},
			{1,1,1,1},
			{0,1,1,1}
		};
		showResults(matrix1); // expect 15

		int[][] matrix2 = {
			{1,0,1},
			{1,1,0},
			{1,1,0}
		};
		showResults(matrix2); // expect 7
	}

	private static void showResults(int[][] matrix) {
		System.out.println("----ShowResults----");
		for(int[] arr: matrix) 
			System.out.println(Arrays.toString(arr));
		System.out.println();
		int rs = countSquaresI(matrix);
		System.out.printf("Number of square submatrices: %d\n\n", rs);
	}

	// DP approach
	// bottom-up DP
	// Time: O(m*n), space: O(m*n)
	public static int countSquaresI(int[][] matrix) {
		int[][] numSquareMatricesHaveOnes = new int[matrix.length + 1][matrix[0].length + 1];
		int squaresCount = 0;

		for(int row = matrix.length - 1; row >= 0; row--) {
			for(int col = matrix[row].length - 1; col >= 0; col--) {
				if(matrix[row][col] == 0) continue; // if 0, skip
				
				int largestLengthRightHaveOnes = numSquareMatricesHaveOnes[row][col + 1];
				int largestLengthBottomHaveOnes = numSquareMatricesHaveOnes[row + 1][col];
				int largestLengthBottomRightHaveOnes = numSquareMatricesHaveOnes[row + 1][col + 1];

				int minSquareMatrixLengthHaveOnes = Math.min(largestLengthRightHaveOnes, Math.min(largestLengthBottomHaveOnes, largestLengthBottomRightHaveOnes)) + 1;
				squaresCount += minSquareMatrixLengthHaveOnes;
				numSquareMatricesHaveOnes[row][col] = minSquareMatrixLengthHaveOnes;
			}
		}

		return squaresCount;
	}
	
	// recursive approach
	// m = matrix.length, n = matrix[0].length
	// Time: O(m*n*(3^(m*n))), space: O(3^(m*n))
	public static int countSquaresII(int[][] matrix) {
		int squaresCount = 0;
		
		for(int row = 0; row < matrix.length; ++row) { // Time: O(m)
			for(int col = 0; col < matrix[0].length; ++col) // Time: O(n)
				// adding number of squares matrices that have only 1s. (start from top left corner)
				squaresCount += dfs(row, col, matrix); // Time: O(3^(m*n))
		}

		return squaresCount;
	}

	// this method returns the largest length of square matrix that have only 1s.
	private static int dfs(int row, int col, int[][] matrix) {
		if(row >= matrix.length || col >= matrix[row].length) return 0;
		if(matrix[row][col] == 0) return 0;
		
		int largestLengthRightHaveOnes = dfs(row, col + 1, matrix);
		int largestLengthBottomHaveOnes = dfs(row + 1, col, matrix);
		int largestLengthBottomRightHaveOnes = dfs(row + 1, col + 1, matrix);
		
		return Math.min(largestLengthRightHaveOnes, Math.min(largestLengthBottomHaveOnes, largestLengthBottomRightHaveOnes)) + 1;
	}
}

// Constraints:
// matrix[i][j] = 1 | 0

// Good explanation: https://leetcode.com/problems/count-square-submatrices-with-all-ones/discuss/853042/Java-Recursive-greater-Memoization-greater-2D-Bottom-Up-greater-1D-Bottom-Up
