import java.util.Arrays;

// leetcode 1433.
public class CheckIfAStringCanBreakAnotherString {
    public static void main(String[] args) {
        showResults("abc", "xya"); // expect true
        showResults("abe", "acd"); // expect false
        showResults("leetcodee", "interview"); // expect true
    }
    
    private static void showResults(String s1, String s2) {
        System.out.println("\t----ShowResults----");
        System.out.printf("S1: %s\nS2: %s\n", s1, s2);
        boolean rs = checkIfCanBreak(s1, s2);
        System.out.printf("RESULTS: %b\n\n", rs);
    }

    // GREEDY APPROACH.
    // n = s1.length() = s2.length()
    // Time: O(nlogn), space: O(n)
    public static boolean checkIfCanBreak(String s1, String s2) {
        if(s1.length() != s2.length()) return false;

        char[] tmp1 = s1.toCharArray();
        Arrays.sort(tmp1);
        char[] tmp2 = s2.toCharArray();
        Arrays.sort(tmp2);

        boolean check = true;
        for(int i = 0; i < tmp1.length; ++i) {
            if(tmp1[i] < tmp2[i]) {
                check = false;
                break;
            }
        }
        if(check) return true;

        check = true;
        for(int i = 0; i < tmp2.length; ++i) {
            if(tmp2[i] < tmp1[i]) {
                check = false;
                break;
            }
        }

        return check;
    }
}
