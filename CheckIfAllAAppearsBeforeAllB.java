// leetcode 2124.
public class CheckIfAllAAppearsBeforeAllB {
    public static void main(String[] args) {
        assert(showResults("aaabbb")); // expect true.
        assert(!showResults("abab")); // expect false.
        assert(showResults("bbb")); // expect true.
    }

    private static boolean showResults(String s) {
        boolean rs = checkString(s);
        System.out.println("\t----ShowResults----");
        System.out.printf("%s -> %b\n\n", s, rs);
        return rs;
    }

    // Time: O(n), space: O(1)
    public static boolean checkString(String s) {
        boolean ifB = false;
        for(int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if(c == 'b') ifB = true;
            else if(ifB) return false;
        }

        return true;
    }
}
