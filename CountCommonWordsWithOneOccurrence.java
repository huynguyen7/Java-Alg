import java.util.*;

// leetcode 2085.
public class CountCommonWordsWithOneOccurrence {
    public static void main(String[] args) {
        showResults(new String[] {"leetcode","is","amazing","as","is"},
                new String[] {"amazing","leetcode","is"}); // expect 2

        showResults(new String[] {"b","bb","bbb"},
                new String[] {"a","aa","aaa"}); // expect 0

        showResults(new String[] {"a","ab"},
                new String[] {"a","a","a","ab"}); // expect 1
    }

    private static void showResults(String[] words1, String[] words2) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s\n%s\n%d\n\n", Arrays.toString(words1), Arrays.toString(words2),
                        countWords(words1, words2));
    }

    // n = words1.length, m = words2.length
    // Time: O(n+m), space: O(n+m)
    public static int countWords(String[] words1, String[] words2) {
        int count = 0;
        
        Map<String, Integer> m1 = new HashMap<>();
        Map<String, Integer> m2 = new HashMap<>();

        for(int i = 0; i < words1.length; ++i)
            m1.put(words1[i], m1.getOrDefault(words1[i],0)+1);

        for(int i = 0; i < words2.length; ++i)
            m2.put(words2[i], m2.getOrDefault(words2[i],0)+1);

        for(Map.Entry<String, Integer> entry: m1.entrySet()) {
            if(entry.getValue() == 1 &&
                m2.containsKey(entry.getKey()) &&
                m2.get(entry.getKey()) == 1)
                count++;
        }

        return count;
    }
}
