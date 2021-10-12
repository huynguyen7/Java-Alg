import java.util.Arrays;

// leetcode 733
public class FloodFill {
    public static void main(String[] args) {
        showResults(new int[][] {{1,1,1},{1,1,0},{1,0,1}}, 1, 1, 2); // expect [[2,2,2],[2,2,0],[2,0,1]]
        showResults(new int[][] {{0,0,0},{0,0,0}}, 0, 0, 2); // expect [[2,2,2],[2,2,2]]
    }

    private static void showResults(int[][] image, int sr, int sc, int newColor) {
        System.out.println("\t----ShowResults----");
        System.out.printf("SR: %d, SC: %d, newColor: %d\n", sr, sc, newColor);
        for(int[] arr: image)
            System.out.println(Arrays.toString(arr));
        System.out.println();

        int[][] rs = floodFill(image, sr, sc, newColor);
        for(int[] arr: rs)
            System.out.println(Arrays.toString(arr));
        System.out.println();
    }

    /**
     * Constraints:
     * + m == image.length
     * + n == image[i].length
     * + 1 <= m, n <= 50
     * + 0 <= image[i][j], newColor < 216
     * + 0 <= sr < m
     * + 0 <= sc < n
     */

    private static int[][] directions = new int[][] {{0,1},{1,0},{0,-1},{-1,0}};

    // Time: O(n*m), space: O(n*m)
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        boolean[][] visited = new boolean[image.length][image[0].length];
        dfs(image, sr, sc, image[sr][sc], newColor, visited);
        return image;
    }

    private static void dfs(int[][] image, int sr, int sc, int startColor, int newColor, boolean[][] visited) {
        if(image[sr][sc] != startColor) return;

        visited[sr][sc] = true;
        image[sr][sc] = newColor;

        for(int[] dir: directions) {
            int r = sr + dir[0];
            int c = sc + dir[1];

            if(r < 0 || r >= image.length || c < 0 || c >= image[0].length
                    || visited[r][c]) continue;
            dfs(image, r, c, startColor, newColor, visited);
        }
    }
}
