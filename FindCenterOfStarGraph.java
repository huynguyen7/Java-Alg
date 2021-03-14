import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

// leetcode 1791.
public class FindCenterOfStarGraph {
    public static void main(String[] args) {
        int[][] edges1 = {{1,2},{2,3},{4,2}};
        showResults(edges1); // expect 2

        int[][] edges2 = {{1,2},{5,1},{1,3},{1,4}};
        showResults(edges2); // expect 1
    }

    private static void showResults(int[][] edges) {
        System.out.println("\t----ShowResults----");
        for(int[] row: edges)
            System.out.println(Arrays.toString(row));

        int rs = findCenter(edges);
        System.out.printf("\nCENTER: %d\n\n", rs);
    }

    // Time: O(n), space: O(n)
    public static int findCenter(int[][] edges) {
        if(edges == null || edges.length == 0)
            throw new IllegalArgumentException();

        Map<Integer, Integer> map = new HashMap<>();
        int maxFreq = Integer.MIN_VALUE;
        int valMaxFreq = 0;

        for(int i = 0; i < edges.length; ++i) {
            int src = edges[i][0], dest = edges[i][1];
            int defaultValueSrc = map.getOrDefault(src, 0) + 1;
            int defaultValueDest = map.getOrDefault(dest, 0) + 1;

            map.put(src, defaultValueSrc);
            map.put(dest, defaultValueDest);

            if(maxFreq < defaultValueSrc) {
                maxFreq = defaultValueSrc;
                valMaxFreq = src;
            }
            if(maxFreq < defaultValueDest) {
                maxFreq = defaultValueDest;
                valMaxFreq = dest;
            }
        }

        return valMaxFreq;
    }
}
