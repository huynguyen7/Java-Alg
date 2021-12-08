import java.util.*;

// leetcode 118.
public class PascalTriangle {
    public static void main(String[] args) {
        showResult(5); // expect [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
        showResult(1); // expect [[1]]
    }

    private static void showResult(int numRows) {
        System.out.printf("\t----ShowResults----");
        System.out.printf("%d\n%s\n\n", numRows,
                        generate(numRows).toString());
    }

    // with k = sum([x for x in range(0,numRows)])
    // Time: O(k), space: O(k)
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> rs = new ArrayList<>();
        
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        rs.add(firstRow);
        if(numRows == 1) return rs;

        List<Integer> secondRow = new ArrayList<>();
        secondRow.add(1);
        secondRow.add(1);
        rs.add(secondRow);
        if(numRows == 2) return rs;

        for(int i = 2; i < numRows; ++i) {
            List<Integer> holder = new ArrayList<>();
            holder.add(1);
            for(int j = 1; j <= i-1; ++j) {
                int upLeft  = rs.get(i-1).get(j-1);
                int upRight = rs.get(i-1).get(j);
                int val = upLeft+upRight;
                holder.add(val);
            }
            holder.add(1);

            rs.add(holder);
        }

        return rs;
    }
}
