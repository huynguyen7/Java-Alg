import java.util.Arrays;

// leetcode 2108.
public class FindFirstPalindromicStringInTheArray {
    public static void main(String[] args) {
        assert(showResults(new String[] {"abc","car","ada","racecar","cool"}).compareTo("ada") == 0); // expect "ada"
        assert(showResults(new String[] {"notapalindrome","racecar"}).compareTo("racecar") == 0); // expect "racecar"
        assert(showResults(new String[] {"def","ghi"}).compareTo("") == 0); // expect ""
    }

    private static String showResults(String[] words) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(words));
        String rs = firstPalindrome(words);
        System.out.printf("%s\n\n", rs);
        return rs;
    }

    // m = word.length()
    // Time: O(m), space: O(1)
    private static boolean isPalindrome(String word) {
        int low = 0, high = word.length()-1;
        while(low <= high) {
            if(word.charAt(low) != word.charAt(high))
                return false;

            low += 1;
            high -= 1;
        }

        return true;
    }

    // n = words.length
    // Time: O(n*m), space: O(1)
    public static String firstPalindrome(String[] words) {
        for(String word: words) {
            if(isPalindrome(word))
                return word;
        }

        return "";
    }
}
