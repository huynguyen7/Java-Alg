// leetcode 2042.
public class CheckIfNumbersAreAscendingInASentence {
    public static void main(String[] args) {
        showResults("1 box has 3 blue 4 red 6 green and 12 yellow marbles"); // expect true
        showResults("hello world 5 x 5"); // expect false
        showResults("sunset is at 7 51 pm overnight lows will be in the low 50 and 60 s"); // expect false
        showResults("4 5 11 26"); // expect true
    }

    private static void showResults(String s) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s\n%b\n\n", s, areNumbersAscending(s));
    }

    /**
     * Constraints:
     * 3 <= s.length <= 200
     * s consists of lowercase English letters, spaces, and digits from 0 to 9, inclusive.
     * The number of tokens in s is between 2 and 100, inclusive.
     * The tokens in s are separated by a single space.
     * There are at least two numbers in s.
     * Each number in s is a positive number less than 100, with no leading zeros.
     * s contains no leading or trailing spaces.
     */

    // Time: O(n), space: O(n)
    public static boolean areNumbersAscending(String s) {
        String[] tokens = s.split(" ");
        boolean flag = true;

        int prevVal = Integer.MIN_VALUE;
        for(String token: tokens) {
            if(!isNum(token)) continue;
            int val = Integer.parseInt(token);
            flag &= val > prevVal;
            if(!flag) break;
            prevVal = val;
        }

        return flag;
    }

    private static boolean isNum(String s) {
        for(int i = 0; i < s.length(); ++i)
            if(!Character.isDigit(s.charAt(i))) return false;
        return true;
    }
}
