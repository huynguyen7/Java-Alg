import java.util.Arrays;

// leetcode 463.
public class IslandPerimeter {
    public static void main(String[] args) {
        int[][] grid1 = {
            {0,1,0,0},
            {1,1,1,0},
            {0,1,0,0},
            {1,1,0,0}
        };
        showResults(grid1); // expect 16

        int[][] grid2 = {{1}};
        showResults(grid2); // expect 4

        int[][] grid3 = {{1,0}};
        showResults(grid3); // expect 4

        int[][] grid4 = {{0,0},{1,1}};
        showResults(grid4); // expect 6
    }

    private static void showResults(int[][] grid) {
        System.out.println("\t----ShowResults----");
        for(int[] row: grid)
            System.out.println(Arrays.toString(row));
        int rs = islandPerimeter(grid);
        System.out.printf("Results: %d\n\n", rs);
    }

    private static final int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}}; 

    // Time: O(n*m), space: O(n*m)
    public static int islandPerimeter(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;

        int i = 0, j = 0;
        boolean flag = false;
        for(i = 0; i < grid.length; ++i) {
            for(j = 0; j < grid[0].length; ++j)
                if(grid[i][j] == 1) {
                    flag = true;
                    break;
                }
            if(flag) break;
        }

        if(i >= grid.length || j >= grid[0].length) return 4;
        return dfs(grid, i, j);
    }

    private static int dfs(int[][] grid, int x, int y) {
        if(grid[x][y] == 2) return 0;
        grid[x][y] = 2;

        int perimeter = 0;
        for(int[] direction: directions) {
            int xPos = x + direction[0];
            int yPos = y + direction[1];

            if(xPos < 0 || xPos >= grid.length 
                || yPos < 0 || yPos >= grid[0].length 
                || grid[x][y] == 0) {
                perimeter += 1;
                continue;
            }
            perimeter += dfs(grid, xPos, yPos);
        }

        return perimeter;
    }
}
