import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

// leetcode 2133.
public class CheckIfEveryRowAndColumnContainsAllNumbers {
    public static void main(String[] args) {
        assert(showResults(new int[][] {{1,2,3},{3,1,2},{2,3,1}}
)); // expect true.
        assert(!showResults(new int[][] {{1,1,1},{1,2,3},{1,2,3}}
)); // expect false.
    }

    private static boolean showResults(int[][] matrix) {
        System.out.println("\t----ShowResults----");
        for(int[] row: matrix)
            System.out.println(Arrays.toString(row));
        boolean rs = checkValid(matrix);;
        System.out.printf("%b\n\n", rs);

        return rs;
    }

    // Time: O(n^2), space: O(n^2)
    public static boolean checkValid(int[][] matrix) {
        Set<Integer> visited = new HashSet<>();
        final int n = matrix.length;

        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                int val = matrix[i][j];
                if(val < 0 || val > n) return false;
                else visited.add(val);
            }

            if(visited.size() != n) return false;
            visited.clear();
        }

        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                int val = matrix[j][i];
                if(val < 0 || val > n) return false;
                else visited.add(val);
            }

            if(visited.size() != n) return false;
            visited.clear();
        }

        return true;
    }
}
