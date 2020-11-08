import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// leetcode 124.
public class BinaryTreeMaximumPathSum {
	public static void main(String[] args) {
		Integer[] nums1 = {1,2,3};
		showResults(nums1); // expect 6

		Integer[] nums2 = {-10,9,20,null,null,15,7};
		showResults(nums2); // expect 42

		Integer[] nums3 = {10,8,2,3,5};
		showResults(nums3); // expect 25
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
		System.out.println();
		int rs = maxPathSum(root);
		System.out.printf("Max Path Sum: %d\n\n", rs);
	}

	// Trick: Using postorder traversal.

	private static int rs;

	// Time: O(n), space: O(logn)
	public static int maxPathSum(TreeNode root) {
		rs = Integer.MIN_VALUE;
		dfs(root);
		
		return rs;
	}

	private static int dfs(TreeNode root) {
		if(root == null) return 0;
		else {
			int left = dfs(root.left);
			int right = dfs(root.right);

			int maxSingle = Math.max(Math.max(left, right) + root.val, root.val);
			int maxTop = Math.max(maxSingle, left + right + root.val);
			rs = Math.max(rs, maxTop);
			return maxSingle;
		}
	}
	
	private static TreeNode createTreeFromArray(Integer[] nums) {
		if(nums.length == 0 || nums == null) return null;
		TreeNode root = new TreeNode(nums[0]);
		root = insertLevelOrder(nums, root, 0);
		
		return root;
	}

	private static TreeNode insertLevelOrder(Integer[] nums, TreeNode root, int i) {
		if(i < nums.length) {
			TreeNode tmp = nums[i] != null ? new TreeNode(nums[i])
						: null;
			root = tmp;
			if(root != null) {
				//insert left node
				root.left = insertLevelOrder(nums, root.left, 2 * i + 1);
				//insert right node
				root.right = insertLevelOrder(nums, root.right, 2 * i + 2);
			}
		}

		return root;
	}

	private static void printBinaryTree(TreeNode root) {
		printBinaryTree("", root, false);
	}

	private static void printBinaryTree(String prefix, TreeNode root, boolean isLeft) {
		if(root == null) return;
		else {
			System.out.println(prefix + (isLeft ? "|-- " : "\\-- ") + root.val);
			printBinaryTree(prefix + (isLeft ? "|   " : "    "), root.left, true);
			printBinaryTree(prefix + (isLeft ? "|   " : "    "), root.right, false);
		}
	}

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode() {}
		TreeNode(int val) {this.val = val;}
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}
