// leetcode 2129.
public class CapitalizeTheTitle {
    public static void main(String[] args) {
        showResults("capiTalIze tHe titLe"); // expect "Capitalize The Title"
        showResults("First leTTeR of EACH Word"); // expect "First Letter of Each Word"
        showResults("i lOve leetcode"); // expect "i Love Leetcode"
    }

    private static void showResults(String title) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s\n%s\n\n", title, capitalizeTitle(title));
    }

    // Time: O(n), space: O(n)
    public static String capitalizeTitle(String title) {
        String[] words = title.split(" ");
        StringBuilder s = new StringBuilder();

        for(int i = 0; i < words.length; ++i) {
            String word = words[i];
            char[] tmp = word.toCharArray();
            if(tmp.length <= 2) {
                tmp[0] = Character.toLowerCase(tmp[0]);
                    if(tmp.length == 2)
                    tmp[1] = Character.toLowerCase(tmp[1]);
            } else {
                tmp[0] = Character.toUpperCase(tmp[0]);
                for(int j = 1; j < tmp.length; ++j)
                    tmp[j] = Character.isLowerCase(tmp[j]) ? tmp[j] : Character.toLowerCase(tmp[j]);
            }

            s.append(tmp);
            if(i != words.length-1) // not last word
                s.append(" ");
        }

        return s.toString();
    }
}
