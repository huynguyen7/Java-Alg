// leetcode 2485.
public class FindThePivotInteger {
    public static void main(String[] args) {
        assert(showResults(8) == 6); // expect 6
        assert(showResults(1) == 1); // expect 1
        assert(showResults(4) == -1); // expect -1
    }

    private static int showResults(int n) {
        System.out.println("\t----ShowResults----");
        int rs = pivotInteger(n);
        System.out.printf("%d -> %d\n\n", n, rs);
        return rs;
    }

    /**
     * 1 <= n <= 1000
     * Time: O(n), space: O(1)
     */
    public static int pivotInteger(int n) {
        int left = 0, right = 0;
        for(int i = 1; i <= n; ++i)
            left += i;
        for(int i = n; i > 0; --i) {
            right += i;
            if(right == left) return i;
            left -= i;
        }

        return -1;
    }
}
