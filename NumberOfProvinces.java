import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

// leetcode 547.
public class NumberOfProvinces {
    public static void main(String[] args) {
        showResults(new int[][] {{1,1,0},{1,1,0},{0,0,1}}); // expect 2
        showResults(new int[][] {{1,0,0},{0,1,0},{0,0,1}}); // expect 3
        showResults(new int[][] {{1,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,1,0,1,0,0,0,0,0,0,0,0,0,1,0},
                {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,1,0,1,0,0,0,1,0,0,0,1,0,0,0},
                {0,0,0,0,1,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,1,1,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0,0,0},
                {1,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
                {0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},
                {0,0,0,0,1,0,0,0,0,0,0,0,1,0,0},
                {0,1,0,0,0,0,0,0,0,0,0,0,0,1,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1}}); // expect 8
    }

    private static void showResults(int[][] isConnected) {
        System.out.println("\t----ShowResults----");
        for(int[] arr: isConnected)
            System.out.println(Arrays.toString(arr));
        System.out.printf("--> %d\n\n", findCircleNum(isConnected));
    }

    // Time: O(n*n), space: O(n)
    public static int findCircleNum(int[][] isConnected) {
        Set<Integer> visited = new HashSet<>();

        int count = 0;
        for(int i = 0; i < isConnected.length; ++i) { // For each node.
            if(visited.contains(i)) continue;
            dfs(isConnected, i, visited);
            count += 1;
        }
        return count;
    }

    private static void dfs(int[][] isConnected, int i, Set<Integer> visited) {
        if(visited.contains(i)) return;

        visited.add(i);

        for(int j = 0; j < isConnected.length; ++j) {
            if(haveConnection(isConnected, i, j))
                dfs(isConnected, j, visited);
        }
    }

    private static boolean haveConnection(int[][] isConnected, int i, int j) {
        return i != j && isConnected[i][j] == 1;
    }
}
