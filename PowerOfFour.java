// leetcode 231.
public class PowerOfFour {
    public static void main(String[] args) {
        showResults(16); // expect true
        showResults(5); // expect false
    }

    private static void showResults(int n) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%d -> %b\n\n", n, isPowerOfFour(n));
    }

    // Time: O(n), space: O(1)
    public static boolean isPowerOfFour(int n) {
        if(n <= 0) return false;
        else if(n == 1) return true;
        
        while(n > 1) {
            if(n == 4) return true;
            else if(n % 4 != 0) return false;
            n /= 4;
        }
        
        return false;
    }
}
