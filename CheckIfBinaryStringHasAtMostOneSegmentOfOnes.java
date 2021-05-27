// leetcode 1784.
public class CheckIfBinaryStringHasAtMostOneSegmentOfOnes {
    public static void main(String[] args) {
        showResults("1001"); // expect false
        showResults("110"); // expect true
    }

    private static void showResults(String s) {
        System.out.println("\t----ShowResults----");
        System.out.printf("INPUT: %s\nOUTPUT: %b\n\n", s, checkOnesSegment(s));
    }

    // Time: O(n), space: O(1)
    public static boolean checkOnesSegment(String s) {
        if(s == null || s.length() == 0) return false;

        boolean flag = true;
        for(int i = 1;  i < s.length(); ++i) {
            int num = (int) s.charAt(i)-48;
            if(num == 0) flag = false;
            if(num == 1 && !flag) return false;
        }

        return true;
    }
}
