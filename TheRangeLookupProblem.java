import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;

// element-prog 15.12
public class TheRangeLookupProblem {
	public static void main(String[] args) {
		Integer[] nums1 = {10,5,15,3,6,13,20,null,null,null,null,12,14,17,23};
		int[] interval1 = {6,13};
		showResults(nums1, interval1); // expect {6,10,12,13}
	}

	// Input must be a BST.
	private static void showResults(Integer[] nums, int[] interval) {
		System.out.println("------ShowResults------\n");
		System.out.println("Interval: " + Arrays.toString(interval));
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
		List<Integer> rs = rangeLookupInBSTI(root, interval);
		System.out.println("\n" + rs.toString() + "\n");
	}

	private static List<Integer> rs;

	// better approach
	// since it might not need to go through all the nodes in the tree
	// Time: O(n), space: O(n)
	public static List<Integer> rangeLookupInBSTI(TreeNode root, int[] interval) {
		rs = new ArrayList<>();
		dfsI(root, interval);
		return rs;
	}

	private static void dfsI(TreeNode root, int[] interval) {
		if(root == null) return;
		else {
			if(interval[0] <= root.val && root.val <= interval[1]) { // lie inside interval
				rs.add(root.val);
				dfsI(root.left, interval);
				dfsI(root.right, interval);
			} else if(root.val < interval[0]) dfsI(root.right, interval);
			else dfsI(root.left, interval);
		}
	}

	// brute-force approach
	// Time: O(n), space: O(n)
	public static List<Integer> rangeLookupInBSTII(TreeNode root, int[] interval) {
		rs = new ArrayList<>();
		dfsII(root, interval);
		return rs;
	}

	private static void dfsII(TreeNode root, int[] interval) {
		if(root == null) return;
		else {
			if(interval[0] <= root.val && root.val <= interval[1]) // lie inside interval
				rs.add(root.val);
			dfsII(root.left, interval);
			dfsII(root.right, interval);
		}
	}
	
	//time: O(n), space: O(h)
	public static boolean isSymmetric(TreeNode root) {
		if(root == null) return true;
		if(root.right == null && root.left == null) return true;
		return validateSymmetric(root.left, root.right);
	}

	//helper method, dfs
	private static boolean validateSymmetric(TreeNode left, TreeNode right) {
		if(left == null && right == null) return true;
		if(left == null || right == null) return false;
		else {
			if(left.val != right.val) return false;
			boolean x = validateSymmetric(left.left, right.right);
			boolean y = validateSymmetric(left.right, right.left);
			
			return x && y;
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
