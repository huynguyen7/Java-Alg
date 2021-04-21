// leetcode 1832.
public class CheckIfTheSentenceIsPangram {
    public static void main(String[] args) {
        showResults("thequickbrownfoxjumpsoverthelazydog"); // expect true
        showResults("leetcode"); // expect false
    }

    private static void showResults(String s) {
        System.out.println("\t----ShowResults----");
        System.out.println("INPUT: " + s);
        boolean rs = checkIfPangram(s);
        System.out.printf("RS: %b\n\n", rs);
    }

    /**
     * Consists of lowercase English letters only!
     */

    // Time: O(n), space: O(n)
    public static boolean checkIfPangram(String s) {
        if(s == null || s.length() == 0) return true;
        
        boolean[] isExisted = new boolean[26];
        for(int i = 0; i < s.length(); ++i) {
            int j = ((int) s.charAt(i) - 97);
            if(!isExisted[j]) isExisted[j] = true;
        }

        for(boolean exist: isExisted)
            if(!exist) return false;
        return true;
    }
}
