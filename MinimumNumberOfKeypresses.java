import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

// leetcode 2268.
public class MinimumNumberOfKeypresses {
    public static void main(String[] args) {
        assert(showResults("apple") == 5); // expect 5
        assert(showResults("abcdefghijkl") == 15); // expect 15
        assert(showResults("aaaaaaaabcdefgggghijkllllllllllmmmnoppponono") == 51); // expect 51
    }

    private static int showResults(String s) {
        System.out.println("\t----ShowResults----");
        int rs = minimumKeypresses(s);
        System.out.printf("%s -> %d\n\n", s, rs);
        return rs;
    }

    // Time: O(nlogn), space: O(1)
    public static int minimumKeypresses(String s) {
        char[] cArr = s.toCharArray();
        Integer[] counts = new Integer[26];
        Arrays.fill(counts, 0);
        int rs = 0, numPresses = 1;
        
        for(int i = 0; i < s.length(); ++i) {
            char c = cArr[i];
            counts[c-0x61]++;
        }

        Arrays.sort(counts, (a, b) -> Integer.compare(b, a));

        for(int i = 0; i < 26; ++i) {
            if(i != 0 && i % 9 == 0) numPresses += 1;
            rs += numPresses * counts[i];
        }

        return rs;
    }
}
