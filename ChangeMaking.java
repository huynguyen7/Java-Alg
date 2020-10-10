import java.util.Arrays;

// element-prog bootcamp 18.
public class ChangeMaking {
	public static void main(String[] args) {
		int[] coins1 = {100,50,25,10,5,1}; // arrays need to be sorted in descending order.
		// showResults(coins1, 3); // expect 3
		showResults(coins1, Integer.parseInt(args[0]));
	}

	private static void showResults(int[] coins, int amount) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(coins));
		int rs = changeMaking(coins, amount);
		System.out.printf("Amount: %d -> Min # coins needed: %d\n\n", amount, rs);
	}

	// Find the minimum of the number of coins
	// which sum are equal to <amount>
	
	// GREEDY approach.
	// Time: O(n), space: O(1)
	public static int changeMaking(int[] coins, int amount) {
		int numCoins = 0;
		for(int i = 0; i < coins.length; ++i) {
			numCoins += amount / coins[i];
			amount %= coins[i];
		}

		return numCoins;
	}
}
