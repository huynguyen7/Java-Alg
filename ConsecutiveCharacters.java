// leetcode 1446.
public class ConsecutiveCharacters {
    public static void main(String[] args) {
        String s1 = "leetcode";
        showResults(s1); // expect 2

        String s2 = "abbcccddddeeeeedcba";
        showResults(s2); // expect 5

        String s3 = "triplepillooooow";
        showResults(s3); // expect 5

        String s4 = "hooraaaaaaaaaaay";
        showResults(s4); // expect 11

        String s5 = "tourist";
        showResults(s5); // expect 1

        String s6 = "cc";
        showResults(s6); // expect 2
    }

    private static void showResults(String s) {
        System.out.printf("\t----ShowResults----");
        int rs = maxPower(s);
        System.out.printf("%s\nRESULTS: %d\n\n",s, rs);
    }

    // Time: O(n), space: O(1)
    public static int maxPower(String s) {
        if(s.length() == 0) return 0;

        char c = s.charAt(0);
        int maxLength = 1, currLength = 1;

        for(int i = 1; i < s.length(); ++i) {
            if(s.charAt(i) != c) {
                c = s.charAt(i);
                maxLength = Math.max(maxLength, currLength);
                currLength = 1;
            } else currLength++;
        }
        maxLength = Math.max(maxLength, currLength);

        return maxLength;
    }
}
