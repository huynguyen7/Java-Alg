// leetcode 1869.
public class LongerContiguousSegmentsOfOnesThanZeros {
    public static void main(String[] args) {
        showResults("1101"); // expect true
        showResults("111000"); // expect false
        showResults("110100010"); // expect false
    }

    private static void showResults(String s) {
        System.out.println("\t----ShowResults----");
        System.out.printf("INPUT: %s\nOUTPUT: %b\n\n", s, checkZeroOnes(s));
    }

    // Time: O(n), space: O(1)
    public static boolean checkZeroOnes(String s) {
        if(s == null || s.length() == 0) return false;

        int startOne = 0, startZero = 0, i = 0;
        boolean isOne = false;
        int maxOnes = 0, maxZeros = 0;
        for(i = 0; i < s.length(); ++i) {
            int num = (int) s.charAt(i)-48;
            if(i == 0) isOne = num == 1 ? true : false;
            if(num == 0 && isOne) {
                isOne = false;
                maxOnes = Math.max(maxOnes, i-startOne);
                startZero = i;
            } else if(num == 1 && !isOne) {
                isOne = true;
                maxZeros = Math.max(maxZeros, i-startZero);
                startOne = i;
            }
        }

        if(isOne) maxOnes = Math.max(maxOnes, i-startOne);
        else maxZeros = Math.max(maxZeros, i-startZero);

        return maxOnes-maxZeros > 0;
    }
}
