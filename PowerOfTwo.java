// leetcode 231.
public class PowerOfTwo {
    public static void main(String[] args) {
        showResults(1); // expect true
        showResults(16); // expect true
        showResults(3); // expect false
        showResults(4); // expect true
        showResults(5); // expect false
    }

    private static void showResults(int n) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%d -> %b\n\n", n, isPowerOfTwo(n));
    }

    // Time: O(n), space: O(1)
    public static boolean isPowerOfTwo(int n) {
        if(n <= 0) return false;
        else if(n == 1) return true;
        
        while(n > 1) {
            if(n == 2) return true;
            else if(n % 2 != 0) return false;
            n /= 2;
        }
        
        return false;
    }
}
