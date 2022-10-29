import java.util.*;

// leetcode 2325.
public class DecodeTheMessage {
    public static void main(String[] args) {
        assert(showResults("the quick brown fox jumps over the lazy dog",
                    "vkbs bs t suepuv").compareTo(
                        "this is a secret") == 0); // expect "this is a secret"
        assert(showResults("eljuxhpwnyrdgtqkviszcfmabo",
                    "zwx hnfx lqantp mnoeius ycgk vcnjrdb").compareTo(
                        "the five boxing wizards jump quickly") == 0); // expect "the five boxing wizards jump quickly"
    }

    private static String showResults(String key, String message) {
        System.out.println("\t----ShowResults----");
        String rs = decodeMessage(key, message);
        System.out.printf("%s\n\n", rs);
        return rs;
    }

    // Time: O(n), space: O(1)
    public static String decodeMessage(String key, String message) {
        Map<Character, Integer> seen = new HashMap<Character, Integer>();

        int seq = 0;
        for(char c: key.toCharArray()) {
            if(seq == 26) break;
            if(c != ' ' && !seen.containsKey(c)) {
                seen.put(c, seq);
                seq += 1;
            }
        }

        StringBuilder s = new StringBuilder();

        for(char c: message.toCharArray()) {
            char mapped = ' ';
            if(c != ' ') {
                mapped = (char) ((int) seen.get(c) + 0x61);
            }
            s.append(mapped);
        }

        return s.toString();
    }
}
