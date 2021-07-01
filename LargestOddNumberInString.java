// leetcode 1903.
public class LargestOddNumberInString {
    public static void main(String[] args) {
        showResults("52"); // expect "5"
        showResults("4206"); // expect ""
        showResults("35427"); // expect "35427"
    }

    private static void showResults(String num) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s -> %s\n\n", num, largestOddNumber(num));
    }

    // `num` only consists of digits and does not contain any leading zeros.

    // Time: O(n), space: O(n)
    public static String largestOddNumber(String num) {
        if(num == null || num.length() <= 0) return "";
        
        for(int i = num.length()-1; i >= 0; --i) {
            if(num.charAt(i) % 2 != 0)
                return num.substring(0,i+1);
        }

        return "";
    }
}
