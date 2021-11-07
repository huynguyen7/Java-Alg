import java.util.Map;
import java.util.HashMap;

// leetcode 383.
public class RansomNote {
    public static void main(String[] args) {
        showResults("a", "b"); // expect false.
        showResults("aa", "ab"); // expect false.
        showResults("aa", "aab"); // expect true.
    }

    private static void showResults(String ransomNote, String magazine) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s %s\n->%b\n\n", ransomNote, magazine, canConstruct(ransomNote, magazine));
    }

    // Time: O(n), space: O(n)
    public static boolean canConstruct(String ransomNote, String magazine) {
        if(ransomNote == null || magazine == null) return false;
        Map<Character, Integer> countFreqMagazine = new HashMap<>();
        Map<Character, Integer> countFreqRansomNote = new HashMap<>();

        for(char c: ransomNote.toCharArray())
            countFreqRansomNote.put(c, countFreqRansomNote.getOrDefault(c, 0)+1);

        for(char c: magazine.toCharArray())
            countFreqMagazine.put(c, countFreqMagazine.getOrDefault(c, 0)+1);

        for(Map.Entry<Character, Integer> pair: countFreqRansomNote.entrySet()) {
            if(!countFreqMagazine.containsKey(pair.getKey())
                || countFreqMagazine.get(pair.getKey()) < pair.getValue())
                return false;
        }

        return true;
    }
}
