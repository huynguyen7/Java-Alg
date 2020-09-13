import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;

//leetcode 108
public class ConvertSortedArrayToBinarySearchTree {
	public static void main(String[] args) {
		int[] nums1 = {-10,-3,0,5,9,10};
		showResults(nums1);
		
		int[] nums2 = {-10,-3,-1,0,5,9,10};
		showResults(nums2);
	}

	private static void showResults(int[] nums) {
		System.out.println("------ShowResults------\n");
		System.out.println("INPUT: " + Arrays.toString(nums) + "\n");
		TreeNode rs = sortedArrayToBST(nums);
		printBinaryTree(rs);
		System.out.println();
	}
	
	//time: O(n), space: O(n)
	public static TreeNode sortedArrayToBST(int[] nums) {
		if(nums.length == 0 || nums == null) return null;
		return dfs(nums, 0, nums.length - 1);
	}

	private static TreeNode dfs(int[] nums, int lo, int hi) {
		if(lo > hi) return null;
		else {
			int mid = lo + (hi - lo) / 2;
			TreeNode root = new TreeNode(nums[mid]);
			root.left = dfs(nums, lo, mid - 1);
			root.right = dfs(nums, mid + 1, hi);

			return root;
		}
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
