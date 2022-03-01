import java.util.Arrays;

// leetcode 2185.
public class CountingWordsWithAGivenPrefix {
    public static void main(String[] args) {
        assert(showResults(new String[] {"pay","attention","practice","attend"}, "at") == 2); // expect 2
        assert(showResults(new String[] {"leetcode","win","loops","success"}, "code") == 0); // expect 0
    }

    private static int showResults(String[] words, String pref) {
        System.out.println("\t----ShowResults----");
        int rs = prefixCount(words, pref);
        System.out.printf("%s\n%s -> %d\n\n", Arrays.toString(words), pref, rs);
        return rs;
    }

    // Time: O(n), space: O(1)
    public static int prefixCount(String[] words, String pref) {
        final int prefixLength = pref.length();
        int count = 0;
        for(String word: words) {
            int n = word.length();
            if(n < prefixLength) continue;

            boolean isOk = true;
            for(int i = 0; i < prefixLength; ++i) {
                if(word.charAt(i) != pref.charAt(i)) {
                    isOk = false;
                    break;
                }
            }

            if(isOk) count++;
        }

        return count;
    }
}
