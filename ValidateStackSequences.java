import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// leetcode 946.
public class ValidateStackSequences {
    public static void main(String[] args) {
        int[] pushed1 = {1,2,3,4,5};
        int[] popped1 = {4,5,3,2,1};
        showResults(pushed1, popped1); // expect true

        int[] pushed2 = {1,2,3,4,5};
        int[] popped2 = {4,3,5,2,1};
        showResults(pushed2, popped2); // expect false
    }

    private static void showResults(int[] pushed, int[] popped) {
        System.out.println("\t----ShowResults----");
        System.out.println("PUSHED: " + Arrays.toString(pushed));
        System.out.println("POPPED: " + Arrays.toString(popped));

        boolean rs = validateStackSequences(pushed, popped);
        System.out.printf("RESULTS: %b\n\n", rs);
    }

    // Time: O(n), space: O(n)
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed.length == 0) return true;

        Deque<Integer> stack = new LinkedList<>();
        int i = 0, j = 0;
        
        while(i < pushed.length || j < popped.length) {
            if(!stack.isEmpty() && (stack.getFirst() == popped[j])) {
                stack.removeFirst();
                j++;
            } else if(i < pushed.length) {
                stack.addFirst(pushed[i]);
                i++;
            } else break;
        }
        
        return stack.isEmpty();
    }
}
