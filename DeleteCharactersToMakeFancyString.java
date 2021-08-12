// leetcode 1957.
public class DeleteCharactersToMakeFancyString {
    public static void main(String[] args) {
        showResults("leeetcode","leetcode"); // expect "leetcode"
        showResults("aaabaaaa","aabaa"); // expect "aabaa"
        showResults("aab","aab"); // expect "aab"
        showResults("aabaabaabaa","aabaabaabaa"); // expect "aabaabaabaa"
    }
    
    private static void showResults(String s, String expect) {
        System.out.println("\t----ShowResults----");
        String output = makeFancyString(s);
        System.out.printf("%s -> %s\n\n", s, output);
        assert(expect.compareTo(output) == 0);
    }

    // A fancy string is a string where no three consecutive characters are equal.
    // Time: O(n), space: O(n)
    public static String makeFancyString(String s) {
        if(s == null || s.length() == 0) return "";
        
        StringBuilder tmpS = new StringBuilder();
        char[] sArr = s.toCharArray();

        int count = 1;
        tmpS.append(sArr[0]);
        char prevC = sArr[0];
        for(int i = 1; i < sArr.length; ++i) {
            if(sArr[i] == prevC) {
                if(count == 2) continue;
                else count++;
            } else {
                count = 1;
                prevC = sArr[i];
            }
            tmpS.append(sArr[i]);
        }

        return tmpS.toString();
    }
}
