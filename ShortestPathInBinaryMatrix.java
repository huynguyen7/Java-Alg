import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

// leetode 1091.
public class ShortestPathInBinaryMatrix {
	public static void main(String[] args) {
		int[][] grid1 = {
			{0,1},
			{1,0}
		};
		showResults(grid1); // expect 2

		int[][] grid2 = {
			{0,0,0},
			{1,1,0},
			{1,1,0}
		};
		showResults(grid2); // expect 4

		int[][] grid3 = {
			{0,1,1},
			{1,1,0},
			{1,1,0}
		};
		showResults(grid3); // expect -1
		
		int[][] grid4 = {
			{0,0,0},
			{0,1,0},
			{1,1,1}
		};
		showResults(grid4); // expect -1

		int[][] grid5 = {
			{0,0,1,0,0,0,0},
			{0,1,0,0,0,0,1},
			{0,0,1,0,1,0,0},
			{0,0,0,1,1,1,0},
			{1,0,0,1,1,0,0},
			{1,1,1,1,1,0,1},
			{0,0,1,0,0,0,0}
		};
		showResults(grid5); // expect 10
	}
	
	private static void showResults(int[][] grid) {
		System.out.println("----ShowResults----");
		for(int[] nums: grid)
			System.out.println(Arrays.toString(nums));
		System.out.println();

		int rs = shortestPathBinaryMatrix(grid);
		System.out.printf("Shortest path: %d\n\n", rs);
	}

	// Using BFS.
	// n is the number of rows, m is the number of cols.
	// Time: O(n*m), space: O(n*m)
    public static int shortestPathBinaryMatrix(int[][] grid) {
        if(grid == null || grid.length == 0) return -1;
        int n = grid.length, m = grid[0].length;
        
        if(grid[0][0] == 1|| grid[n - 1][m - 1] == 1)
            return -1;
        
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                if(grid[i][j] != 1)
                    grid[i][j] = Integer.MAX_VALUE;
            }
        }
        grid[0][0] = 1; // path from source to source is 1.
        
        Deque<int[]> queue = new LinkedList<>();
        queue.addLast(new int[] {0,0}); // start at [0,0]
        
        int[][] moves = {
            {1,0},{1,1},{0,1},{-1,1},
            {-1,0},{-1,-1},{0,-1},{1,-1}
        };
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- != 0) {
                int[] coordinate = queue.removeFirst();
                int x = coordinate[0];
                int y = coordinate[1];
                
                for(int[] d: moves) {
                    int dx = x + d[0];
                    int dy = y + d[1];
                    
                    if(dx < 0 || dy < 0 || dx == n || dy == m ||
                      grid[dx][dy] <= grid[x][y] + 1)
                        continue;
                    
                    grid[dx][dy] = grid[x][y] + 1;
                    queue.addLast(new int[] {dx, dy});
                }
            }
        }
        
        return grid[n - 1][m - 1] != Integer.MAX_VALUE ? grid[n - 1][m - 1] : -1;
    }
}

// Constraints:
// - We can go down, right, diagonal right.
// - Empty is 0, blocked is 1.
