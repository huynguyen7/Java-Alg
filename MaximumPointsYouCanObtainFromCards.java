import java.util.Arrays;

// leetcode 1423.

public class MaximumPointsYouCanObtainFromCards {
	public static void main(String[] args) {
		int[] cardPoints1 = {1,2,3,4,5,6,1};
		int k1 = 3;
		showResults(cardPoints1, k1); // expect 12
		
		int[] cardPoints2 = {2,2,2};
		int k2 = 2;
		showResults(cardPoints2, k2); // expect 4

		int[] cardPoints3 = {9,7,7,9,7,7,9};
		int k3 = 7;
		showResults(cardPoints3, k3); // expect 55

		int[] cardPoints4 = {1,1000,1};
		int k4 = 1;
		showResults(cardPoints4, k4); // expect 1

		int[] cardPoints5 = {1,79,80,1,1,1,200,1};
		int k5 = 3;
		showResults(cardPoints5, k5); // expect 202

		int[] cardPoints6 = {1,3,2}; // GOOD EXAMPLE TO DRAW RECURSION TREE.
		int k6 = 2;
		showResults(cardPoints6, k6); // expect 5
	}

	private static void showResults(int[] cardPoints, int k) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(cardPoints));
		int rs = maxScoreI(cardPoints, k);
		System.out.printf("k = %d -> %d\n\n", k, rs);
	}

	// DP approach
	// bottom-up DP
	// Time: O(n), space: O(n)
	public static int maxScoreI(int[] cardPoints, int k) {
		// with k cards, we can only go as max index with
		// index = cardPoints - k from right side
		//  or index = k from left side
		
		int[] leftMaxSums = new int[k + 1];
		int[] rightMaxSums = new int[k + 1];

		for(int i = 1; i <= k; ++i) 
			leftMaxSums[i] = leftMaxSums[i - 1] + cardPoints[i - 1];
		for(int j = 1; j <= k; ++j)
			rightMaxSums[j] = rightMaxSums[j - 1] + cardPoints[cardPoints.length - j];

		int maxSum = Integer.MIN_VALUE;
		for(int i = 0; i < leftMaxSums.length; ++i)
			maxSum = Math.max(maxSum, leftMaxSums[i] + rightMaxSums[k - i]);
		

		return maxSum;
	}

	// recursive approach
	// Time: O(2^n), space: O(2^n)
	public static int maxScoreII(int[] cardPoints, int k) {
		return dfs(cardPoints, k, 0, cardPoints.length - 1);
	}

	private static int dfs(int[] cardPoints, int k, int left, int right) {
		if(k == 0) return 0; // there is no more k cards left to pick.
		
		int leftMaxSum = dfs(cardPoints, k - 1, left + 1, right) + cardPoints[left];
		int rightMaxSum = dfs(cardPoints, k - 1, left, right - 1) + cardPoints[right];

		return Math.max(leftMaxSum, rightMaxSum);
	}
}

// Constraints:
// 1 <= k <= cardPoints.length
