import java.util.Arrays;

// Elements of Programming Interview 6.7.
public class BuyAndSellAStockTwice {
    public static void main(String[] args) {
        assert(showResults(new int[] {12,11,13,9,12,8,14,13,15}) == 10);
    }

    private static int showResults(int[] prices) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(prices));
        int rs = solve(prices);
        System.out.printf("-> %d\n\n", rs);
        return rs;
    }

    // Time: O(n), space: O(n)
    public static int solve(int[] prices) {
        final int n = prices.length;

        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        int[] maxFirstBoughtProfits = new int[n];

        for(int i = 0; i < n; ++i) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i]-minPrice);
            maxFirstBoughtProfits[i] = maxProfit;
        }

        int maxPrice = Integer.MIN_VALUE;
        maxProfit = 0;
        int[] maxSecondBoughtProfits = new int[n];
        for(int i = n-1; i >= 0; --i) {
            maxPrice = Math.max(maxPrice, prices[i]);
            maxProfit = Math.max(maxProfit, maxPrice-prices[i]);
            maxSecondBoughtProfits[i] = maxProfit;
        }

        for(int i = 1; i < n; ++i)
            maxProfit = Math.max(maxProfit, maxSecondBoughtProfits[i] + maxFirstBoughtProfits[i-1]);

        return maxProfit;
    }
}
