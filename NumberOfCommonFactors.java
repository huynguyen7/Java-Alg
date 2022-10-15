// leetcode 2427.
public class NumberOfCommonFactors {
    public static void main(String[] args) {
        assert(showResults(12, 6) == 4); // expect 4
        assert(showResults(25, 30) == 2); // expect 2
    }

    private static int showResults(int a, int b) {
        System.out.println("\t----ShowResults----");
        int rs = commonFactors(a, b);
        System.out.printf("%d %d -> %d\n", a, b, rs);
        return rs;
    }

    // Time: O(min(a,b)), space: O(1)
    public static int commonFactors(int a, int b) {
        int val = Math.min(a, b);
        int count = a % b == 0 || b % a == 0 ? 1 : 0;
        for(int i = 1; i < val; ++i) {
            if(a % i == 0 && b % i == 0)
                count += 1;
        }

        return count;
    }
}
