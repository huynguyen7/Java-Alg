// leetcode 693.
public class BinaryNumberWithAlternatingBits {
    public static void main(String[] args) {
        showResults(5); // expect true
        showResults(7); // expect false
        showResults(11); // expect false
        showResults(10); // expect true
        showResults(3); // expect false
        showResults(2); // expect true
        showResults(4); // expect false
    }

    private static void showResults(int n) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%d -> %s\n", n, Integer.toBinaryString(n));
        System.out.printf("%b\n\n", hasAlternatingBits(n));
    }

    // Time: O(sqrt(n)), space: O(1)
    public static boolean hasAlternatingBits(int n) {
        int currBit = n%2;
        n /= 2;

        while(n > 0) {
            if(currBit == n%2) return false;
            currBit = n%2;
            n /= 2;
        }
        
        return true;
    }
}
