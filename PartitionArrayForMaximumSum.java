import java.util.*;

// leetcode 1043.
public class PartitionArrayForMaximumSum {
    public static void main(String[] args) {
        assert(showResults(new int[] {1,15,7,9,2,5,10}, 3) == 84); // expect 84
        assert(showResults(new int[] {1,4,1,5,7,3,6,1,9,9,3}, 4) == 83); // expect 83
        assert(showResults(new int[] {1}, 1) == 1); // expect 1
    }

    private static int showResults(int[] arr, int k) {
        System.out.println("\t----ShowResults----");
        int rs = maxSumAfterPartitioning(arr, k);
        System.out.printf("%s\n%d -> %d\n\n", Arrays.toString(arr), 
                            k, rs);
        return rs;
    }

    /** DP approach.
        Top-down approach.
        Time: O(k*n), space: O(k*n)
    */
    public static int maxSumAfterPartitioningI(int[] arr, int k) {
        return dfsI(arr, k, 0, new int[arr.length]);
    }

    private static int dfsI(int[] arr, int k, int startIndex, int[] mem) {
        if(startIndex >= arr.length) return 0;
        else {
            if(mem[startIndex] != 0) return mem[startIndex];

            int rs  = Integer.MIN_VALUE;
            int max = Integer.MIN_VALUE;

            for(int i = 0; i < k; ++i) {
                if(startIndex+i >= arr.length) break;
                
                max = Math.max(max, arr[startIndex+i]);
                int leftSum  = (i+1)*max;
                int rightSum = dfsI(arr, k, startIndex+i+1, mem);

                rs = Math.max(rs, leftSum + rightSum);
            }

            mem[startIndex] = rs;
            return rs;
        }
    }

    /** Recursive approach.
        Time: O(k^n), space: O(k^n)
    */
    public static int maxSumAfterPartitioningII(int[] arr, int k) {
        return dfsII(arr, k, 0);
    }

    private static int dfsII(int[] arr, int k, int startIndex) {
        if(startIndex >= arr.length) return 0;
        else {
            int rs  = Integer.MIN_VALUE;
            int max = Integer.MIN_VALUE;

            for(int i = 0; i < k; ++i) {
                if(startIndex+i >= arr.length) break;
                
                max = Math.max(max, arr[startIndex+i]);
                int leftSum  = (i+1)*max;
                int rightSum = dfsII(arr, k, startIndex+i+1);

                rs = Math.max(rs, leftSum + rightSum);
            }

            return rs;
        }
    }
}
