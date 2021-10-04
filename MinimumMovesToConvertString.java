// leetcode 2027.
public class MinimumMovesToConvertString {
    public static void main(String[] args) {
        showResults("XXX"); // expect 1
        showResults("XX0X"); // expect 2
        showResults("0000"); // expect 0
        showResults("00XX00"); // expect 1
        showResults("X00X00X00"); // expect 3
    }
    
    private static void showResults(String s) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s -> %d\n\n", s, minimumMoves(s));
    }

    /** Constraints:
     * s.length() >= 3
     * s[i] is either 'X' or 'O'.
     */

    // Greedy approach
    // Time: O(n), space: O(1)
    public static int minimumMoves(String s) {
        int i = 0, numMoves = 0;

        while(i < s.length()) {
            if(s.charAt(i) == 'X') {
                i += 3;
                numMoves += 1;
            } else i += 1;
        }

        return numMoves;
    }
}
