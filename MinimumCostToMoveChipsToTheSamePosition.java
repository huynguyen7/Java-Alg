import java.util.Arrays;

// leetcode 1217.
public class MinimumCostToMoveChipsToTheSamePosition {
    public static void main(String[] args) {
        int[] position1 = {1,2,3};
        showResutls(position1); // expect 1

        int[] position2 = {2,2,2,3,3};
        showResutls(position2); // expect 2

        int[] position3 = {1,1000000000};
        showResutls(position3); // expect 1
    }
    
    private static void showResutls(int[] position) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(position));
        int rs = minCostToMoveChips(position);
        System.out.printf("MIN COST: %d\n\n", rs);
    }

    // Greedy approach.
    // Time: O(n), space: O(1)
    public static int minCostToMoveChips(int[] position) {
        if(position == null || position.length == 0) return 0;

        int evenCost = 0, oddCost = 0;
        for(int p: position) {
            if(p % 2 == 0) evenCost++;
            else oddCost++;
        }

        return Math.min(evenCost, oddCost);
    }
}
