import java.util.HashMap;
import java.util.Map;

//leetcode 389.
public class FindTheDifference {
    public static void main(String args[]) {
        String s1 = "abcd";
        String t1 = "abcde";
        showResults(s1, t1); // expect 'e'

        showResults("abf", "abfe"); // expect 'e'

    }

    private static void showResults(String s, String t) {
        System.out.println("----ShowResults----");
        System.out.printf("s: %s, t: %s\n", s, t);
        System.out.printf("Result: %c\n\n", findTheDifference(s, t));
    }

    // time: O(n), space: O(1)
    public static char findTheDifference(String s, String t) {
        char rs = t.charAt(t.length() - 1);
        for (int i = 0; i < s.length(); ++i)
            rs += t.charAt(i) - s.charAt(i);

        return rs;
    }
}
