import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//leetcode 226 
public class InvertBinaryTree {
	public static void main(String[] args) {
		Integer[] nums1 = {4,2,7,1,3,6,9};
		showResults(nums1); //expect {4,7,2,9,6,3,1}
		
		Integer[] nums2 = {1,2};
		showResults(nums2); //expect {1,null,2}
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);

		System.out.println();
		TreeNode rs = invertTree(root);
		printBinaryTree(rs);
		System.out.println();
	}
	
	//time: O(n), space: O(n)
	public static TreeNode invertTree(TreeNode root) {
		if(root == null) return null;

		TreeNode rs = new TreeNode(root.val);
		dfs(root.left, root.right, rs);
		return rs;
	}

	//pre-order traversal
	private static void dfs(TreeNode leftSubTree, TreeNode rightSubTree, TreeNode rs) {
		if(leftSubTree == null && rightSubTree == null) return;
		else {
			rs.left = rightSubTree == null ? null : new TreeNode(rightSubTree.val);
			rs.right = leftSubTree == null ? null : new TreeNode(leftSubTree.val);

			if(rightSubTree != null) dfs(rightSubTree.left, rightSubTree.right, rs.left);
			if(leftSubTree != null) dfs(leftSubTree.left, leftSubTree.right, rs.right);
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
