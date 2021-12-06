import java.util.*;

// leetcode 1641.
public class CountSortedVowelStrings {
    public static void main(String[] args) {
        assert(showResults(1) == 5); // expect 5
        assert(showResults(2) == 15); // expect 15
        assert(showResults(33) == 66045); // expect 66045
    }

    private static int showResults(int n) {
        System.out.println("\t----ShowResults----");
        int rs = countVowelStrings(n);
        System.out.printf("%d -> %d\n\n", n, rs);
        return rs;
    }

    /** DP approach.
     * Bottom-up DP.
     * Time: O(n), space: O(1)
     */
    public static int countVowelStrings(int n) {
        /* 4='a', 3='e', 2='i', 1='o', 0='u' */
        int[] mem = new int[5];
        mem[0] = 1; // Base case.

        for(int len = 0; len < n; ++len) {
            for(int c = 1; c < 5; ++c)
                mem[c] += mem[c-1];
        }
        
        return Arrays.stream(mem)
                .reduce(Integer::sum)
                .getAsInt();
    }

    /** DP approach.
     * Top-down DP.
     * This cost n space complexity for the recursive stack call.
     * Time: O(n), space: O(n)
     */
    public static int countVowelStringsI(int n) {
        /* 4='a', 3='e', 2='i', 1='o', 0='u' */
        int[] mem = new int[5];

        mem[0] = 1; // Base case.
        dfsI(n, mem);


        return Arrays.stream(mem)
                .reduce(Integer::sum)
                .getAsInt();
    }

    private static void dfsI(int n, int[] mem) {
        if(n == 0) return;
        else {
            for(int c = 1; c < 5; ++c)
                mem[c] += mem[c-1];
            dfsI(n-1, mem);
        }
    }

    /** Recursive approach.
        Time: O(5^n), space: O(5^n)
    */
    public static int countVowelStringsII(int n) {
        /* 0='a', 1='e', 2='i', 3='o', 4='u' */
        int count = 0;

        for(int c = 0; c < 5; ++c)
            count += dfsII(c, n-1); // We already use one char, so n-1 as input.
        return count;
    }

    private static int dfsII(int c, int n) {
        if(n == 0) return 1; // Base case.
        else {
            switch(c) {
                case 0:  // c == 'a'
                    return dfsII(0, n-1) + dfsII(1, n-1) + dfsII(2, n-1)
                        + dfsII(3, n-1) + dfsII(4, n-1);
                case 1:  // c == 'e'
                    return dfsII(1, n-1) + dfsII(2, n-1) + dfsII(3, n-1)
                        + dfsII(4, n-1);
                case 2:  // c == 'i'
                    return dfsII(2, n-1) + dfsII(3, n-1) + dfsII(4, n-1);
                case 3:  // c == 'o'
                    return dfsII(3, n-1) + dfsII(4, n-1);
                default: // c == 'u'
                    return dfsII(4, n-1);
            }
        }
    }
}
