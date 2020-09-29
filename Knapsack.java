import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// element-prog 17.6
public class Knapsack {
	public static void main(String[] args) {
		int[] w1 = {5,3,2,1};
		int[] v1 = {60,50,70,30};
		int cap1 = 5;
		showResults(w1, v1, cap1); // expect 170

		int[] w2 = {5,2,3};
		int[] v2 = {60,30,40};
		int cap2 = 5;
		showResults(w2, v2, cap2); // expect 70, GOOD EXAMPLE TO DRAW RECURSION TREE.

		int[] w3 = {5,3,4,2};
		int[] v3 = {60,50,70,30};
		int cap3 = 5;
		showResults(w3, v3, cap3); // expect 80
	}

	private static void showResults(int[] weights, int[] values, int capacity) {
		System.out.println("----ShowResults----");
		List<Item> items = new ArrayList<>();
		for(int i = 0; i < weights.length; ++i)
			items.add(new Item(weights[i], values[i]));
		
		System.out.println(items.toString());
		int rs = optimumSubjectToCapacityI(items, capacity);
		
		System.out.printf("Maximum Value for Weight %d: %d\n\n", capacity, rs);
	}

	// VERY IMPORTANT NOTES: EACH ITEM CAN ONLY BE PICKED ONCE IN KNAPSACK PROBLEM.

	// Good explanation: https://www.youtube.com/watch?v=xCbYmUPvc2Q
	// 0/1 Knapsack: We are not allowed to break items.
	// We either take the whole item or don't take it.

	// Subproblem: Investigate the maximum value at each time we pick an item
	// UNTIL there is no more capacity left.
	
	// DP approach
	// n = items.size(), c = capacity
	// Time: O(n*c), space: O(n*c)
	public static int optimumSubjectToCapacityI(List<Item> items, int capacity) {
		int[][] maxValuesForCapacities = new int[items.size() + 1][capacity + 1];

		for(int i = 1; i < maxValuesForCapacities.length; ++i) {
			Item currItem = items.get(i - 1);
			
			for(int j = 1; j < maxValuesForCapacities[0].length; ++j) {
				if(currItem.weight > j) { // item which has weight > capacity
					maxValuesForCapacities[i][j] = maxValuesForCapacities[i - 1][j];
					continue;
				}
				
				int notChoosingThisItem = maxValuesForCapacities[i - 1][j];
				int choosingThisItem = maxValuesForCapacities[i - 1][j - currItem.weight] + currItem.value;

				maxValuesForCapacities[i][j] = Math.max(notChoosingThisItem, choosingThisItem);
			}
		}

		return maxValuesForCapacities[items.size()][capacity];
	}

	// Recursive approach
	// n = items.size()
	// Time: O(n*2^n), space: O(2^n)
	public static int optimumSubjectToCapacityII(List<Item> items, int capacity) {
		return dfs(items, capacity, 0);
	}

	private static int dfs(List<Item> items, int capacity, int startIndex) {
		if(capacity == 0 || startIndex >= items.size()) return 0;
		
		int maxValue = Integer.MIN_VALUE;
		for(int i = startIndex; i < items.size(); ++i) { // Time: O(n)
			if(items.get(i).weight > capacity) continue;
			
			int currMaxValue = dfs(items, capacity - items.get(i).weight, startIndex + 1) + items.get(i).value; // recursive calls.
			maxValue = Math.max(maxValue, currMaxValue);
		}

		return maxValue;
	}

	static class Item {
		public int weight;
		public int value;
		
		public Item(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		@Override
		public String toString() {
			return String.format("[%d, %d]", weight, value);
		}
	}
}
