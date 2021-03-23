import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class FindAndReplacePattern {
    public static void main(String[] args) {
        String[] words1 = {"abc","deq","mee","aqq","dkd","ccc"};
        String pattern1 = "abb";
        showResults(words1, pattern1); // expect ["mee","aqq"]

        String[] words2 = {"ef","fq","ao","at","lx"};
        String pattern2 = "ya";
        showResults(words2, pattern2); // expect ["ef","fq","ao","at","lx"]
    }

    private static void showResults(String[] words, String pattern) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(words));
        System.out.println("PATTERN: " + pattern);
        List<String> rs = findAndReplacePattern(words, pattern);
        System.out.printf("%s\n\n", rs.toString());
    }

    // Time: O(n), space: O(n)
    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        if(words == null || words.length == 0) return Collections.emptyList();

        List<String> rs = new ArrayList<>();
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        boolean isValid;

        for(int i = 0; i < words.length; ++i) {
            if(words[i].length() != pattern.length()) continue;

            isValid = true;
            for(int j = 0; j < pattern.length(); ++j) {
                char c1 = words[i].charAt(j);
                char c2 = pattern.charAt(j);

                if(!map1.containsKey(c1)) map1.put(c1, c2);
                else if(map1.get(c1) != c2) {
                    isValid = false;
                    break;
                }
                
                if(!map2.containsKey(c2)) map2.put(c2, c1);
                else if(map2.get(c2) != c1) {
                    isValid = false;
                    break;
                }

            }

            if(isValid) rs.add(words[i]);
            map1.clear();
            map2.clear();
        }
        
        return rs;
    }
}
