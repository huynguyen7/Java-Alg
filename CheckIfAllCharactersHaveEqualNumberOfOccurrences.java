// leetcode 1941.
public class CheckIfAllCharactersHaveEqualNumberOfOccurrences {
    public static void main(String[] args) {
        showResults("abacbc"); // expect true
        showResults("aaabb"); // expect false
    }

    private static void showResults(String s) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s -> %b\n\n", s, areOccurrencesEqual(s));
    }

    // Time: O(n), space: O(n)
    public static boolean areOccurrencesEqual(String s) {
        int[] count = new int[26];
        for(char c: s.toCharArray()) {
            count[(int) c - 0x61]++;
        }

        int val = -1;
        for(int i = 0; i < count.length; ++i) {
            if(count[i] == 0) continue;
            if(val == -1) val = count[i];
            else if(count[i] != val) return false;
        }

        return true;
    }
}
