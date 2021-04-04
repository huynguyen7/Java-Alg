// leetcode 520.
public class DetectCapital {
    public static void main(String[] args) {
        showResults("USA"); // expect true
        showResults("FlaG"); // expect false
        showResults("ggg"); // expect true
        showResults("GG"); // expect true
    }

    private static void showResults(String word) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s\n%b\n\n", word, detectCapitalUse(word));
    }

    // Time: O(n), space: O(n)
    public static boolean detectCapitalUse(String word) {
        if(word == null || word.length() <= 1) return true;
        
        char[] cArr = word.toCharArray();
        
        boolean isCapital = false;
        for(int i = 1; i < cArr.length-1; ++i) {
            char c = cArr[i];
            if(Character.isUpperCase(c)) {
                if(isCapital) continue;
                else if(i != 1) return false;
                else isCapital = true;
            } else if(i != 1 && isCapital) return false;
        }
        
        char c1 = cArr[0];
        char c2 = cArr[cArr.length-1];
        
        if(isCapital) return Character.isUpperCase(c1) && Character.isUpperCase(c2);
        else if(!isCapital && word.length() == 2) {
            return (Character.isUpperCase(c1) && Character.isUpperCase(c2)) ||
                (!Character.isUpperCase(c1) && !Character.isUpperCase(c2)) ||
                (Character.isUpperCase(c1) && !Character.isUpperCase(c2));
        } else return (Character.isUpperCase(c1) && !Character.isUpperCase(c2)) || 
            (!Character.isUpperCase(c1) && !Character.isUpperCase(c2));
    }
}
