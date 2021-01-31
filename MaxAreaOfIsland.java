import java.util.Arrays;

// leetcode 695.
public class MaxAreaOfIsland {
    public static void main(String[] args) {
        int[][] grid1 = {
            {0,0,1,0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,1,1,0,1,0,0,0,0,0,0,0,0},
            {0,1,0,0,1,1,0,0,1,0,1,0,0},
            {0,1,0,0,1,1,0,0,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };
        showResults(grid1); // expect 6

        int[][] grid2 = {
            {0,0,0,0,0,0,0,0}
        };
        showResults(grid2); // expect 0
    }

    private static void showResults(int[][] grid) {
        System.out.println("\t----ShowResults----");
        for(int[] row: grid)
            System.out.println(Arrays.toString(row));

        int rs = maxAreaOfIsland(grid);
        System.out.printf("\nMax area: %d\n\n", rs);
    }

    private static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};

    // DFS
    // Time: O(m*n), space: O(m*n)
    public static int maxAreaOfIsland(int[][] grid) {
        if(grid.length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;
        int maxArea = 0;

        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(grid[i][j] == 1)
                    maxArea = Math.max(maxArea, dfs(grid, i, j, 0));
            }
        }

        return maxArea;
    }

    private static int dfs(int[][] grid, int i, int j, int area) {
        if(i < 0 || i >= grid.length 
            || j < 0 || j >= grid[0].length 
            || grid[i][j] != 1) return 0;

        int tmp = 0;
        grid[i][j] = 2; // 2 means visited.
        for(int[] dir: directions)
            tmp += dfs(grid, i + dir[0], j + dir[1], area + 1);
        return tmp + 1;
    }
}
