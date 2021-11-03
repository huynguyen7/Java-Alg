import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

// leetcode 2053.
public class KthDistinctStringInAnArray {
    public static void main(String[] args) {
        showResults(new String[] {"d","b","c","b","c","a"}, 2); // expect "a"
        showResults(new String[] {"aaa","aa","a"}, 1); // expect "aaa"
        showResults(new String[] {"a","b","a"}, 3); // expect ""
    }

    private static void showResults(String[] arr, int k) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(arr));
        System.out.printf("%d -> %s\n\n", k, kthDistinct(arr, k));
    }

    // Time: O(n), space: O(n)
    public static String kthDistinct(String[] arr, int k) {
        Map<String, Boolean> dups = new HashMap<>();

        for(String s: arr) {
            if(!dups.containsKey(s)) dups.put(s, false);
            else dups.put(s, true);
        }

        int x = 1;
        for(String s: arr) {
            if(!dups.get(s)) {
                if(x == k) return s;
                else x += 1;
            }
        }

        return "";
    }
}
