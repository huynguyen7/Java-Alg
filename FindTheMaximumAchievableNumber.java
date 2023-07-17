// leetcode 2769.
public class FindTheMaximumAchievableNumber {
    public static void main(String[] args) {
        assert(showResults(4,1) == 6); // expect 6
        assert(showResults(3,2) == 7); // expect 7
    }

    private static int showResults(int num, int t) {
        System.out.println("\t----ShowResults----");
        int rs = theMaximumAchievableX(num, t);
        System.out.printf("%d %d -> %d\n\n", num, t, rs);
        return rs;
    }

    // Time: O(1), space: O(1)
    public static int theMaximumAchievableX(int num, int t) {
        return num+t*2;
    }
}
