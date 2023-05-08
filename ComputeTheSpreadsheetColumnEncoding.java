// Elements of Programming Interview 7.3
public class ComputeTheSpreadsheetColumnEncoding {
    public static void main(String[] args) {
        assert(showResults("A") == 1);
        assert(showResults("D") == 4);
        assert(showResults("ZY") == 701);
        assert(showResults("ZZ") == 702);
        assert(showResults("YZ") == 676);
    }

    private static int showResults(String s) {
        System.out.println("\t----ShowResults----");
        int rs = computeTheSpreadsheetColumnEncoding(s);
        System.out.printf("%s -> %d\n\n", s, rs);
        return rs;
    }

    // Time: O(n), space: O(n)
    public static int computeTheSpreadsheetColumnEncoding(String s) {
        int rs = 0;
        char[] c = s.toCharArray();
        final int n = c.length;

        for(int i = n-1; i >= 0; --i) {
            int cValue = (int) c[i] - 0x40;
            rs += (int) Math.pow(26, n-1-i) * cValue;
        }

        return rs;
    }
}
