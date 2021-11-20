// leetcode 2068.
public class CheckWhetherTwoStringsAreAlmostEquivalent {
    public static void main(String[] args) {
        showResults("aaaa", "bccb"); // expect false
        showResults("abcdeef", "abaaacc"); // expect true
        showResults("cccddabba", "babababab"); // expect true 
    }

    private static void showResults(String word1, String word2) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s %s\n", word1, word2);
        System.out.printf("%b\n\n", checkAlmostEquivalent(word1, word2));
    }

    // Time: O(n), space: O(1)
    public static boolean checkAlmostEquivalent(String word1, String word2) {
        short[] freq = new short[26];

        for(int i = 0; i < word1.length(); ++i) {
            int c = word1.charAt(i)-0x61;
            freq[c]++;
        }

        for(int i = 0; i < word2.length(); ++i) {
            int c = word2.charAt(i)-0x61;
            freq[c]--;
        }

        for(int i = 0; i < 26; ++i) {
            if(Math.abs(freq[i]) > 3)
                return false;
        }

        return true;
    }
}
