// elements-prog 7.14
public class RabinKarp {
    public static void main(String[] args) {
        String t1 = "GACGCCA";
        String s1 = "CGC";
        showResults(t1, s1); // expect 2

        String t2 = "ABCD";
        String s2 = "CGC";
        showResults(t2, s2); // expect -1
    }

    private static void showResults(String t, String s) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s <-> %s\n", t, s);
        int indexFound = rabinKarp(t, s);
        System.out.printf("FOUND: %d\n\n", indexFound);
    }

    // n = t.length(), m = s.length()
    // Time: O(n+m), space: O(1)
    public static int rabinKarp(String t, String s) {
        if(t.length() < s.length()) return -1;

        final int BASE = 26; // There are 26 characters in English alphabet.
        int tHash = 0, sHash = 0;
        int powerS = 1;

        for(int i = 0; i < s.length(); ++i) {
            powerS = i > 0 ? powerS * BASE : 1;
            sHash = sHash * BASE + s.charAt(i);
            tHash = tHash * BASE + t.charAt(i);
        }

        for(int i = s.length(); i < t.length(); ++i) {
            if(tHash == sHash && t.substring(i - s.length(), i).equals(s))
                return i - s.length(); // FOUND
            tHash -= t.charAt(i - s.length()) * powerS;
            tHash = tHash * BASE + t.charAt(i);
        }

        if(tHash == sHash && t.substring(t.length() - s.length(), t.length()).equals(s))
            return t.length() - s.length(); // FOUND
        return -1;
    }
}
