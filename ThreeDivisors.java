import java.util.Arrays;

// leetcode 1952.
public class ThreeDivisors {
    public static void main(String[] args) {
        showResults(2); // expect false
        showResults(4); // expect true
        showResults(5); // expect false
        showResults(9); // expect true
    }

    private static void showResults(int n) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%d -> %b\n\n", n, isThree(n));
    }

    // Constraints: 1 <= n <= 10^4
    // Time: O(n), space: O(n)
    public static boolean isThree(int n) {
        if(n <= 3) return false;

        int[] count = new int[n+1];

        for(int i = 2; i <= n; ++i) {
            int j = i;
            while(j <= n) {
                count[j]++;
                j += i;
            }
        }

        return count[n]+1 == 3; // plus one for divided by 1.
    }
}
