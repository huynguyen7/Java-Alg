import java.util.Arrays;

// leetcode 122.
public class BestTimeToBuyAndSellStockII {
    public static void main(String[] args) {
        assert(showResults(new int[] {7,1,5,3,6,4}) == 7); // expect 7
        assert(showResults(new int[] {1,2,3,4,5}) == 4); // expect 4
        assert(showResults(new int[] {7,6,4,3,1}) == 0); // expect 0
    }

    private static int showResults(int[] prices) {
        System.out.println("\t----ShowResults----");
        int rs = maxProfit(prices);
        System.out.printf("%s\n%d\n\n", Arrays.toString(prices), rs);
        return rs;
    }

    // Time: O(n), space: O(1)
    public static int maxProfit(int[] prices) {
        final int n = prices.length;
        int profit = 0;
        for(int i = 1; i < n; ++i)
            profit += prices[i] - prices[i-1] > 0 ? prices[i] - prices[i-1] : 0;
        return profit;
    }
}
