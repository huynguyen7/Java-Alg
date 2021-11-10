import java.util.Set;
import java.util.HashSet;

// leetcode 2062.
public class CountVowelSubstringsOfAString {
    public static void main(String[] args) {
        showResults("aeiouu"); // expect 2
        showResults("unicornarihan"); // expect 0
        showResults("cuaieuouac"); // expect 7
        showResults("bbaeixoubb"); // expect 0
    }

    private static void showResults(String word) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s\n%d\n\n", word, countVowelSubstrings(word));
    }

    // Time: O(n^2), space: O(n)
    public static int countVowelSubstrings(String word) {
        final int n = word.length();
        int count = 0;
        Set<Character> set = new HashSet<>();

        for(int i = 0; i < n; ++i) {
            for(int j = i; j < n; ++j) {
                char c = word.charAt(j);
                if(!isVowel(c)) break;
                set.add(c);
                if(set.size() == 5) count++;
            }
            set.clear();
        }

        return count;
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
