// leetcode 2710.
public class RemoveTrailingZerosFromAString {
    public static void main(String[] args) {
        assert(showResults("51230100").equals("512301")); // expect "512301"
        assert(showResults("123").equals("123")); // expect "123"
        assert(showResults("").equals("")); // expect "0"
    }

    private static String showResults(String s) {
        System.out.println("\t----ShowResults----");
        String rs = removeTrailingZeros(s);
        System.out.printf("%s\n%s\n\n", s, rs);
        return rs;
    }

    // Time: O(n), space: O(n)
    public static String removeTrailingZeros(String num) {
        final int n = num.length();
        if(n == 0) return num;

        final int ZERO = 0x30;
        boolean hasZeroAtStart = num.charAt(0) == ZERO;
        boolean hasZeroAtEnd = num.charAt(n-1) == ZERO;
        if(!hasZeroAtStart && !hasZeroAtEnd)
            return num;

        StringBuilder s = new StringBuilder(num);

        if(hasZeroAtStart) {
            int i = 0;
            while(num.charAt(i) == ZERO) i++;
            s = s.delete(0, i);
        }

        if(hasZeroAtEnd) {
            int i = n-1;
            while(num.charAt(i) == ZERO) i--;
            s = s.delete(i+1, n);
        }

        return s.toString();
    }
}
