import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// leetcode 200.
public class NumberOfIslands {
    public static void main(String[] args) {
        char[][] grid1 = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        showResults(grid1); // expect 1

        char[][] grid2 = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'},
        };
        showResults(grid2); // expect 3
    }

    private static void showResults(char[][] grid) {
        System.out.println("\t----ShowResults----");
        for(char[] row: grid)
            System.out.println(Arrays.toString(row));
        int rs = numIslandsI(grid);
        System.out.printf("Number of islands: %d\n\n", rs);
    }

    // DFS
    // Time: O(m*n), space: O(m*n)
    public static int numIslandsI(char[][] grid) {
        if(grid.length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }

        return count;
    }

    private static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};

    private static void dfs(char[][] grid, int i, int j) {
        if(i < 0 || i >= grid.length 
            || j < 0 || j >= grid[0].length 
            || grid[i][j] != '1') return;
        
        grid[i][j] = '2'; // 2 means visited
        for(int[] dir: directions)
            dfs(grid, i + dir[0], j + dir[1]);
    }

    // BFS
    // Time: O(m*n), space: O(m*n)
    public static int numIslandsII(char[][] grid) {
        if(grid.length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;

        int count = 0;
        Deque<int[]> queue = new LinkedList<>();
        
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(grid[i][j] == '1') {
                    count++;
                    queue.addLast(new int[] {i, j});

                    while(!queue.isEmpty()) {
                        int[] pos = queue.removeFirst();
                        grid[pos[0]][pos[1]] = '2'; // 2 means visited.
                        
                        for(int[] dir: directions) {
                            int x = pos[0] + dir[0];
                            int y = pos[1] + dir[1];

                            if(x < 0 || x >= m 
                                || y < 0 || y >= n 
                                || grid[x][y] != '1') continue;

                            queue.addLast(new int[] {x, y});
                        }
                    }
                }
            }
        }

        return count;
    }
}
