import java.util.*;

// element-prog 17.9
public class PickUpCoinsForMaximumGain {
	public static void main(String[] args) {
		int[] coins1 = {5,25,10,1};
		showResults(coins1); // expect 26
							// 1 + 25
							// GOOD EXAMPLE TO DRAW RECURSION TREE.

		int[] coins2 = {5,1,20,3,2,15};
		showResults(coins2); // expect 37
							// 15 + 2 + 20
	}
	
	private static void showResults(int[] coins) {
		System.out.println("----ShowResults----");
		List<Integer> list = new ArrayList<>();
		for(int i: coins)
			list.add(i);
		System.out.println(list.toString());
		int rs = pickUpCoinsI(list);
		System.out.printf("Max: %d\n", rs);
	}

	// RULES:
	// 2 users.
	// Can only choose coin from left or right of the list.
	// Assume that another user is GREEDY.
		
	// Better codes in the book.

	// DP approach.
	// top-down DP.
	// n = coins.size()
	// Time: O(n^2), space: O(n^2)
	public static int pickUpCoinsI(List<Integer> coins) {
		return dfsI(coins, 0, coins.size() - 1, new int[coins.size()][coins.size()]);
	}

	private static int dfsI(List<Integer> coins, int left, int right, int[][] maximumRevenueForRange) {
		if(left > right) return 0; // no coins left.
		else {
			if(maximumRevenueForRange[left][right] == 0) { // no coins has been picked at current index.
				int bothPickLeft = dfsI(coins, left + 2, right, maximumRevenueForRange);
				int bothPickRight = dfsI(coins, left, right - 2, maximumRevenueForRange);
				int pickLeftRight = dfsI(coins, left + 1, right - 1, maximumRevenueForRange);

				int maximumRevenueLeft = coins.get(left) + // get left coin
					Math.min(bothPickLeft, pickLeftRight); // get min because another user will pick max.
				int maximumRevenueRight = coins.get(right) + // get right coin
					Math.min(bothPickRight, pickLeftRight); // get min because another user will pick max.

				maximumRevenueForRange[left][right] = Math.max(maximumRevenueLeft, maximumRevenueRight);
			}

			return maximumRevenueForRange[left][right];
		}
	}

	// recursive approach.
	// Time: O(3^n), space: O(3^n)
	public static int pickUpCoinsII(List<Integer> coins) {
		return dfsII(coins, 0, coins.size() - 1);
	}

	private static int dfsII(List<Integer> coins, int left, int right) {
		if(left > right) return 0; // no coins left.
		else {
			int bothPickLeft = dfsII(coins, left + 2, right);
			int bothPickRight = dfsII(coins, left, right - 2);
			int pickLeftRight = dfsII(coins, left + 1, right - 1);
			
			int maxLeft = coins.get(left) + // get left coin
				Math.min(bothPickLeft, pickLeftRight); // get min because another user will pick max.
			int maxRight = coins.get(right) + // get right coin
				Math.min(bothPickRight, pickLeftRight); // get min because another user will pick max.

			return Math.max(maxLeft, maxRight);
		}
	}
}
