// leetcode 326.
public class PowerOfThree {
    public static void main(String[] args) {
        showResults(27); // expect true
        showResults(0); // expect false
        showResults(9); // expect true
        showResults(45); // expect false
    }

    private static void showResults(int n) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%d -> %b\n\n", n, isPowerOfThree(n));
    }

    // Time: O(n), space: O(1)
    public static boolean isPowerOfThree(int n) {
        if(n <= 0) return false;
        else if(n == 1) return true;
        
        while(n > 1) {
            if(n == 3) return true;
            else if(n % 3 != 0) return false;
            n /= 3;
        }
        
        return false;
    }
}
