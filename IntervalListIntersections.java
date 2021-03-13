import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

// leetcode 986.
public class IntervalListIntersections {
    public static void main(String[] args) {
        int[][] firstList1 = {{0,2},{5,10},{13,23},{24,25}};
        int[][] secondList1 = {{1,5},{8,12},{15,24},{25,26}};
        showResults(firstList1, secondList1); // expect [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]

        int[][] firstList2 = {{1,3},{5,9}};
        int[][] secondList2 = {};
        showResults(firstList2, secondList2); // expect []

        int[][] firstList3 = {};
        int[][] secondList3 = {{4,8},{10,12}};
        showResults(firstList3, secondList3); // expect []

        int[][] firstList4 = {{1,7}};
        int[][] secondList4 = {{3,10}};
        showResults(firstList4, secondList4); // expect [[3,7]]
    }

    private static void showResults(int[][] firstList, int[][] secondList) {
        System.out.println("\t----ShowResults----");
        if(firstList.length != 0) {
            for(int[] row: firstList)
                System.out.printf("%s ", Arrays.toString(row));
        } else System.out.print("[]");

        System.out.println();
        if(secondList.length != 0) {
            for(int[] row: secondList)
                System.out.printf("%s ", Arrays.toString(row));
        } else System.out.print("[]");

        int[][] rs = intervalIntersection(firstList, secondList);
        System.out.println("\nRESULTS:");
        if(rs.length == 0) {
            System.out.println("[]\n");
            return;
        }
        for(int[] row: rs)
            System.out.printf("%s ", Arrays.toString(row));
        System.out.println("\n");
    }

    // m = firstList.length, n = secondList.length
    // Time: O(m+n), space: O(m+n)
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i = 0, j = 0; // Indexing two input arrays. 
        List<int[]> ans = new ArrayList<>();

        while(i < firstList.length && j < secondList.length) {
            int li = firstList[i][0], ri = firstList[i][1];
            int lj = secondList[j][0], rj = secondList[j][1];

            int l = Math.max(li, lj);
            int r = Math.min(ri, rj);
            if(l <= r) ans.add(new int[] {l, r});

            if(ri < rj) i++;
            else j++;
        }

        return ans.toArray(new int[ans.size()][]);
    }
}
