import java.util.Arrays;

// leetcode 338.
public class CountingBits {
    public static void main(String[] args) {
        showResults(2); // expect [0,1,1]
        showResults(5); // expect [0,1,1,2,1,2]
        showResults(3); // expect [0,1,1,2]
    }

    private static void showResults(int num) {
        System.out.println("\t----ShowResults----");
        System.out.printf("NUM: %d\n", num);
        int[] rs = countBits(num);
        System.out.printf("RESULTS: %s\n\n", Arrays.toString(rs));
    }

    // DP approach.
    // Bottom-up DP.
    // Time: O(n), space: O(n)
    public static int[] countBits(int num) {
        int[] rs = new int[num+1];
        if(num >= 1) rs[1] = 1;
        for(int i = 1; i <= num; ++i) {
            if(i%2 == 0) rs[i] = rs[i/2];
            else rs[i] = rs[i-1]+1;
        }

        return rs;
    }

    // Recursive approach.
    // Time: O(nlogn), space: O(n)
    public static int[] countBitsII(int num) {
        int[] rs = new int[num+1];
        for(int i = 0; i <= num; ++i)
            rs[i] = dfsII(rs, i);

        return rs;
    }

    private static int dfsII(int[] rs, int num) {
        if(num <= 1) return num;
        else if(rs[num] != 0) return rs[num];
        else return num%2 + dfsII(rs, num/2);
    }
}

/*
 * Constraints:
 * 0 <= num <= 10^5
 */
