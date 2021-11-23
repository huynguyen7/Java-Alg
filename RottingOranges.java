import java.util.*;

// leetcode 994.
public class RottingOranges {
    public static void main(String[] args) {
        showResults(new int[][] {{2,1,1},{1,1,0},{0,1,1}}); // expect 4
        showResults(new int[][] {{2,1,1},{0,1,1},{1,0,1}}); // expect -1
        showResults(new int[][] {{0,2}}); // expect 0
    }

    private static void showResults(int[][] grid) {
        System.out.println("\t----ShowResults----");
        for(int[] row: grid)
            System.out.println(Arrays.toString(row));
        int rs = orangesRotting(grid);
        System.out.printf("-> %d\n\n", rs);
    }

    private static final int[][] DIRECTIONS = new int[][] {{0,1},{0,-1},{1,0},{-1,0}};
    private static final int IS_EMPTY  = 0;
    private static final int IS_FRESH  = 1;
    private static final int IS_ROTTEN = 2;

    // BFS approach.
    // Time: O(m*n), space: O(m*n)
    public static int orangesRotting(int[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;

        Deque<int[]> queue = new LinkedList<>();

        int numFresh = 0;
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(grid[i][j] == IS_FRESH)
                    numFresh++;
                else if(grid[i][j] == IS_ROTTEN)
                    queue.addLast(new int[] {i,j});
            }
        }

        int time = 0;
        // BFS
        while(!queue.isEmpty()) {
            int size = queue.size();
            int currNumFresh = numFresh;
            while(size-- > 0) { // Level traversal.
                int[] currRotten = queue.pollFirst();
                for(int[] dir: DIRECTIONS) {
                    int new_i = currRotten[0]+dir[0];
                    int new_j = currRotten[1]+dir[1];

                    // Check off-grid and if the cell is fresh.
                    if(new_i < 0 || new_j < 0 || new_i >= m || new_j >= n
                            || grid[new_i][new_j] != IS_FRESH) continue;

                    // Make it rotten.
                    grid[new_i][new_j] = IS_ROTTEN;
                    queue.addLast(new int[] {new_i,new_j});
                    numFresh--;
                }
            }

            // If it keeps rotting, we increase the time.
            if(currNumFresh != numFresh)
                time++;
        }

        // If we cannot make all the fresh ones turn to rottens, return -1.
        return numFresh != 0 ? -1 : time;
    }
}
