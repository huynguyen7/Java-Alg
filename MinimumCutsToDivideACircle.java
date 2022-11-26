import java.util.Arrays;

// leetcode 2481.
public class MinimumCutsToDivideACircle {
    public static void main(String[] args) {
        assert(showResults(4) == 2); // expect 2
        assert(showResults(3) == 3); // expect 3
        assert(showResults(1) == 0); // expect 0
        assert(showResults(2) == 1); // expect 1
        assert(showResults(8) == 4); // expect 4
    }

    private static int showResults(int n) {
        System.out.println("\t----ShowResults----");
        int rs = numberOfCuts(n);
        System.out.printf("%d -> %d\n\n", n, rs);
        return rs;
    }

    // Time: O(1), space: O(1)
    public static int numberOfCuts(int n) {
        if(n % 2 == 0) return n/2;
        return n == 1 ? 0 : n;
    }
}
