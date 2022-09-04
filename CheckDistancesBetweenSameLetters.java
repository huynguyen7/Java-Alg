import java.util.Arrays;

// leetcode 2399.
public class CheckDistancesBetweenSameLetters {
    public static void main(String[] args) {
        assert(showResults("abaccb", new int[] {1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0})); // expect true
        assert(!showResults("aa", new int[] {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0})); // expect false
    }

    private static boolean showResults(String s, int[] distance) {
        System.out.println("----ShowResults----");
        boolean rs = checkDistances(s, distance);
        System.out.printf("%s\n%s\n%b\n\n", s, Arrays.toString(distance), rs);
        return rs;
    }

    // Time: O(n), space: O(1)
    public static boolean checkDistances(String s, int[] distance) {
        int[] lastIndinces = new int[26];
        Arrays.fill(lastIndinces, -1);

        final int n = s.length();
        for(int i = 0; i < n; ++i) {
            char a = s.charAt(i);
            int idx = (int) a - 0x61;
            if(lastIndinces[idx] == -1) lastIndinces[idx] = i;
            else if(distance[idx] != (i - lastIndinces[idx] - 1)) return false;
        }

        return true;
    }
}
