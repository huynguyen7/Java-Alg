public class SumOfDigitsOfStringAfterConvert {
    public static void main(String[] args) {
        showResults("iiii",1); // expect 36
        showResults("leetcode",2); // expect 6
        showResults("zbax",2); // expect 8
    }

    private static void showResults(String s, int k) {
        System.out.println("\t----ShowResults----");
        System.out.printf("INPUT: %s %d\nOUTPUT: %d\n\n", s, k, getLucky(s, k));
    }

    // n = s.length()
    // Time: O(n+k), space: O(n)
    public static int getLucky(String s, int k) {
        int sum = 0;
        for(char c: s.toCharArray())
            sum += sumDigits((int) c - 0x61+1);

        while(--k != 0) {
           sum = sumDigits(sum); 
        }

        return sum;
    }

    private static int sumDigits(int num) {
        int sum = 0;
        final int BASE = 10;

        while(num != 0) {
            sum += num % BASE;
            num /= BASE;
        }

        return sum;
    }
}
