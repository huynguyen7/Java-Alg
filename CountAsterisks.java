import java.util.*;

// leetcode 2315.
public class CountAsterisks {
    public static void main(String[] args) {
        assert(showResults("l|*e*et|c**o|*de|") == 2); // expect 2
        assert(showResults("iamprogrammer") == 0); // expect 0
        assert(showResults("yo|uar|e**|b|e***au|tifu|l") == 5); // expect 5
    }

    private static int showResults(String s) {
        System.out.println("\t----ShowResults----");
        int rs = countAsterisks(s);
        System.out.printf("%s\n-> %d\n\n", s, rs);
        return rs;
    }

    // Time: O(n), space: O(1)
    public static int countAsterisks(String s) {
        boolean flag = false;
        int count = 0;

        for(char c: s.toCharArray()) {
            if(c == '|') flag = !flag;
            else {
                if(c == '*' && !flag)
                    count += 1;
            }
        }

        return count;
    }
}
