import java.util.Arrays;

// leetcode 64.
public class MinimumPathSum {
	public static void main(String[] args) {
		int[][] grid1 = {
							{1,3,1},
							{1,5,1},
							{4,2,1}
						};
		showResults(grid1); // expect 7
							// 1->3->1->1->1
							// GOOD EXAMPLE TO DRAW RECURSION TREE.

		int[][] grid2 = {
							{1,4,1},
							{2,2,3},
							{1,2,2}
						};
		showResults(grid2); // expect 8
							// 1->2->1->2->2

		int[][] grid3 = {
							{0,0},
							{1,3}
						};
		showResults(grid3); // expect 3
							// 0->0->3
	}

	private static void showResults(int[][] grid) {
		System.out.println("----ShowResults----");
		for(int[] row: grid)
			System.out.println(Arrays.toString(row));
		System.out.println();

		int rs = minPathSumI(grid);
		System.out.printf("Min Path Sum: %d\n\n", rs);
	}

	// RULES:
	// You can only move either down or right.
	// Find a path from top left to bottom right.

	// DP approach.
	// bottom-up DP.
	// n = grid.length
	// Time: O(n^2), space: O(n^2)
	public static int minPathSumI(int[][] grid) {
		if(grid.length == 0 || grid[0].length == 0) return 0;

		int row = grid.length;
		int col = grid[0].length;
		int[][] cache = new int[row][col];

		cache[0][0] = grid[0][0]; // init first element

		for(int i = 1; i < row; ++i)
			cache[i][0] = cache[i - 1][0] + grid[i][0];
		for(int i = 1; i < col; ++i)
			cache[0][i] = cache[0][i - 1] + grid[0][i];
		for(int i = 1; i < row; ++i) {
			for(int j = 1; j < col; ++j)
				cache[i][j] = Math.min(cache[i - 1][j], cache[i][j - 1]) + grid[i][j];
		}

		return cache[row - 1][col - 1];
	}

	// Recursive approach.
	// n = grid.length
	// Time: O(2^n), space: O(2^n)
	public static int minPathSumII(int[][] grid) {
		if(grid.length == 0 || grid[0].length == 0) return 0;
		return dfsII(grid, 0, 0);
	}

	private static int dfsII(int[][] grid, int row, int col) {
		if(row >= grid.length || col >= grid[0].length) return 0;
		
		int minDownSum = dfsII(grid, row + 1, col);
		int minRightSum = dfsII(grid, row, col + 1);
		
		// 0 means it reach the grid's edge,
		// only use the path that can only reach the bottom-right (not 0).
		if(minDownSum != 0 && minRightSum != 0) return Math.min(minDownSum, minRightSum) + grid[row][col];
		else if(minDownSum == 0) return minRightSum + grid[row][col];
		else return minDownSum + grid[row][col];
	}
}
