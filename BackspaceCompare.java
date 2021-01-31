import java.util.Deque;
import java.util.LinkedList;

// leetcode 844
public class BackspaceCompare {
    public static void main(String[] args) {
        String s1 = "ab#c";
        String t1 = "ad#c";
        showResults(s1, t1); // expect true

        String s2 = "ab##";
        String t2 = "c#d#";
        showResults(s2, t2); // expect true

        String s3 = "a##c";
        String t3 = "#a#c";
        showResults(s3, t3); // expect true

        String s4 = "a#c";
        String t4 = "b";
        showResults(s4, t4); // expect false

        String s5 = "y#fo##f";
        String t5 = "y#f#o##f";
        showResults(s5, t5); // expect true
    }

    private static void showResults(String s, String t) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s - %s\n", s, t);
        boolean rs = backspaceCompare(s, t);
        System.out.printf("Results = %b\n\n", rs);
    }

    // Time: O(n), space: O(n)
    public static boolean backspaceCompare(String s, String t) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        int i;

        Deque<Character> stackS = new LinkedList<>();
        for(i = 0; i < sArr.length; ++i) {
            if(sArr[i] == '#') {
                if(!stackS.isEmpty()) stackS.removeFirst();
                else continue;
            } else stackS.addFirst(sArr[i]);
        }

        Deque<Character> stackT = new LinkedList<>();
        for(i = 0; i < tArr.length; ++i) {
            if(tArr[i] == '#') {
                if(!stackT.isEmpty()) stackT.removeFirst();
                else continue;
            } else stackT.addFirst(tArr[i]);
        }

        System.out.println(stackS.toString());
        System.out.println(stackT.toString());

        if(stackS.size() != stackT.size()) return false;
        while(!stackS.isEmpty() && !stackT.isEmpty()) {
            char a = stackS.removeFirst();
            char b = stackT.removeFirst();

            if(a != b) return false;
        }

        return true;
    }
}
