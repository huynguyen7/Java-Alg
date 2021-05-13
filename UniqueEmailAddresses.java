import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

// leetcode 929.
public class UniqueEmailAddresses {
    public static void main(String[] args) {
        String[] emails1 = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
        showResults(emails1); // expect 2
        
        String[] emails2 = {"a@leetcode.com","b@leetcode.com","c@leetcode.com"};
        showResults(emails2); // expect 3
    }

    private static void showResults(String[] emails) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(emails));
        int rs = numUniqueEmails(emails);
        System.out.printf("Num unique: %d\n\n", rs);
    }

    // Time: O(n), space: O(n)
    public static int numUniqueEmails(String[] emails) {
        if(emails == null || emails.length == 0) return 0;

        final char DOT = '.';
        final char PLUS = '+';
        final char AT = '@';

        Set<String> uniques = new HashSet<>();

        for(String email: emails) {
            StringBuilder s = new StringBuilder();
            int i;
            boolean afterPlus = false;
            for(i = 0; i < email.length(); ++i) {
                char c = email.charAt(i);
                if(c == AT) break;
                else if(afterPlus || c == DOT) continue;
                else if(c == PLUS) afterPlus = true;
                else s.append(c);
            }

            for(; i < email.length(); ++i)
                s.append(email.charAt(i));
            
            uniques.add(s.toString());
        }

        return uniques.size();
    }
}
