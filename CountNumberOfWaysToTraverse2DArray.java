import java.util.Arrays;

// element-prog 17.3
public class CountNumberOfWaysToTraverse2DArray {
	public static void main(String[] args) {
		int n1 = 5;
		int m1 = 5;
		showResults(n1, m1); // expect 70

		int n2 = 2;
		int m2 = 3;
		showResults(n2, m2); // expect 3
							// GOOD EXAMPLE TO DRAW RECURSION TREE.
	}

	// n is the number of rows.
	// m is the number of cols.
	
	// Constraints:
	// m, n > 0
	// Traverse from top-left corner to bottom-right corner.
	// All movement must either right or down.
	
	private static void showResults(int n, int m) {
		System.out.println("----ShowResults----");
		
		char[][] sample = new char[n][m];
		for(char[] arr: sample) {
			Arrays.fill(arr, 'x');
			System.out.println(Arrays.toString(arr));
		}
		System.out.println();
		
		System.out.printf("Array size %dx%d have %d ways to traverse.\n\n", n, m,
						numberOfWaysI(n, m));
	}

	// DP approach
	// bottom-up DP.
	// Time: O(n*m), space: O(n*m)
	public static int numberOfWaysI(int n, int m) {
		int[][] numOfWays = new int[n + 1][m + 1];
		
		for(int i = 1; i <= n; ++i) {
			for(int j = 1; j <= m; ++j) {
				if(i == 1 || j == 1) // init base case
					numOfWays[i][j] = 1;
				else
					numOfWays[i][j] = numOfWays[i - 1][j] + numOfWays[i][j - 1];
			}
		}

		return numOfWays[n][m];
	}

	// DP approach with recursion.
	// top-down DP.
	// Time: O(n*m), space: O(n*m)
	public static int numberOfWaysII(int n, int m) {
		return dfs(n - 1, m - 1, new int[n][m]); // start from bottom-right corner
	}

	private static int dfs(int col, int row, int[][] numOfWays) {
		if(col == 0 || row == 0) return 1; // col | row = 0 means this is the end of traversing. 

		if(numOfWays[col][row] == 0) { // numOfWays[col][row] = 0 means this value is not explored.
			int numWaysTop = dfs(col - 1, row, numOfWays); // go top
			int numWaysLeft = dfs(col, row - 1, numOfWays); // go left

			numOfWays[col][row] = numWaysTop + numWaysLeft;
		}

		return numOfWays[col][row];
	}
}
