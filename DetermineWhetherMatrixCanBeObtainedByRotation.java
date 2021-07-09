import java.util.Arrays;

// leetcode 1886.
public class DetermineWhetherMatrixCanBeObtainedByRotation {
    public static void main(String[] args) {
        int[][] mat1 = {{0,1},{1,0}};
        int[][] target1 = {{1,0},{0,1}};
        showResults(mat1, target1); // expect true

        int[][] mat2 = {{0,1},{1,1}};
        int[][] target2 = {{1,0},{0,1}};
        showResults(mat2, target2); // expect false

        int[][] mat3 = {{0,0,0},{0,1,0},{1,1,1}};
        int[][] target3 = {{1,1,1},{0,1,0},{0,0,0}};
        showResults(mat3, target3); // expect true

        int[][] mat4 = {{0,0},{0,1}};
        int[][] target4 = {{0,0},{1,0}};
        showResults(mat4, target4); // expect true

        int[][] mat5 = {{1,2},{3,4}};
        int[][] target5 = {{4,3},{2,1}};
        showResults(mat5, target5); // expect true

        int[][] mat6 = {{1,1},{0,0}};
        int[][] target6 = {{0,1},{0,1}};
        showResults(mat6, target6); // expect true
    }

    private static void showResults(int[][] mat, int[][] target) {
        System.out.println("\t----ShowResults----");
        System.out.println("INPUT:");
        for(int[] row: mat)
            System.out.println(Arrays.toString(row));

        System.out.println("\nTARGET:");
        for(int[] row: target)
            System.out.println(Arrays.toString(row));

        System.out.printf("\nOUTPUT: %b\n\n", findRotation(mat, target));
    }

    // Time: O(m*n), space: O(1)
    public static boolean findRotation(int[][] mat, int[][] target) {
        if(mat == null || target == null) return false;
        else if(mat.length != target[0].length) return false;

        final int m = mat.length; // num rows.
        final int n = mat[0].length; // num cols.

        boolean flag1 = true, flag2 = true, flag3 = true, flag4 = true;
        for(int i = 0; i < m; ++i) { // Rotate 90 degree.
            for(int j = 0; j < n; ++j) {
                flag1 &= mat[i][j] == target[j][m-i-1]; // 90 degree.
                flag2 &= mat[i][j] == target[m-i-1][n-j-1]; // 180 degree.
                flag3 &= mat[i][j] == target[n-j-1][i]; // 270 degree.
                flag4 &= mat[i][j] == target[i][j]; // 360 degree.
                if(!(flag1 || flag2 || flag3 || flag4)) return false;
            }
        }

        return true;
    }
}
