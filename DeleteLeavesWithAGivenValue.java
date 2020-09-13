import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//leetcode 1325. 
public class DeleteLeavesWithAGivenValue {
	public static void main(String[] args) {
		Integer[] nums1 = {1,2,3,2,null,2,4,};
		showResults(nums1, 2); //expect {1,null,3,null,null,null,4}

		Integer[] nums2 = {1,3,3,3,2};
		showResults(nums2, 3); //expect {1,3,null,null,2}
	}

	private static void showResults(Integer[] nums, int target) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		System.out.println();
		
		TreeNode rs = removeLeafNodes(root, target);
		printBinaryTree(rs);
		System.out.println();
	}
	
	//better approach
	//time: O(n), space: O(h)
	public static TreeNode removeLeafNodes(TreeNode root, int target) {
		if(root == null) return root;
		else {
			root.left = removeLeafNodes(root.left, target);
			root.right = removeLeafNodes(root.right, target);
			
			return isLeaf(root) && root.val == target ? null : root;
		}
	}
	
	//naive approach
	//time: O(n^2), space: O(h)
	public static TreeNode removeLeafNodes2(TreeNode root, int target) {
		if(root == null) return null;
		while(hasTarget(root, target)) {
			root = dfs(root, target);
		}

		return root;
	}
	
	private static TreeNode dfs(TreeNode root, int target) {
		if(isLeaf(root) && root.val == target) return null;
		else if(isLeaf(root)) return root;
		else {
			if(root.left != null) root.left = dfs(root.left, target);
			if(root.right != null) root.right = dfs(root.right, target);
			return root;
		}
	}

	private static boolean isLeaf(TreeNode node) {
		return node.right == null && node.left == null;
	}
	
	private static boolean hasTarget(TreeNode root, int target) {
		if(root == null) return false;
		else if(isLeaf(root) && root.val == target) return true;
		else {
			boolean leftCheck = hasTarget(root.left, target);
			boolean rightCheck = hasTarget(root.right, target);
			return leftCheck || rightCheck;
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
