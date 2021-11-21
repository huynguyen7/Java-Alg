import java.util.*;

// leetcode 438.
public class FindAllAnagramsInAString {
    public static void main(String[] args) {
        showResults("cbaebabacd", "abc"); // expect [0,6]
        showResults("abab", "ab"); // expect [0,1,2]
    }

    private static void showResults(String s, String p) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s %s\n%s\n\n", s, p, findAnagrams(s, p).toString());
    }

    /**
     * Constraints:
     * 1 <= s.length, p.length <= 3 * 104
     * s and p consist of lowercase English letters.
     */

    private static final int alphabetSize = 26;

    // Time: O(n), space: O(1)
    public static List<Integer> findAnagrams(String s, String p) {
        if(s.length() < p.length()) return Collections.emptyList();

        List<Integer> rs = new ArrayList<>();
        int[] sCount = new int[alphabetSize];
        int[] pCount = new int[alphabetSize];

        int c;
        for(int i = 0; i < p.length(); ++i) {
            c = p.charAt(i)-0x61;
            pCount[c]++;
        }

        int left = 0, right = 0;
        // Sliding window.
        while(right < s.length()) {
            c = s.charAt(right)-0x61;
            sCount[c]++;
            right++;

            // Window check.
            if(right-left == p.length()) {
                if(canConstructSameAnagram(sCount, pCount))
                    rs.add(left);

                c = s.charAt(left)-0x61;
                sCount[c]--;
                left++;
            }
        }

        return rs;
    }

    private static boolean canConstructSameAnagram(int[] a, int [] b) {
        for(int i = 0; i < alphabetSize; ++i)
            if(a[i] != b[i]) return false;
        return true;
    }
}
