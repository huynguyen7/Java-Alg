// leetcode 1768.
public class MergeStringsAlternately {
    public static void main(String[] args) {
        showResults("abc", "pqr"); // expect "apbqcr"
        showResults("ab", "pqrs"); // expect "apbqrs"
        showResults("abcd", "pq"); // expect "apbqcd" 
    }

    private static void showResults(String word1, String word2) {
        System.out.println("\t----ShowResults----");
        System.out.printf("WORD1 = %s\nWORD2 = %s\nOUTPUT = %s\n\n", word1, word2, mergeAlternately(word1, word2));
    }

    // n = word1.length(), m = word2.length()
    // Time: O(n+m), space: O(n+m)
    public static String mergeAlternately(String word1, String word2) {
        int i = 0, j = 0;
        boolean flag = true;
        StringBuilder s = new StringBuilder();
        for(int k = 0; k < word1.length()+word2.length(); ++k) {
            if(i >= word1.length()) s.append(word2.charAt(j++));
            else if(j >= word2.length()) s.append(word1.charAt(i++));
            else if(flag) {
                s.append(word1.charAt(i++));
                flag = false;
            } else {
                s.append(word2.charAt(j++));
                flag = true;
            }
        }

        return s.toString();
    }
}
