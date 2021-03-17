import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

// leetcode 1219.
public class PathWithMaximumGold {
    public static void main(String[] args) {
        int[][] grid1 = {
            {0,6,0},
            {5,8,7},
            {0,9,0}
        };
        showResults(grid1); // expect 24

        int[][] grid2 = {
            {1,0,7},
            {2,0,6},
            {3,4,5},
            {0,3,0},
            {9,0,20}
        };
        showResults(grid2); // expect 28
    }

    private static void showResults(int[][] grid) {
        System.out.println("\t----ShowResults----");
        for(int[] row: grid)
            System.out.println(Arrays.toString(row));
        System.out.printf("\nRESULT: %d\n\n", getMaximumGold(grid));
    }

    private static int maxGold;
    private static final int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};

    // m = grid.length, n = grid[0].length
    // Time: O(m*n), space: O(m*n)
    public static int getMaximumGold(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;

        final int m = grid.length;
        final int n = grid[0].length;
        Set<Integer> visited = new HashSet<>();
        maxGold = 0;

        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j)
                dfs(grid, visited, i, j, n, m, 0);
        }

        return maxGold;
    }

    private static void dfs(int[][] grid, Set<Integer> visited, int i, int j, final int n, final int m, int currGold) {
        if(grid[i][j] == 0) return;

        visited.add(i*n+j);
        maxGold = Math.max(maxGold, currGold+grid[i][j]);

        for(int[] d: directions) { // For each direction: LEFT, RIGHT, UP, DOWN.
            int iMove = i+d[0];
            int jMove = j+d[1];

            // Constraints:
            if(iMove < 0 || iMove >= m ||
                jMove < 0 || jMove >= n ||
                grid[iMove][jMove] == 0 ||
                visited.contains(iMove*n+jMove)) continue;

            dfs(grid, visited, iMove, jMove, n, m, currGold+grid[i][j]); // EXPLORE
        }

        visited.remove(i*n+j); // UNDO CHOICE
    }
}
