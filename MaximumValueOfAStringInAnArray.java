import java.util.Arrays;

// leetcode 2496.
public class MaximumValueOfAStringInAnArray {
    public static void main(String[] args) {
        assert(showResults(new String[] {"alic3","bob","3","4","00000"}) == 5); // expect 5
        assert(showResults(new String[] {"1","01","001","0001"}) == 1); // expect 1
    }

    private static int showResults(String[] strs) {
        System.out.println("\t----ShowResults----");
        int rs = maximumValue(strs);
        System.out.printf("%s\n-> %d\n\n", Arrays.toString(strs), rs);
        return rs;
    }

    /**
     * Time: O(n), space: O(1)
     */
    public static int maximumValue(String[] strs) {
        int maxValue = Integer.MIN_VALUE;
        for(String s: strs)
            maxValue = Math.max(maxValue, getStringValue(s));

        return maxValue;
    }

    private static int getStringValue(String s) {
        if(s.length() == 0) return 0;
        else if(stringContainsLetter(s)) return s.length();
        else return Integer.parseInt(s);
    }

    private static boolean stringContainsLetter(String s) {
        for(char c: s.toCharArray()) {
            if(Character.isAlphabetic(c))
                return true;
        }
        return false;
    }
}
