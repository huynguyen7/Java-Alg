import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//leetcode 938. 
public class RangeSumOfBST {
	public static void main(String[] args) {
		Integer[] nums1 = {10,5,15,3,7,null,18};
		int l1 = 7, r1 = 15;
		showResults(nums1, l1, r1); //expect 32

		Integer[] nums2 = {10,5,15,3,7,13,18,1,null,6};
		int l2 = 6, r2 = 10;
		showResults(nums2, l2, r2); //expect 23
	}

	private static void showResults(Integer[] nums, int l, int r) {
		System.out.println("------ShowResults------\n");
		System.out.printf("l: %d, r: %d\n\n", l, r);
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);

		System.out.printf("\nRange sum: %d\n", rangeSumBST(root, l, r));
	}
	
	//the BST guaranteed to have UNIQUE values.
	//time: O(n), space: O(h)
	public static int rangeSumBST(TreeNode root, int l, int r) {
		return dfs(root, l, r, 0);
	}

	//helper method, dfs
	private static int dfs(TreeNode root, int l, int r, int rs) {
		if(root == null) return 0;
		else if(root.val < l && root.val < r)
			return dfs(root.right, l, r, rs);
		else if(root.val > l && root.val > r)
			return dfs(root.left, l, r, rs);
		else {
			int leftSum = dfs(root.left, l, r, rs);
			int rightSum = dfs(root.right, l, r, rs);
			System.out.printf("left: %d, right: %d, root: %d\n", leftSum, rightSum, root.val);
			return leftSum + rightSum + root.val;
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
