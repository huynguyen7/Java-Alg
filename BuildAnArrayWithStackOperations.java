import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

// leetcode 1441.
public class BuildAnArrayWithStackOperations {
    public static void main(String[] args) {
        int[] target1 = {1,3};
        int n1 = 3;
        showResults(target1, n1); // expect ["Push","Push","Pop","Push"]

        int[] target2 = {1,2,3};
        int n2 = 3;
        showResults(target2, n2); // expect ["Push","Push","Push"]

        int[] target3 = {1,2};
        int n3 = 4;
        showResults(target3, n3); // expect ["Push","Push"]

        int[] target4 = {2,3,4};
        int n4 = 4;
        showResults(target4, n4); // expect ["Push","Pop","Push","Push","Push"]
    }

    private static void showResults(int[] target, int n) {
        System.out.println("\t----ShowResults----");
        System.out.println("Target: " + Arrays.toString(target));
        System.out.printf("n = %d\n", n);
        List<String> rs = buildArray(target, n);
        System.out.println("RS: " + rs.toString() + "\n");
    }

    // Time: O(n), space: O(n)
    public static List<String> buildArray(int[] target, int n) {
        if(target == null || target.length == 00 || n <= 0)
            return Collections.emptyList();

        List<String> rs = new ArrayList<>();
        int i = 1, j = 0;
        for( ; i <= n; ++i) {
            if(j >= target.length) break;
            else if(target[j] == i) {
                rs.add("Push");
                j++;
            } else {
                rs.add("Push");
                rs.add("Pop");
            }
        }

        return rs;
    }
}
