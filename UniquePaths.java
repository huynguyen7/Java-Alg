import java.util.Arrays;

// leetcode 62.
public class UniquePaths {
	public static void main(String[] args) {
		showResults(3, 2); // expect 3
		showResults(3, 7); // expect 28
		showResults(7, 3); // expect 28
		showResults(3, 3); // expect 6
		showResults(1, 1); // expect 1
	}

	private static void showResults(int m, int n) {
		System.out.println("----ShowResults----");
		System.out.printf("m = %d, n = %d\n", m, n);
		int rs = uniquePathsII(m, n);
		System.out.printf("Number of unique paths: %d\n\n", rs);
	}

	// DP approach.
	// bottom-up DP.
	// Time: O(m*n), space: O(m*n)
	public static int uniquePaths(int m, int n) {
		int[][] cache = new int[m][n];

		// Only way for first row and first col.
		for(int i = 0; i < m; ++i)
			cache[i][0] = 1;
		for(int j = 0; j < n; ++j)
			cache[0][j] = 1;

		for(int i = 1; i < m; ++i) {
			for(int j = 1; j < n; ++j)
				cache[i][j] = cache[i - 1][j] + cache[i][j - 1];
		}

		return cache[m - 1][n - 1];
	}

	// DP approach.
	// top-down DP.
	// Time: O(m*n), space: O(m*n)
	public static int uniquePathsI(int m, int n) {
		if(m == 1 && n == 1) return 1;

		int[][] cache = new int[m][n];
		for(int[] nums: cache)
			Arrays.fill(nums, -1);
		
		dfsI(m, n, 0, 0, cache);
		return cache[0][0];
	}

	private static int dfsI(int m, int n, int row, int col, int[][] cache) {
		if(row == m - 1 && col == n - 1) return 1;
		else if(cache[row][col] == -1) { // unexplored
			int numWaysGoRight = col < n - 1 ? dfsI(m, n, row, col + 1, cache) : 0;
			int numWaysGoDown = row < m - 1 ? dfsI(m, n, row + 1, col, cache) : 0;

			cache[row][col] = numWaysGoRight + numWaysGoDown;
		}

		return cache[row][col];
	}

	// Recursive approach.
	// Time: O(2^n), space: O(2^n)
	public static int uniquePathsII(int m, int n) {
		return dfsII(m, n, 0, 0);
	}

	private static int dfsII(int m, int n, int row, int col) {
		if(row == m - 1 && col == n - 1) return 1;
		else {
			int numWaysGoRight = col < n - 1 ? dfsII(m, n, row, col + 1) : 0;
			int numWaysGoDown = row < m - 1 ? dfsII(m, n, row + 1, col) : 0;
			
			return numWaysGoRight + numWaysGoDown;
		}
	}
}

// Constraints:
// - Can only move either down or right.
// - Bottom-right corner of the grid is the final destination.
