import java.util.Arrays;

// leetcode 134.
public class GasStation {
    public static void main(String[] args) {
        int[] gas1 = {1,2,3,4,5};
        int[] cost1 = {3,4,5,1,2};
        showResults(gas1, cost1); // expect 3

        int[] gas2 = {2,3,4};
        int[] cost2 = {3,4,4};
        showResults(gas2, cost2); // expect 3

        int[] gas3 = {5,1,2,3,4};
        int[] cost3 = {4,4,1,5,1};
        showResults(gas3, cost3); // expect 4
    }

    private static void showResults(int[] gas, int[] cost) {
        System.out.println("\t----ShowResults----");
        System.out.printf("GAS: %s\nCOST: %s\nOUTPUT: %d\n\n", Arrays.toString(gas), Arrays.toString(cost), canCompleteCircuit(gas, cost));
    }

    // Time: O(n), space: O(1)
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas == null || cost == null ||
            gas.length == 0 || cost.length == 0 || gas.length != cost.length) return -1;

        final int n = gas.length;
        int i;
        int start = -1, tank = 0;

        for(i = 0; i < n; ++i) {
            tank += gas[i]-cost[i];
            if(tank < 0) {
                tank = 0;
                start = -1;
            } else if(start == -1) start = i;
        }

        for(i = 0 ; i < start; ++i) {
            tank += gas[i]-cost[i];
            if(tank < 0) return -1;
        }

        return start;
    }
}
