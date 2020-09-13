import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//leetcode 110, element-prog 10.1
public class BalancedBinaryTree {
	public static void main(String[] args) {
		Integer[] nums1 = {3,9,20,null,null,15,7};
		showResults(nums1); //expect true

		Integer[] nums2 = {1,2,2,3,3,null,null,4,4};
		showResults(nums2); //expect false
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);

		System.out.printf("\nThis tree is height balanced: %b\n\n",
						isBalanced(root));
	}

	//balanced binary tree:
	//a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
	//time: O(n), space: O(h); n is total nodes, h is tree's height
	public static boolean isBalanced(TreeNode root) {
		if(root == null) return true;
		return validateHeight(root) != -1; //-1 means invalid balanced binary tree.
	}

	//helper method
	private static int validateHeight(TreeNode root) {
		if(root == null) return 0;

		int leftSubTree = validateHeight(root.left);
		int rightSubTree = validateHeight(root.right);
		if(Math.abs(leftSubTree - rightSubTree) > 1 || //validate heights, if not valid, bubble up -1 immediately.
			leftSubTree == -1 || rightSubTree == -1) //if any sub tree's height is -1, bubble up -1 immediately, no need to check more.
				return -1; 
		
		//if height is valid, bubble up the call stack with max_height + 1
		return Math.max(leftSubTree + 1, rightSubTree + 1); //get max height between two sub trees.
	}

	//time: O(n), space: O(h); n is total nodes, h is height of the tree
	public static void printPreorder(TreeNode root) {
		if(root == null) return;
		else {
			System.out.printf("%s ", root.val);
			printPreorder(root.left);
			printPreorder(root.right);
		}
	}

	//time: O(n), space: O(h)
	public static void printInorder(TreeNode root) {
		if(root == null) return;
		else {
			printInorder(root.left);
			System.out.printf("%s ", root.val);
			printInorder(root.right);
		}
	}

	//time: O(n), space: O(h)
	public static void printPostorder(TreeNode root) {
		if(root == null) return;
		else {
			printPostorder(root.left);
			printPostorder(root.right);
			System.out.printf("%s ", root.val);
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

