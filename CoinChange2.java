import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// leetcode 518. 
public class CoinChange2 {
	public static void main(String[] args) {
		int amount1 = 12;
		int[] coins1 = {2,3,7};
		showResults(amount1, coins1); // expect 4

		int amount2 = 5;
		int[] coins2 = {1,2,5};
		showResults(amount2, coins2); // ex[ect 4

		int amount3 = 3;
		int[] coins3 = {2};
		showResults(amount3, coins3); // expect 0

		int amount4 = 5;
		int[] coins4 = {1,2,3};
		showResults(amount4, coins4); // expect 5
	}

	private static void showResults(int amount, int[] coins) {
		System.out.println("----ShowResults----");
		System.out.println("INPUT: " + Arrays.toString(coins));
		int rs = changeI(amount, coins);
		System.out.printf("Final Score: %d -> Total combinations: %d\n\n", amount, rs);
	}

	// Constraints:
	// 0 <= amount
	
	// Good explanation for DP: https://www.youtube.com/watch?v=DJ4a7cmjZY0
	// Also: https://www.youtube.com/watch?v=xCbYmUPvc2Q

    // DP approach
    // bottom-up DP
    // n is the number of values from 0 -> amount
    // c is coins.length
    // Time: O(c*n), space: O(c*n)
    public static int changeI(int amount, int[] coins) {
        if(amount == 0 && coins.length == 0) return 1;
        if(amount != 0 && coins.length == 0) return 0;
        
        int[][] numCombinationsForCoins = new int[coins.length][amount + 1];
        
        for(int i = 0; i < numCombinationsForCoins.length; ++i)
            // init all 0 values to 1 since 0$ can be created from
            // any combination of coins
            numCombinationsForCoins[i][0] = 1; 
        
        for(int i = 0; i < numCombinationsForCoins.length; ++i) {
            for(int j = 1; j < numCombinationsForCoins[0].length; ++j) {
                int numCombinationsWhenNotChoosing = i - 1 >= 0 ?
                    numCombinationsForCoins[i - 1][j] : 0;
                int numCombinationsWhenChoosing = j >= coins[i] ?
                    numCombinationsForCoins[i][j - coins[i]] : 0;
                
                numCombinationsForCoins[i][j] = numCombinationsWhenNotChoosing + numCombinationsWhenChoosing;
            }
        }
        
        return numCombinationsForCoins[coins.length - 1][amount];
    }

    // recursive approach
    // Time: O(2^n), space: O(2^n)
    public static int changeII(int amount, int[] coins) {
        return dfs(coins, coins.length, amount);
    }
    
    private static int dfs(int[] coins, int size, int amount) {
        if(amount == 0) return 1; // amount has been used all
                                  // so there is one combination.
        if(amount < 0) return 0; // no such solution exist when
                                 // amount is negative
        if(size <= 0 && amount >= 1) return 0; // no solution when there is
                                               // no more coins left.
        
        return dfs(coins, size - 1, amount)
                + dfs(coins, size, amount - coins[size - 1]);
    }	
}
