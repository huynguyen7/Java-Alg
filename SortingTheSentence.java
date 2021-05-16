import java.util.Arrays;

// leetcode 1859.
public class SortingTheSentence {
    public static void main(String[] args) {
        showResults("is2 sentence4 This1 a3"); // expect "This is a sentence"
        showResults("Myself2 Me1 I4 and3"); // expect "Me Myself and I"
    }

    private static void showResults(String s) {
        System.out.println("\t----ShowResults----");
        System.out.println(s);
        System.out.printf("%s\n\n", sortSentence(s));
    }

    // Time: O(n), space: O(n)
    public static String sortSentence(String s) {
        if(s == null || s.length() == 0) return "";

        String[] words = s.split(" ");
        StringBuilder rs = new StringBuilder();

        Arrays.sort(words, (a, b) -> {
            int indexA = (int) a.charAt(a.length()-1) - 48;
            int indexB = (int) b.charAt(b.length()-1) - 48;
            return Integer.compare(indexA, indexB);
        });
        
        for(String word: words) {
            rs.append(word);
            rs.setCharAt(rs.length()-1, ' ');
        }

        return rs.toString().trim();
    }
}
