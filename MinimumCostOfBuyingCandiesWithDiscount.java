import java.util.Arrays;

// leetcode 2144.
public class MinimumCostOfBuyingCandiesWithDiscount {
    public static void main(String[] args) {
        assert(showResults(new int[] {1,2,3}) == 5); // expect 5
        assert(showResults(new int[] {6,5,7,9,2,2}) == 23); // expect 23
    }

    private static int showResults(int[] cost) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(cost));
        int rs = minimumCost(cost);
        System.out.printf("-> %d\n\n", rs);
        return rs;
    }

    // Time: O(nlogn), space: O(1)
    public static int minimumCost(int[] cost) {
        int minCost = 0;
        int candiesBought = 0;

        Arrays.sort(cost); // time: O(nlogn)

        for(int i = cost.length-1; i >= 0; i -= 1) {
            candiesBought += 1;
            minCost += cost[i];
            if(candiesBought == 2) {
                candiesBought = 0;
                i -= 1;
            }
        }

        return minCost;
    }
}
