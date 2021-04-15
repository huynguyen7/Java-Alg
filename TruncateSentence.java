// leetcode 1816.
public class TruncateSentence {
    public static void main(String[] args) {
        showResults("Hello how are you Contestant", 4); // expect "Hello how are you"
        showResults("What is the solution to this problem", 4); // expect "What is the solution"
        showResults("chopper is not a tanuki", 5); // expect "chopper is not a tanuki"
    }

    private static void showResults(String s, int k) {
        System.out.println("\t----ShowResults----");
        System.out.printf("INPUT: %s\nk = %d\nOUTPUT: %s\n\n", s, k, truncateSentence(s, k));
    }

    // Time: O(n), space: O(n)
    public static String truncateSentence(String s, int k) {
        StringBuilder rs = new StringBuilder();
        int currK = 0, i = 0; 

        for(; i < s.length(); ++i) {
            char c = s.charAt(i);
            if(Character.isSpaceChar(c) && ++currK == k) break;
            rs.append(c);
        }

        return rs.toString();
    }
}
