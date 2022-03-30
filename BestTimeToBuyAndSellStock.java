import java.util.Arrays;

// leetcode 121.
public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        assert(showResults(new int[] {7,1,5,3,6,4}) == 5); // expect 5
        assert(showResults(new int[] {7,6,4,3,1}) == 0); // expect 0
    }

    private static int showResults(int[] prices) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(prices));
        int rs = maxProfit(prices);
        System.out.printf("-> %d\n\n", rs);
        return rs;
    }

    // Time: O(), space: O()
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;

        for(int price: prices) {
            maxProfit = Math.max(maxProfit, price - minPrice);
            minPrice = Math.min(minPrice, price);
        }

        return maxProfit;
    }
}
