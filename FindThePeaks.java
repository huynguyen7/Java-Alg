import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// leetcode 2951.
public class FindThePeaks {
    public static void main(String[] args) {
        assert(assertEquals(showResults(new int[] {2,4,4}), new int[] {}));
        assert(assertEquals(showResults(new int[] {1,4,3,8,5}), new int[] {1,3}));
    }

    private static List<Integer> showResults(int[] mountain) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(mountain));
        List<Integer> rs = findPeaks(mountain);
        System.out.println(rs.toString());
        return rs;
    }

    // Time: O(n), space: O(n)
    public static List<Integer> findPeaks(int[] mountain) {
        if(mountain == null || mountain.length == 0)
            return Collections.emptyList();
        final int n = mountain.length;
        List<Integer> rs = new ArrayList<>();
        for(int i = 1; i < n-1; ++i) {
            int val = mountain[i];
            int prev = mountain[i-1];
            int next = mountain[i+1];
            if(val > prev && val > next)
                rs.add(i);
        }
        return rs;
    }

    private static boolean assertEquals(List<Integer> t1, int[] expected) {
        if(t1 == null || expected == null) return false;
        final int n1 = t1.size();
        final int n2 = expected.length;
        if(n1 != n2) return false;

        for(int i = 0; i < n2; ++i) {
            if(expected[i] != t1.get(i))
                return false;
        }

        return true;
    }
}
