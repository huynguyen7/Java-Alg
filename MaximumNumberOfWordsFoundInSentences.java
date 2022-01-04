import java.util.Arrays;

// leetcode 2114.
public class MaximumNumberOfWordsFoundInSentences {
    public static void main(String[] args) {
        assert(showResults(new String[] {"alice and bob love leetcode", "i think so too", "this is great thanks very much"}) == 6); // expect 6.
        assert(showResults(new String[] {"please wait", "continue to fight", "continue to win"}) == 3); // expect 3.
    }

    private static int showResults(String[] sentences) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s\n", Arrays.toString(sentences));
        int rs = mostWordsFound(sentences);
        System.out.printf("-> %d\n\n", rs);
        return rs;
    }

    // Time: O(n), space: O(n)
    public static int mostWordsFound(String[] sentences) {
        int maxNumWords = 0;
        for(String sentence: sentences) {
            int numWords = sentence.split(" ").length; // This splitting op consumes space.
            maxNumWords = Math.max(maxNumWords, numWords);
        }

        return maxNumWords;
    }
}
