import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// leetcode 386.
public class LexicographicalNumbers {
    public static void main(String[] args) {
        showResults(13); // expect [1,10,11,12,13,2,3,4,5,6,7,8,9]

        //showResults(Integer.parseInt(args[0]));
    }

    private static void showResults(int n) {
        System.out.println("\t----ShowResults----");
        System.out.printf("N = %d\n", n);
        List<Integer> rs = lexicalOrder(n);
        System.out.println(rs.toString() + "\n");
    }

    // Time: O(n), space: O(n)
    public static List<Integer> lexicalOrder(int n) {
        if(n <= 0) return Collections.emptyList();

        List<Integer> rs = new ArrayList<>();
        for(int i = 1; i <= 9; ++i) // There are 9 digits.
            dfs(i, n, rs);

        return rs;
    }

    private static void dfs(int i, int n, List<Integer> rs) {
        if(i > n) return;

        rs.add(i);
        for(int j = 0; j <= 9; ++j) // There are 9 digits.
            dfs(10*i+j, n, rs);
    }
}
