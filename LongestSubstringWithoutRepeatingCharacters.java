import java.util.Set;
import java.util.HashSet;

// leetcode 3.
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        assert(showResults("abcabcbb") == 3); // expect 3
        assert(showResults("bbbbb") == 1); // expect 1
        assert(showResults("pwwkew") == 3); // expect 3
    }

    private static int showResults(String s) {
        System.out.println("\t----ShowResults----");
        int rs = lengthOfLongestSubstring(s);
        System.out.printf("%s\n%d\n\n", s, rs);
        return rs;
    }

    // Time: O(n^2), space: O(1)
    public static int lengthOfLongestSubstring(String s) {
        final int n = s.length();
        if(n <= 1) return n;
        else if(n == 2) return s.charAt(0) != s.charAt(1) ? 2 : 1;
        
        Set<Character> set = new HashSet<>();
        int rs = 1;
        int left = 0, right = 0;
        
        while(right < n) {
            char a = s.charAt(left);
            char b = s.charAt(right);

            if(set.contains(b)) {
                set.remove(a);
                left += 1;
            } else {
                set.add(b);
                right += 1;
            }
            rs = Math.max(rs, set.size());
        }
        
        return rs;
    }

    // Brute-force approach.
    // Time: O(n^2), space: O(n)
    public static int lengthOfLongestSubstringII(String s) {
        Set<Character> set = new HashSet<>();
        int rs = 1;
        for(int i = 0; i < s.length(); ++i)
            rs = Math.max(rs, backtrack(s, set, i));
        return rs;
    }
    
    private static int backtrack(String s, Set<Character> set, int currIndex) {
        if(currIndex >= s.length() || set.contains(s.charAt(currIndex)))
            return set.size();
        // Make choice
        set.add(s.charAt(currIndex));
        
        // Explore
        int rs = backtrack(s, set, currIndex+1);
        
        // Undo choice
        set.remove(s.charAt(currIndex));
        return rs;
    }
}
