import java.util.Arrays;

// leetcode 2643.
public class RowWithMaximumOnes {
    public static void main(String[] args) {
        assert(compare(showResults(new int[][] {{0,1},{1,0}}), new int[] {0,1})); // expect [0,1]
        assert(compare(showResults(new int[][] {{0,0,0},{0,1,1}}), new int[] {1,2})); // expect [1,2]
        assert(compare(showResults(new int[][] {{0,0},{1,1},{0,0}}), new int[] {1,2})); // expect [1,2]
    }

    private static int[] showResults(int[][] mat) {
        int[] rs = rowAndMaximumOnes(mat);
        return rs;
    }

    // Time: O(m*n), space: O(1)
    public static int[] rowAndMaximumOnes(int[][] mat) {
        int rowIdx = 0; // For storing resulting row that contains the maximum count of ones
        int numOnes = 0; // For storing resulting the number of ones in rowIndex.

        for(int row = 0; row < mat.length; ++row) {
            int numOnesTmp = 0;
            for(int i = 0; i < mat[0].length; ++i)
                if(mat[row][i] == 1) numOnesTmp++;
            if(numOnesTmp > numOnes) {
                numOnes = numOnesTmp;
                rowIdx = row;
            }
        }

        return new int[] {rowIdx, numOnes};
    }

    private static boolean compare(int[] nums1, int[] nums2) {
        final int n1 = nums1.length;
        final int n2 = nums2.length;
        if(n1 != n2) return false;

        for(int i = 0; i < n1; ++i)
            if(nums1[i] != nums2[i]) return false;

        return true;
    }
}
