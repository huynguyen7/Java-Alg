// leetcode 1758.
public class MinimumChangesToMakeAlternatingBinaryString {
    public static void main(String[] args) {
        String s1 = "0100";
        showResults(s1); // expect 1

        String s2 = "10";
        showResults(s2); // expect 0

        String s3 = "1111";
        showResults(s3); // expect 2

        String s4 = "10010100";
        showResults(s4); // expect 3
    }

    private static void showResults(String s) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s -> %d\n\n", s, minOperations(s));
    }

    // Time: O(n), space: O(1)
    public static int minOperations(String s) {
        if(s == null || s.length() == 0) return 0;
        char[] sArr = s.toCharArray();

        int count1 = 0, count2 = 0;
        char c1 = '0', c2 = '1';

        for(char c: sArr) {
            if(c == c1) count1++;
            if(c == c2) count2++;

            c1 = c1 == '0' ? '1' : '0';
            c2 = c2 == '0' ? '1' : '0';
        }

        return Math.min(count1, count2);
    }
}
