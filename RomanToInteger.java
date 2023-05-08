import java.util.*;

// Elements of Programming Interview 7.9 .
// leetcode 13.
public class RomanToInteger {
    public static void main(String[] args) {
        assert(showResult("III") == 3); // expect 3
        assert(showResult("LVIII") == 58); // expect 58
        assert(showResult("MCMXCIV") == 1994); // expect 1994
    }

    private static int showResult(String s) {
        System.out.println("\t----ShowResults----");
        int rs = romanToInt(s);
        System.out.printf("%s -> %d\n\n", s, rs);
        return rs;
    }

    // Time: O(n), space: O(1)
    public static int romanToInt(String s) {
        final int n = s.length();
        int rs = 0;
        Map<Character, Integer> values = getRomanValuesTable();

        for(int i = 0; i < n; ++i) {
            if(i+1 < n && values.get(s.charAt(i)) < values.get(s.charAt(i+1))) {
                rs += values.get(s.charAt(i+1)) - values.get(s.charAt(i));
                i += 1;
            } else rs += values.get(s.charAt(i));
        }

        return rs;
    }

    private static Map<Character, Integer> getRomanValuesTable() {
        Map<Character, Integer> values = new HashMap<>();
        values.put('I', 1);
        values.put('V', 5);
        values.put('X', 10);
        values.put('L', 50);
        values.put('C', 100);
        values.put('D', 500);
        values.put('M', 1000);

        return values;
    }
}
