import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

// leetcode 1002.
public class FindCommonCharacters {
    public static void main(String args[]) {
        String[] s1 = { "bella", "label", "roller" };
        showResults(s1); // expect ["e","l","l"]

        String[] s2 = { "cool", "lock", "cook" };
        showResults(s2); // expect ["c", "0"]
    }

    private static void showResults(String[] A) {
        System.out.println("----ShowResults----");
        System.out.println(Arrays.toString(A));
        List<String> rs = commonChars(A);
        System.out.println(rs.toString() + "\n");
    }

    // time: O(n), space: O(A[0].length())
    // n is total characters of all string in A[]
    public static List<String> commonChars(String[] A) {
        List<String> rs = new ArrayList<>();
        if (A.length == 1)
            return rs;

        int[] counts = new int[26]; // 26 alphabet characters
        for (int i = 0; i < A[0].length(); ++i) // count letter freq for first string in A
            counts[(int) A[0].charAt(i) - 'a']++;

        for (int i = 1; i < A.length; ++i) {
            int[] tmpCounts = new int[26];
            for (int j = 0; j < A[i].length(); ++j)
                tmpCounts[(int) A[i].charAt(j) - 'a']++;
            for (int j = 0; j < tmpCounts.length; ++j)
                counts[j] = Math.min(counts[j], tmpCounts[j]);
        }

        for (int i = 0; i < counts.length; ++i)
            while (counts[i] != 0) {
                rs.add(String.valueOf((char) (i + 'a')));
                counts[i]--;
            }

        return rs;
    }
}

// constraints:
// A[i][j] is a lowercase letter (alphabet characters)
