import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;


// leetcode 2744.
public class FindMaximumNumberOfStringPairs {
    public static void main(String[] args) {
        assert(showResults(new String[] {"cd","ac","dc","ca","zz"}) == 2); // expect 2
        assert(showResults(new String[] {"ab","ba","cc"}) == 1); // expect 1
        assert(showResults(new String[] {"aa","ab"}) == 0); // expect 0
    }

    private static int showResults(String[] words) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(words));
        int rs = maximumNumberOfStringPairs(words);
        System.out.printf("-> %d\n\n", rs);
        return rs;
    }

    private static String reverseCharacters(String word) {
        char[] cArr = word.toCharArray();

        int i = 0, j = cArr.length-1;
        while(i < j) {
            char tmp = cArr[i];
            cArr[i] = cArr[j];
            cArr[j] = tmp;
            i++;
            j--;
        }

        return new String(cArr);
    }

    // Time: O(n), space: O(n)
    public static int maximumNumberOfStringPairs(String[] words) {
        Set<String> seen = new HashSet<>();
        int rs = 0;

        for(String word: words) {
            if(seen.contains(word)) rs += 1;
            else seen.add(reverseCharacters(word));
        }

        return rs;
    }
}
