import java.util.*;

// leetcode 1260.
public class Shifting2DGrid {
	public static void main(String[] args) {
		int[][] grid1 = {
			{1,2,3},
			{4,5,6},
			{7,8,9}
		};
		int k1 = 1;
		showResults(grid1, k1);

		int[][] grid2 = {
			{3,8,1,9},
			{19,7,2,5},
			{4,6,11,10},
			{12,0,21,13}
		};
		int k2 = 4;
		showResults(grid2, k2);
	
		int[][] grid3 = {
			{1,2,3},
			{4,5,6},
			{7,8,9}
		};
		int k3 = 9;
		showResults(grid3, k3);
	}

	private static void showResults(int[][] grid, int k) {
		System.out.println("----ShowResults----");
		System.out.printf("k = %d\n", k);
		for(int[] row: grid)
			System.out.println(Arrays.toString(row));
		System.out.println();
		List<List<Integer>> rs = shiftGrid(grid, k);
		for(List<Integer> row: rs)
			System.out.println(row.toString());
		System.out.println();
	}	

	// Time: O(k*m*n), space: O(m*n)
	public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
		if(grid == null || grid.length == 0) return Collections.emptyList();

		final int m = grid.length;
		final int n = grid[0].length;

		k %= m*n;

		List<List<Integer>> rs = new ArrayList<>();
		for(int i = 0; i < m; ++i) {
			rs.add(new ArrayList<>());
			for(int j = 0; j < n; ++j)
				rs.get(i).add(grid[i][j]);
		}

		while(k-- > 0) {
			for(int i = m*n - 1; i > 0; --i) {
				int tmp = rs.get(i / n).get(i % n);
				rs.get(i / n).set(i % n, rs.get((i - 1) / n).get((i - 1) % n));
				rs.get((i - 1) / n).set((i - 1) % n, tmp);
			}
		}
		
		return rs;
	}
}
