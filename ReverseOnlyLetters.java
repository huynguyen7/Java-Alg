// leetcode 917.
public class ReverseOnlyLetters {
    public static void main(String args[]) {
        String s1 = "ab-cd";
        showResults(s1); // expect "dc-ba"

        String s2 = "a-bC-dEf-ghIj";
        showResults(s2); // expect "j-Ih-gfE-dCba"

        String s3 = "Test1ng-Leet=code-Q!";
        showResults(s3); // expect "Qedo1ct-eeLg=ntse-T!"
    }

    private static void showResults(String s) {
        System.out.println("----ShowResults----");
        System.out.printf("%s -> %s\n\n", s, reverseOnlyLetters(s));
    }

    // time: O(n), space: O(n)
    public static String reverseOnlyLetters(String s) {
        char[] carr = s.toCharArray();
        int lo = 0;
        int hi = s.length() - 1;

        while (lo < hi) {
            if (Character.isLetter(carr[lo]) && Character.isLetter(carr[hi]))
                swap(carr, lo++, hi--);
            else if (!Character.isLetter(carr[lo]))
                lo++;
            else
                hi--;
        }

        return new String(carr);
    }

    private static void swap(char[] carr, int i, int j) {
        char tmp = carr[i];
        carr[i] = carr[j];
        carr[j] = tmp;
    }
}
