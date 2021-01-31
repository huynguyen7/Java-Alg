import java.util.Deque;
import java.util.LinkedList;

// leetcode 1544.
public class MakeTheStringGreat {
    public static void main(String[] args) {
        String s1 = "leEeetcode";
        showResults(s1); // expect "leetcode"

        String s2 = "abBAcC";
        showResults(s2); // expect ""

        String s3 = "s";
        showResults(s3); // expect "s"

        String s4 = "Pp";
        showResults(s4); // expect ""
    }

    private static void showResults(String s) {
        System.out.println("\t----ShowResults----");
        String rs = makeGood(s);
        System.out.printf("%s -> %s\n\n", s, rs);
    }

    // Time: O(n), space: O(n)
    public static String makeGood(String s) {
        if(s.length() <= 1) return s;

        char[] sArr = s.toCharArray();
        Deque<Character> deque = new LinkedList<>();
        StringBuilder rs = new StringBuilder();

        for(int i = 0; i < sArr.length; ++i) {
            if(!deque.isEmpty() 
                && ((Character.isUpperCase(sArr[i])
                && !Character.isUpperCase(deque.getFirst())
                && deque.getFirst() == Character.toLowerCase(sArr[i])) 
                || (!Character.isUpperCase(sArr[i])
                && Character.isUpperCase(deque.getFirst())
                && sArr[i] == Character.toLowerCase(deque.getFirst()))))
                deque.removeFirst();
            else deque.addFirst(sArr[i]);
        }

        while(!deque.isEmpty())
            rs.append(deque.removeLast());

        return rs.toString();
    }
}
