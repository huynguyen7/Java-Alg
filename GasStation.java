import java.util.Arrays;

// leetcode 134.
public class GasStation {
    public static void main(String[] args) {
        int[] gas1 = {1,2,3,4,5};
        int[] cost1 = {3,4,5,1,2};
        assert(showResults(gas1, cost1) == 3); // expect 3

        int[] gas2 = {2,3,4};
        int[] cost2 = {3,4,3};
        assert(showResults(gas2, cost2) == -1); // expect -1

        int[] gas3 = {5,1,2,3,4};
        int[] cost3 = {4,4,1,5,1};
        assert(showResults(gas3, cost3) == 4); // expect 4
    }

    private static int showResults(int[] gas, int[] cost) {
        System.out.println("\t----ShowResults----");
        int output = canCompleteCircuit(gas, cost);
        System.out.printf("GAS: %s\nCOST: %s\nOUTPUT: %d\n\n", Arrays.toString(gas), Arrays.toString(cost), output);
        return output;
    }

    // Time: O(n), space: O(1)
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas == null || cost == null ||
            gas.length == 0 || cost.length == 0 || gas.length != cost.length) return -1;

        final int n = gas.length;
        int i;
        int start = -1, tank = 0;

        for(i = 0; i < n; ++i) { // Find the best result at first loop, since the answer is unique, there is only one result else nothing.
            tank += gas[i]-cost[i];
            if(tank < 0) {
                tank = 0;
                start = -1;
            } else if(start == -1) start = i;
        }

        for(i = 0 ; i < start; ++i) { // Check whether if the best result works.
            tank += gas[i]-cost[i];
            if(tank < 0) return -1;
        }

        return start;
    }

    // Naive approach
    // Time: O(n^2), space: O(1)
    public static int canCompleteCircuitII(int[] gas, int[] cost) {
        final int n = gas.length;
        for(int i = 0; i < n; ++i) {
            int j = i;
            int counter = n;
            boolean flag = true;
            int currGas = gas[j];
            while(counter-- > 0) {
                currGas = currGas - cost[j];
                if(currGas < 0) {
                    flag = false;
                    break;
                }
                j = (j+1) % n;
                currGas += gas[j];
            }
            
            if(flag) return i;
        }
        
        return -1;
    }
}
