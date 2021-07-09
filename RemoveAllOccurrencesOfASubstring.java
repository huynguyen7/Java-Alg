// leetcode 1910.
public class RemoveAllOccurrencesOfASubstring {
    public static void main(String[] args) {
        showResults("daabcbaabcbc", "abc"); // expect "dab"
        showResults("axxxxyyyyb", "xy"); // expect "ab"
        showResults("eemckxmckx", "emckx"); // expect ""
        showResults("ccctltctlltlb", "ctl"); // expect "b"
    }

    private static void showResults(String s, String part) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s <-> %s\n", s, part);
        System.out.printf("OUTPUT: %s\n", removeOccurrences(s, part));
    }

    // Time: O(n), space: O(n)
    public static String removeOccurrences(String s, String part) {
        if(s == null || part == null || s.length() == 0 || part.length() == 0) return s;

        StringBuilder rs = new StringBuilder(s);
        final int partLength = part.length();
        int length = s.length(), i = 0;

        while(i <= length-partLength) {
            String sub = rs.substring(i, i+partLength);
            
            if(sub.compareTo(part) == 0) {
                rs.delete(i, i+partLength);
                length -= partLength;
                i = 0;
                continue;
            }
            i++;
        }

        return rs.toString();
    }
}
