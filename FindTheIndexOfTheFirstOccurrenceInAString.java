/** Elements of Programming Interview 7.13.
 *  leetcode 28.
 *  Returns the index of the first character of the substring if found, -1
 *  Constraints: Only lowercase English alphabet characters string.
 */
public class FindTheIndexOfTheFirstOccurrenceInAString {
    public static void main(String[] args) {
        assert(showResults("a", "a") == 0); // expect 0
        assert(showResults("a", "ab") == -1); // expect -1
        assert(showResults("abc", "c") == 2); // expect 2
        assert(showResults("ababcaababcaabc", "ababcaabc") == 6); // expect 6
        assert(showResults("sadbutsad", "sad") == 0); // expect 0
        assert(showResults("leetcode", "leeto") == -1); // expect -1
    }

    private static int showResults(String haystack, String needle) {
        System.out.println("\t----ShowResults----");
        int rs = strStr(haystack, needle);;
        System.out.printf("%s %s\n%d\n\n", haystack, needle, rs);
        return rs;
    }

    /**
     * Rabin Karp Algorithm.
     * Time: O(n+m), space: O(1)
     */
    public static int strStr(String haystack, String needle) {
        final int n = haystack.length();
        final int m = needle.length();

        if(n == m) return haystack.equals(needle) ? 0 : -1;
        if(m > n) return -1;

        final int BASE = 26; // 26 English alphabet characters.
        
        int nHash = 0, mHash = 0;
        int powerN = 1;

        // Find the hashcodes for the first m characters.
        for(int i = 0; i < m; ++i) {
            powerN = i > 0 ? powerN * BASE : 1;
            nHash = nHash * BASE + haystack.charAt(i);
            mHash = mHash * BASE + needle.charAt(i);
        }

        for(int i = m; i < n; ++i) {
            // Check if hashcodes and strings are equals.
            if(mHash == nHash && haystack.substring(i-m, i).equals(needle))
                return i-m;

            // Recalculate hashcode as i move forward.
            nHash -= haystack.charAt(i-m) * powerN;
            nHash = nHash * BASE + haystack.charAt(i);
        }

        // Check if hashcodes and strings are equals.
        if(mHash == nHash && haystack.substring(n-m, n).equals(needle))
            return n-m;

        return -1;
    }

    /**
     * Naive Approach.
     * Time: O(m*n), space: O(1)
     */
    public static int strStrII(String haystack, String needle) {
        int i, j, k;
        final int n = haystack.length();
        final int m = needle.length();

        if(n == m) return haystack.equals(needle) ? 0 : -1;

        for(i = 0; i <= n-m; ++i) {
            k = 0;
            for(j = i; j < i+m; ++j) {
                if(needle.charAt(k) == haystack.charAt(j))
                    k++;
                else break;
            }

            if(k == m) return i;
        }

        return -1;
    }
}
