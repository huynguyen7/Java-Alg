import java.util.Arrays;

// leetcode 2011.
public class FinalValueOfVariableAfterPerformingOperations {
    public static void main(String[] args) {
        showResults(new String[] {"--X","X++","X++"}); // expect 1
        showResults(new String[] {"++X","++X","X++"}); // expect 3
        showResults(new String[] {"X++","++X","--X","X--"}); // expect 0
    }

    private static void showResults(String[] ops) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(ops));
        System.out.printf("X = %d\n\n", finalValueAfterOperations(ops));
    }
    
    // Time: O(n), space: O(1)
    public static int finalValueAfterOperations(String[] ops)  {
        if(ops == null || ops.length == 0) return 0;

        final String INC1 = "++X", INC2 = "X++";
        int inc = 0, dec = 0;

        for(String op: ops) {
            if(op.compareTo(INC1) == 0 || op.compareTo(INC2) == 0) inc++;
            else dec++;
        }

        return inc-dec;
    }
}
