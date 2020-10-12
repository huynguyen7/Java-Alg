import java.util.Arrays;

// leetcode 42.
public class TrappingRainWater {
	public static void main(String[] args) {
		int[] h1 = {0,1,0,2,1,0,1,3,2,1,2,1};
		showResults(h1); // expect 6
	}

	private static void showResults(int[] height) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(height));

		int rs = trap(height);
		System.out.printf("Max water can be trapped: %d\n\n", rs);
	}

	// GOOD EXPLANATION:
	// https://www.youtube.com/watch?v=HmBbcDiJapY

	// Greedy approach.
	// Time: O(n^2), space: O(1)
	public static int trap(int[] height) {
		int totalWater = 0;
		for(int i = 1; i < height.length - 1; ++i) { // first and last column cannot contain water.
			int leftMaxHeight = maxHeight(height, 0, i); // time: O(n)
			int rightMaxHeight = maxHeight(height, i, height.length - 1); // time: O(n)
			int waterAtCurrCol = Math.min(leftMaxHeight, rightMaxHeight) - height[i];

			if(waterAtCurrCol > 0) totalWater += waterAtCurrCol;
		}

		return totalWater;
	}

	// Time: O(n), space: O(1)
	private static int maxHeight(int[] height, int from, int to) {
		int max = Integer.MIN_VALUE;
		for(int i = from; i <= to; ++i)
			max = Math.max(max, height[i]);

		return max;
	}
}

// Constraints:
// height[i] >= 0
