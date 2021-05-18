// leetcode 1624.
public class LargestSubstringBetweenTwoEqualCharacters {
    public static void main(String[] args) {
        showResults("aa"); // expect 0
        showResults("abca"); // expect 2
        showResults("cbzxy"); // expect -1
        showResults("cabbac"); // expect 4
        showResults("aabcdeb"); // expect 3
    }

    private static void showResults(String s) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s -> %d\n\n", s, maxLengthBetweenEqualCharacters(s));
    }

    /**
     * CONSTRAINTS:
     * input s contains only lowercase English letters.
     */

    // Sliding Window approach.
    // Time: O(n), space: O(n)
    public static int maxLengthBetweenEqualCharacters(String s) {
        if(s == null || s.length() == 0) return -1;

        final int n = s.length();
        for(int i = n-2; i >= 0; --i){
            System.out.printf("-> i = %d\n", i);
            for(int j = 0; j < n-i-1; ++j)
                if(s.charAt(j) == s.charAt(j+i+1)) return i;
        }

        return -1;
    }
}
