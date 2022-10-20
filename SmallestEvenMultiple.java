// leetcode 2413.
public class SmallestEvenMultiple {
    public static void main(String[] args) {
        assert(showResults(5) == 10); // expect 10 
        assert(showResults(6) == 6); // expect 6
        assert(showResults(1) == 2); // expect 2
    }

    private static int showResults(int n) {
        System.out.println("\t----ShowResults----");
        int rs = smallestEvenMultiple(n);
        System.out.printf("%d %d\n", n, rs);
        return rs;
    }

    // Time: O(), space: O()
    public static int smallestEvenMultiple(int n) {
        if(n <= 2) return 2;
        if(n % 2 == 0) return n;
        return n*2;
    }
}
