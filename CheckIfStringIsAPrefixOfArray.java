import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


// leetcode 1961.
public class CheckIfStringIsAPrefixOfArray {
    public static void main(String[] args) {
        showResults("iloveleetcode", new String[] {"i","love","leetcode","apples"}); // expect true
        showResults("iloveleetcode", new String[] {"apples","i","love","leetcode"}); // expect false
        showResults("ccccccccc", new String[] {"c","cc"}); // expect false
        showResults("applebananacookie", new String[] {"banana","apple","cookie"}); // expect false
    }

    private static void showResults(String s, String[] words) {
        System.out.println("\t----ShowResults----");
        System.out.printf("s = %s, words = %s\nOUTPUT: %b\n\n", s, Arrays.toString(words), isPrefixString(s, words));
    }

    // Time: O(n), space: O(n)
    public static boolean isPrefixString(String s, String[] words) {
        StringBuilder tmpS = new StringBuilder();

        for(int i = 0; i < words.length; ++i) {
            String word = words[i];
            if(word.length() + tmpS.length() > s.length()) return false;

            int startIndex = tmpS.length();
            tmpS.append(word);
            int comp = tmpS.toString().compareTo(s);
            if(comp == 0) break;
        }
        int comp = tmpS.toString().compareTo(s);

        return comp == 0;
    }
}
