// Elements of Programming Interview 7.6 .
public class ReverseAllTheWordsInASentence {
    public static void main(String[] args) {
        assert(showResults("hello world").equals("world hello"));
        assert(showResults("Bob likes Alice").equals("Alice likes Bob"));
    }

    private static String showResults(String s) {
        System.out.println("\t----ShowResults----");
        String rs = reverseAllTheWordsInASentence(s);
        System.out.printf("%s\n%s\n\n", s, rs);
        return rs;
    }

    // Time: O(), space: O()
    private static String reverseAllTheWordsInASentence(String s) {
        final int n = s.length();
        char[] c = s.toCharArray();

        int low = 0, high = n-1;
        reverse(c, low, high);

        low = 0;
        high = low;
        for(int i = 0; i < n; ++i) {
            if(c[i] == ' ') { // Is space.
                high = i > 0 ? i-1 : low;
                reverse(c, low, high);
                low = i < n-1 ? i+1 : i;
            }
        }

        // Reverse last word.
        reverse(c, low, n-1);

        return new String(c);
    }

    private static void reverse(char[] c, int low, int high) {
        while(low < high)
            swap(c, low++, high--);
    }

    private static void swap(char[] c, int i, int j) {
        char tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }
}
