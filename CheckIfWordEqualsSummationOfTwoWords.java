// leetcode 1880.
public class CheckIfWordEqualsSummationOfTwoWords {
    public static void main(String[] args) {
        showResults("acb", "cba", "cdb"); // expect true
        showResults("aaa", "a", "aab"); // expect false
        showResults("aaa", "a", "aaaa"); // expect true
    }

    private static void showResults(String firstWord, String secondWord, String targetWord) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s, %s, %s -> %b\n\n", firstWord, secondWord, targetWord,
                isSumEqual(firstWord, secondWord, targetWord));
    }

    // Time: O(n), space: O(1)
    public static boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        int val1 = 0, val2 = 0, val3 = 0;
        final int BASE = 10;

        for(int i = 0; i < firstWord.length(); ++i) {
            int num = (int) firstWord.charAt(i) - 'a';
            val1 = val1*BASE + num;
        }

        for(int i = 0; i < secondWord.length(); ++i) {
            int num = (int) secondWord.charAt(i) - 'a';
            val2 = val2*BASE + num;
        }

        for(int i = 0; i < targetWord.length(); ++i) {
            int num = (int) targetWord.charAt(i) - 'a';
            val3 = val3*BASE + num;
        }

        return val1 + val2 == val3;
    }
}
