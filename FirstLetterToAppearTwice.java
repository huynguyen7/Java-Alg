// leetcode 2351.
public class FirstLetterToAppearTwice {
    public static void main(String[] args) {
        assert(showResults("abccbaacz") == 'c'); // expect c
        assert(showResults("abcdd") == 'd'); // expect d
    }

    private static char showResults(String s) {
        System.out.println("\t----ShowResults----");
        char rs = repeatedCharacter(s);
        System.out.printf("%s\n-> %c\n\n", s, rs);
        return rs;
    }

    // Time: O(n), space: O(1)
    public static char repeatedCharacter(String s) {
        char[] count = new char[26];
        for(char c: s.toCharArray()) {
            int pos = (int)c-0x61;
            count[pos] += 1;
            if(count[pos] == 2)
                return c;
        }

        return 'a';
    }
}
