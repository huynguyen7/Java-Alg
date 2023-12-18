import java.util.*;

// leetcode 2924.
public class FindChampionII {
    public static void main(String[] args) {
        assert(showResults(3, new int[][] {{0,1},{1,2}}) == 0); // expect 0
        assert(showResults(4, new int[][] {{0,2},{1,3},{1,2}}) == -1); // expect -1
    }

    private static int showResults(int n, int[][] edges) {
        System.out.println("\t----ShowResults----");
        for(int[] edge: edges)
            System.out.println(Arrays.toString(edge));
        int rs = findChampion(n, edges);
        System.out.printf("=> RS: %d\n\n", rs);
        return rs;
    }

    // Time: O(n), space: O(n)
    public static int findChampion(int n, int[][] edges) {
        int rs = -1;
        Set<Integer> seen = new HashSet<>();
        final int numEdges = edges.length;
        for(int i = 0; i < numEdges; ++i)
            seen.add(edges[i][1]);
        for(int i = 0; i < n; ++i) {
            if(seen.contains(i)) continue;
            if(!seen.contains(i) && rs == -1) {
                rs = i;
            } else return -1;
        }
        return rs;
    }
}
