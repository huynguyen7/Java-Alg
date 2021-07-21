import java.util.*;

// leetcode 1935.
public class MaximumNumberOfWordsYouCanType {
    public static void main(String[] args) {
        String text1 = "hello world";
        String brokenLetters1 = "ad";
        showResults(text1, brokenLetters1); // expect 1

        String text2 = "leet code";
        String brokenLetters2 = "lt"; 
        showResults(text2, brokenLetters2); // expect 1

        String text3 = "leet code";
        String brokenLetters3 = "e"; 
        showResults(text3, brokenLetters3); // expect 0

        String text4 = "veikxddtjgpixjrux srxiqrczp cxaldqsvsxpzn xrlxovsjy ervh cdtxwnahcvj xazmhniydmzsseuhq htrsuiabtzcjglilehrpxqcadk ynls r pjkiwtcmvldcr t urevy fjmeutye gjnyd wv fueploq eol zofra xnwaxnwh lpckcgzfcslugpmu judahwebqnwtk gfttojiqcffstkcq nfxbw ugnviyeincmuzoosfy kdazdudaztlnj rqg umaohfgtvk i vfhdvuvbih falmmrke rv zsaqn oswdlfq eapt mnr swcoa jhmui t vkm tumfqvj ehcycfgzxjkhxhdbymmwxy xnsxxerahbrr silb rqmhfbyopev fstlsvpblocrvrheghvgiuqftknewskmhbk nchoj bo cxovzradanq fofsrtmnytq brcixelmzvdxmm";
        String brokenLetters4 = "wqchprenozi";
        showResults(text4, brokenLetters4); // expect 3
    }

    private static void showResults(String text, String brokenLetters) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s %s\n", text, brokenLetters);
        System.out.printf("%d\n", canBeTypedWords(text, brokenLetters));
    }

    /** Constraints:
     * 1 <= text.length <= 104
     * 0 <= brokenLetters.length <= 26
     * text consists of words separated by a single space without any leading or trailing spaces.
     * Each word only consists of lowercase English letters.
     * brokenLetters consists of distinct lowercase English letters.
     */

    // n is text.length()
    // m is brokenLetters.length()
    // Time: O(n*m), space: O(n)
    public static int canBeTypedWords(String text, String brokenLetters) {
        if(text == null || text.length() == 0) return 0;

        Set<Character> chars = new HashSet<>();
        int count = 0;

        for(int i = 0; i < brokenLetters.length(); ++i)
            chars.add(brokenLetters.charAt(i));

        for(String word: text.split(" ")) {
            boolean flag = true;
            for(char c: word.toCharArray()) {
                if(chars.contains(c)) {
                    flag = false;
                    break;
                }
            }

            if(flag) count++;
        }

        return count;
    }
} 
