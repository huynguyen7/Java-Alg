// leetcode 1844.
public class ReplaceAllDigitsWithCharacters {
    public static void main(String[] args) {
        showResults("a1c1e1"); // expect "abcdef"
        showResults("a1b2c3d4e"); // expect "abbdcfdhe"
    }
    
    private static void showResults(String s) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s -> %s\n\n", s, replaceDigits(s));
    }

    /**
     * CONSTRAINTS:
     * - 0-indexed string s that has lowercase English letters in its even indices and digits in its odd indices
     * - It is guaranteed that shift(s[i-1], s[i]) will never exceed 'z'.
     */

    private static char shift(char x, int stepSize) {
        return (char) (((int) x) + stepSize);
    }

    // Time: O(n), space: O(n)
    public static String replaceDigits(String s) {
        if(s == null || s.length() == 0) return "";

        char[] cArr = s.toCharArray();
        for(int i = 1; i < cArr.length; i += 2) {
            cArr[i] = shift(cArr[i-1], (int) cArr[i]-48);
        }

        return new String(cArr);
    }
}
