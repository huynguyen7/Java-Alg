import java.util.Arrays;

// leetcode 2706.
public class BuyTwoChocolates {
    public static void main(String[] args) {
        assert(showResults(new int[] {1,2,2}, 3) == 0); // expect 0
        assert(showResults(new int[] {3,2,3}, 3) == 3); // expect 3
    }

    private static int showResults(int[] prices, int money) {
        System.out.println("\t----ShowResults----");
        System.out.printf("Money: %d %s\n", money, Arrays.toString(prices));
        int rs = buyChoco(prices, money);;
        return rs;
    }

    // Time: O(nlogn), space: O(nlogn)
    public static int buyChoco(int[] prices, int money) {
        Arrays.sort(prices));
        int comp = money - prices[0] - prices[1];
        return comp >= 0 ? comp : money;
    }
}
