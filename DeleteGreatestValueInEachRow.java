import java.util.*;

// leetcode 2500.
public class DeleteGreatestValueInEachRow {
    public static void main(String[] args) {
        assert(showResults(new int[][] {{1,2,4},{3,3,1}}) == 8); // expect 8
        assert(showResults(new int[][] {{10}}) == 10); // expect 10
    }

    private static int showResults(int[][] grid) {
        System.out.println("\t----ShowResults----");
        for(int[] row: grid)
            System.out.println(Arrays.toString(row));
        int rs = deleteGreatestValue(grid);
        System.out.printf("-> %d\n\n", rs);
        return rs;
    }

    // Time: O(n*m*logn), space: O(n*m)
    public static int deleteGreatestValue(int[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;
        int rs = 0;

        List<PriorityQueue<Integer>> listMaxHeap = new ArrayList<PriorityQueue<Integer>>();
        for(int i = 0; i < m; ++i) { // time: O(m)
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
            for(int j = 0; j < n; ++j) // time: O(n)
                maxHeap.add(grid[i][j]); // time: O(logn)
            listMaxHeap.add(maxHeap);
        }

        for(int j = 0; j < n; ++j) { // time: O(n)
            int maxValue = 0;
            for(int i = 0; i < m; ++i) { // time: O(m)
                maxValue = Math.max(maxValue, listMaxHeap.get(i).poll().intValue());
            }
            rs += maxValue;
        }

        return rs;
    }
}
