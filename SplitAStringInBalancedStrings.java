// leetcode 1221.
public class SplitAStringInBalancedStrings {
    public static void main(String[] args) {
        showResults("RLRRLLRLRL"); // expect 4
        showResults("RLLLLRRRLR"); // expect 3
        showResults("LLLLRRRR"); // expect 1
        showResults("RLRRRLLRLL"); // expect 2
    }

    private static void showResults(String s) {
        System.out.println("\t----ShowResults----");
        int rs = balancedStringSplit(s);
        System.out.printf("%s\n%d\n\n", s, rs);
    }

    // GREEDY APPROACH.
    // Time: O(n), space: O(1)
    public static int balancedStringSplit(String s) {
        if(s == null || s.length() == 0) return 0;

        int L = 0, R = 0, count = 0;
        for(int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if(c == 'L') L++;
            else R++; // c == 'R'

            if(L == R) count++;
        }

        return count;
    }
}
