// leetcode 1876.
public class SubstringOfSizeThreeWithDistinctCharacters {
    public static void main(String[] args) {
        showResults("xyzzaz"); // expect 1
        showResults("aababcabc"); // expect 4
    }

    private static void showResults(String s) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s -> %d\n\n", s, countGoodSubstrings(s));
    }

    // Time: O(n), space: O(1)
    public static int countGoodSubstrings(String s) {
        if(s == null || s.length() < 3) return 0;
        int count = 0;
        char a = s.charAt(0), b = s.charAt(1), c = s.charAt(2);
        for(int i = 3; i < s.length(); ++i) {
            if(a != c && a != b && b != c) count++;
            a = b;
            b = c;
            c = s.charAt(i);
        }
        if(a != c && a != b && b != c) count++;

        return count;
    }
}
