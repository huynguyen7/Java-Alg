import java.util.Arrays;

// leetcode 1130.
public class MinimumCostTreeFromLeafValues {
	public static void main(String[] args) {
		int[] nums1 = {6,2,4};
		showResults(nums1); // expect 32
							// GOOD EXAMPLE TO DRAW RECURSION TREE.

		int[] nums2 = {6,2,7,4};
		showResults(nums2); // expect 82

		int[] nums3 = {6,2};
		showResults(nums3); // expect 12
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		int rs = mctFromLeafValuesI(nums);
		System.out.printf("Minimum Cost Tree From Leaf Values: %d\n\n", rs);
	}

	// DP approach.
	// top-down DP.
	// Time: O(n^2), space: O(n^2)
	public static int mctFromLeafValuesI(int[] nums) {
		if(nums == null || nums.length <= 1) return 0;
		
		int n = nums.length;
		Node[][] cache = new Node[n][n];

		return dfsI(nums, 0, nums.length - 1, cache).minSum;
	}

	private static Node dfsI(int[] nums, int left, int right, Node[][] cache) {
		if(left == right) return new Node(nums[left], 0);
		
		if(cache[left][right] == null) {
			int maxLeaf = Integer.MIN_VALUE, minSum = Integer.MAX_VALUE;
			for(int i = left; i < right; ++i) {
				Node leftSide = dfsI(nums, left, i, cache);
				Node rightSide = dfsI(nums, i + 1, right, cache);

				minSum = Math.min(minSum, leftSide.minSum + rightSide.minSum + leftSide.maxLeaf * rightSide.maxLeaf);
				maxLeaf = Math.max(maxLeaf, Math.max(leftSide.maxLeaf, rightSide.maxLeaf));
			}

			cache[left][right] = new Node(maxLeaf, minSum);
		}


		return cache[left][right];
	}

	// Recursive approach.
	// Time: O(n*2^n), space: O(n*2^n)
	public static int mctFromLeafValuesII(int[] nums) {
		if(nums == null || nums.length <= 1) return 0;

		Node minCost = dfsII(nums, 0, nums.length - 1);
		return minCost.minSum;
	}

	private static Node dfsII(int[] nums, int left, int right) {
		if(left == right) return new Node(nums[left], 0);

		int maxLeaf = Integer.MIN_VALUE, minSum = Integer.MAX_VALUE;
		for(int i = left; i < right; ++i) { // time: O(n*2^n)
			Node leftSide = dfsII(nums, left, i);
			Node rightSide = dfsII(nums, i + 1, right);

			minSum = Math.min(minSum, leftSide.minSum + rightSide.minSum + leftSide.maxLeaf * rightSide.maxLeaf);
			maxLeaf = Math.max(maxLeaf, Math.max(leftSide.maxLeaf, rightSide.maxLeaf));
		}

		return new Node(maxLeaf, minSum);
	}

	private static class Node {
		public int maxLeaf;
		public int minSum;

		public Node(int maxLeaf, int minSum) {
			this.maxLeaf = maxLeaf;
			this.minSum = minSum;
		}

		@Override
		public String toString() {
			return String.format("[%d, %d]", maxLeaf, minSum);
		}
	}
}

// Constraints:
// - Each node has either 0 or 2 children.
// - The values of nums correspond to the values of each leaf in an in-order traversal of the tree.
// - The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
