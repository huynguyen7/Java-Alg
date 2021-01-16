import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// leetcode 1457.
public class PseudoPalindromicPathsInABinaryTree {
	public static void main(String[] args) {
		Integer[] nums1 = {2,3,1,3,1,null,1};
		showResults(nums1); // expect 2

		Integer[] nums2 = {2,1,1,1,3,null,null,null,null,null,1};
		showResults(nums2); // expect 1

		Integer[] nums3 = {3};
		showResults(nums3); // expect 1
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
	
		int rs = pseudoPalindromicPaths(root);
		System.out.printf("\nNumber of pseudo-palindromic paths: %d\n\n", rs);
	}

	// Time: O(n), space: O(h)
	public static int pseudoPalindromicPaths(TreeNode root) {
		 if(root == null) return 0;
		 return dfs(root, 0);
	}

	private static int dfs(TreeNode root, int pathXor) {
		if(isLeaf(root)) {
			pathXor = pathXor ^ (1 << root.val);
			if((pathXor & (pathXor - 1)) == 0) return 1;
			return 0;
		} else {
			pathXor = pathXor ^ (1 << root.val);
			int leftCount = root.left != null ? dfs(root.left, pathXor) : 0;
			int rightCount = root.right != null ? dfs(root.right, pathXor) : 0;

			return leftCount + rightCount;
		}
	}

	private static boolean isLeaf(TreeNode root) {
		return root.left == null && root.right == null;
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
