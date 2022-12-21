// leetcode 2490.
public class CircularSentence {
    public static void main(String[] args) {
        assert(showResults("leetcode exercises sound delightful")); // expect true
        assert(showResults("eetcode")); // expect true
        assert(!showResults("Leetcode is cool")); // expect false
    }

    private static boolean showResults(String sentence) {
        System.out.println("\t----ShowResults----");
        boolean rs = isCircularSentence(sentence);
        System.out.printf("%s -> %b\n\n", sentence, rs);
        return rs;
    }

    /**
     * m is the number of words
     * n is the number of characters.
     * Time: O(m), space: O(n)
     */
    public static boolean isCircularSentence(String sentence) {
        final int n = sentence.length();
        if(n == 0) return false;
        if(n == 1) return true;
        if(sentence.charAt(0) != sentence.charAt(n-1)) return false;
        
        String[] words = sentence.split(" ");
        for(int i = 0; i < words.length-1; ++i) {
            String word1 = words[i];
            String word2 = words[i+1];
            if(word1.charAt(word1.length()-1) != word2.charAt(0)) return false;
        }

        return true;
    }
}
