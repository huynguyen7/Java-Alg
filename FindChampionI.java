import java.util.Arrays;

// leetcode 2923.
public class FindChampionI {
    public static void main(String[] args) {
        assert(showResults(new int[][] {{0,1},{0,0}}) == 0); // expect 0
        assert(showResults(new int[][] {{0,0,1},{1,0,1},{0,0,0}}) == 1); // expect 1
        assert(showResults(new int[][] {{0,0,0},{1,0,1},{1,0,0}}) == 1); // expect 1
    }

    private static int showResults(int[][] grid) {
        System.out.println("\t----ShowResults----");
        for(int[] arr: grid)
            System.out.println(Arrays.toString(arr));
        int rs = findChampion(grid);
        System.out.printf("=> RS: %d\n\n", rs);
        return rs;
    }

    // Time: O(n^2), space: O(1)
    public static int findChampion(int[][] grid) {
        final int n = grid.length;
        int winningTeam = 0;
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                if(i == j) continue;
                if(grid[i][j] == 1 && i != winningTeam && grid[winningTeam][j] != 1)
                    winningTeam = i;
            }
        }

        return winningTeam;
    }
}
