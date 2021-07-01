import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

// leetcode 1897.
public class RedistributeCharactersToMakeAllStringsEqual {
    public static void main(String[] args) {
        String[] words1 = {"abc","aabc","bc"};
        showResults(words1); // expect true

        String[] words2 = {"ab","a"};
        showResults(words2); // expect false

        String[] words3 = {"b","a"};
        showResults(words3); // expect false

        String[] words4 = {"b"};
        showResults(words4); // expect true

        String[] words5 = {"asdf"};
        showResults(words5); // expect true
    }
    
    private static void showResults(String[] words) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(words));
        System.out.printf("Result: %b\n\n", makeEqual(words));
    }

    // Time: O(n), space: O(n)
    public static boolean makeEqual(String[] words) {
        if(words == null || words.length < 2) return true;

        Map<Character, Integer> map = new HashMap<>();
        for(String word: words) {
            for(char c: word.toCharArray())
                map.put(c, map.getOrDefault(c,0)+1);
        }
        
        for(Integer charCount: map.values()) {
            if(charCount % words.length != 0)
                return false;
        }
        
        return true;
    }
}
