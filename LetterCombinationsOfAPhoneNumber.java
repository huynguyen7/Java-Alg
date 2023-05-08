import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

// Elements of Programming Interview 7.7 .
// leetcode 17.
public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        String digits1 = "23";
        showResults(digits1); // expect ["ad","ae","af","bd","be","bf","cd","ce","cf"]

        String digits2 = "";
        showResults(digits2); // expect []

        String digits3 = "2";
        showResults(digits3); // expect ["a","b","c"]
    }

    private static void showResults(String digits) {
        System.out.println("\t----ShowResults----");
        List<String> rs = letterCombinations(digits);
        System.out.printf("INPUT: %s\n", digits);
        System.out.printf("OUTPUT: %s\n\n", rs.toString());
    }

    private static final String[] letters = {"","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    /*
     * n is the number of digits that map to 3 letters
     * m is the number of digits that map to 4 letters
     * Time: O(3^n * 4^m), space: O(3^n * 4^m)
     */
    public static List<String> letterCombinations(String digits) {
        if(digits.length() == 0) return Collections.emptyList();

        List<String> rs = new ArrayList<>();
        backtrack(rs, digits, new StringBuilder(), 0);

        return rs;
    }

    private static void backtrack(List<String> rs, String digits, StringBuilder holder, int startIndex) {
        if(holder.length() == digits.length()) rs.add(holder.toString()); // goal
        else {
            char c = digits.charAt(startIndex);
            int cNum = (int) c - 48;

            for(char a: letters[cNum - 1].toCharArray()) {
                holder.append(a); // Make choice.
                backtrack(rs, digits, holder, startIndex + 1); // Explore.
                holder.deleteCharAt(holder.length() - 1); // Undo choice.
            }
        }
    }
}
