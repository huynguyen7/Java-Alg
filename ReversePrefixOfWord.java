// leetcode 2000.
public class ReversePrefixOfWord {
    public static void main(String[] args) {
        showResults("abcdefd", 'd'); // expect "dcbaefd"
        showResults("xyxzxe", 'z'); // expect "zxyxxe"
        showResults("abcd", 'z'); // expect "abcd"
    }

    private static void showResults(String word, char ch) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s %c -> %s\n\n", word, ch, reversePrefix(word, ch));
    }

    // Time: O(n), space: O(1)
    public static String reversePrefix(String word, char ch) {
        char[] cList = word.toCharArray();

        int i,j;
        for(j = 0; j < word.length(); ++j) {
            if(cList[j] == ch)
                break;
        }

        if(j == word.length()) return word;

        i = 0;
        while(i < j)
            swap(i++, j--, cList);

        return new String(cList);
    }

    private static void swap(int i, int j, char[] cList) {
        char tmp = cList[i];
        cList[i] = cList[j];
        cList[j] = tmp;
    }
}
