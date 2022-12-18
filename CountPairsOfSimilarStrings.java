import java.util.*;

// leetcode 2506.
public class CountPairsOfSimilarStrings {
    public static void main(String[] args) {
        assert(showResults(new String[] {"aba","aabb","abcd","bac","aabc"}) == 2); // expect 2
        assert(showResults(new String[] {"aabb","ab","ba"}) == 3); // expect 3
        assert(showResults(new String[] {"nba","cba","dba"}) == 0); // expect 0
    }

    private static int showResults(String[] words) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(words));
        int rs = similarPairs(words);
        System.out.printf("%d\n\n", rs);
        return rs;
    }

    /**
     * n is number of words
     * Time: O(n), space: O(n)
     */
    public static int similarPairs(String[] words) {
        final int n = words.length;
        int rs = 0;
        
        Map<Integer, Set<Character>> map = new HashMap<>();
        for(int i = 0; i < n; ++i) {
            String word = words[i];
            Set<Character> chars = new HashSet<>();
            for(char c: word.toCharArray())
                chars.add(c);
            map.put(i, chars);
        }

        for(int i = 0; i < n; ++i) {
            Set<Character> iChars = map.get(i);
            for(int j = i+1; j < n; ++j) {
                Set<Character> jChars = map.get(j);
                if(iChars.size() != jChars.size()) continue;
                boolean flag = true;
                for(char c: iChars) {
                    if(!jChars.contains(c)) {
                        flag = false;
                        break;
                    }
                }
                if(flag) rs += 1;
            }
        }

        return rs;
    }
}
