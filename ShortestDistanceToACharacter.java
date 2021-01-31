import java.util.Arrays;

// leetcode 821.
public class ShortestDistanceToACharacter {
    public static void main(String[] args) {
        String s1 = "loveleetcode";
        char c1 = 'e';
        showResults(s1, c1); // expect [3,2,1,0,1,0,0,1,2,2,1,0]
    }

    private static void showResults(String s, char c) {
        System.out.println("\t----ShowResults----");
        System.out.printf("'%s' << %c\n", s, c);
        int[] rs = shortestToChar(s, c); 
        System.out.println(Arrays.toString(rs) + "\n");
    }

    // Time: O(n), space: O(n)
    public static int[] shortestToChar(String s, char c) {
        if(s.length() == 0) return new int[0];

        int[] rs = new int[s.length()];
        int prev = Integer.MIN_VALUE / 2;

        for(int i = 0; i < s.length(); ++i) {
            if(s.charAt(i) == c) prev = i;
            rs[i] = i - prev;
        }

        prev = Integer.MAX_VALUE / 2;

        for(int i = s.length() - 1; i >= 0; --i) {
            if(s.charAt(i) == c) prev = i;
            rs[i] = Math.min(rs[i], prev - i);
        }

        return rs;
    }
}
