import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// leetcode 96.
public class UniqueBinarySearchTrees {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		showResults(n);
	}

	private static void showResults(int n) {
		System.out.println("------ShowResults------\n");
		System.out.printf("n = %d\n", n);
		int rs = numTrees(n);
		System.out.printf("Num Trees: %d\n\n", rs);
	}

	// How many structurally unique BST that store values from 1..n ?
	// Trick: Using the nth Catalan Number.

	// GOOD EXPLANATION:
	// https://www.youtube.com/watch?v=GgP75HAvrlY

	// DP approach.
	// Bottom-up DP.
	// Time: O(n^2), space: O(n)
	public static int numTrees(int n) {
		if(n <= 1) return n;
		
		int[] cache = new int[n + 1];
		cache[0] = 1; // init base case;

		for(int i = 1; i <= n; ++i) {
			int count = 0;
			for(int j = 1; j <= i; ++j)
				count += cache[j - 1] * cache[i - j];
			cache[i] = count;
		}

		return cache[n];
	}

	// DP approach.
	// Top-down DP.
	// Time: O(n^2), space: O(n)
	public static int numTreesI(int n) {
		if(n <= 1) return n;

		int[] cache = new int[n + 1];
		cache[0] = 1; // init base case

		dfsI(n , cache);
		return cache[n];
	}

	private static int dfsI(int n, int[] cache) {
		if(n == 0 || n == 1) return 1;
		else if(cache[n] == 0) { 
			int count = 0;
			for(int i = 1; i <= n; ++i)
				count += dfsI(i - 1, cache) * dfsI(n - i, cache);
			
			cache[n] = count;
		}

		return cache[n];
	}

	// Recursive approach.
	// Time: O(4^n), space: O(4^n)
	public static int numTreesII(int n) {
		if(n <= 1) return n;
		return dfsII(n);
	}

	private static int dfsII(int n) {
		if(n == 0 || n == 1) return 1;
		else {
			int count = 0;
			for(int i = 1; i <= n; ++i) // time: O(n)
				count += dfsII(i - 1) * dfsII(n - i); // Apply nth Catalan formula
			return count;
		}
	}

	// Time: O(n), space: O(h); n the number of nodes, h is height of the tree
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
