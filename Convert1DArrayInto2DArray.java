import java.util.Arrays;

// leetcode 2022.
public class Convert1DArrayInto2DArray {
    public static void main(String[] args) {
        showResults(new int[] {1,2,3,4},2,2); // expect [[1,2],[3,4]]
        showResults(new int[] {1,2,3},1,3); // expect [[1,2,3]]
        showResults(new int[] {1,2},1,1); // expect []
        showResults(new int[] {3},1,2); // expect []
    }

    private static void showResults(int[] original, int m, int n) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(original));
        System.out.printf("\n2D Mat size: %d %d\n", m, n);
        int[][] mat = construct2DArray(original, m, n);
        for(int[] lin: mat)
            System.out.println(Arrays.toString(lin));
        System.out.println("\n");
    }

    // Time: O(n*m), space: O(n*m)
    public static int[][] construct2DArray(int[] original, int m, int n) {
        if(m*n != original.length) return new int[][] {};

        int[][] mat = new int[m][n];
        for(int i = 0; i < original.length; ++i)
            mat[i/n][i%n] = original[i];

        return mat;
    }
}
