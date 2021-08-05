import java.util.Arrays;

// leetcode 942.
public class DIStringMatch {
    public static void main(String[] args) {
        showResults("IDID"); // expect [0,4,1,3,2]
        showResults("III"); // expect [0,1,2,3]
        showResults("DDI"); // expect [3,2,0,1]
    }

    private static void showResults(String s) {
        System.out.println("\t----ShowResults----");
        System.out.println("INPUT: " + s);
        System.out.println("OUTPUT: " + Arrays.toString(diStringMatch(s)) + "\n");
    }

    // Time: O(n), space: O(n)
    public static int[] diStringMatch(String s) {
        final int n = s.length();
        int[] rs = new int[n+1];
        int low = 0, high = n;

        for(int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if(c == 'I') rs[i] = low++;
            else rs[i] = high--;
        }

        rs[n] = high;

        return rs;
    }
}
