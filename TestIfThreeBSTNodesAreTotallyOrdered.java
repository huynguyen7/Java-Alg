import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// element-prog 15.11
public class TestIfThreeBSTNodesAreTotallyOrdered {
	public static void main(String[] args) {
		Integer[] nums1 = {10,5,15,3,6,13,20,null,null,null,null,12,14,17,23};
		showResults(nums1, 20, 15, 17); // expect true
		showResults(nums1, 13, 10, 23); // expect false
	}

	// Check if the target node has valid ancestor node and descendant node
	private static void showResults(Integer[] nums, int target, int ancestor, int descendant) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		boolean rs = checkAncestorDescendant(root, target, ancestor, descendant);
		System.out.printf("\nTarget: %d, Ancestor: %d, Descendant: %d\nResult: %b\n\n",
						target, ancestor, descendant, rs);
	}

	// Time: O(h), space: O(h)
	public static boolean checkAncestorDescendant(TreeNode root, int target, int ancestor, int descendant) {
		// Check valid ancestor
		TreeNode ancestorNode = getNode(root, ancestor); // Time: O(h), space: O(h)
		boolean isValidAncestor = checkValidAncestor(ancestorNode, target); // Time: O(h), space: O(h)

		// Check valid descendant
		TreeNode targetNode = getNode(root, target); // Time: O(h), space: O(h)
		boolean isValidDescendant = checkValidAncestor(targetNode, descendant); // Time: O(h), space: O(h)
		
		return isValidAncestor && isValidDescendant;
	}

	private static boolean checkValidAncestor(TreeNode root, int target) {
		if(root == null) return false;
		else if(root.val == target) return true;
		else if(target < root.val) return checkValidAncestor(root.left, target);
		else return checkValidAncestor(root.right, target);
	}

	private static TreeNode getNode(TreeNode root, int ancestor) {
		if(root == null) return null;
		else if(root.val == ancestor) return root;
		else if(ancestor < root.val) return getNode(root.left, ancestor);
		else return getNode(root.right, ancestor);
	}

	// Time: O(n), space: O(n)
	private static TreeNode createBSTFromSortedArray(int[] nums) {
		if(nums.length == 0) return null;
		return createBSTFromSortedArrayHelper(nums, 0, nums.length - 1);
	}
	
	private static TreeNode createBSTFromSortedArrayHelper(int[] nums, int lo, int hi) {
		if(lo > hi) return null;
		else {
			int rootIndex = lo + (hi - lo) / 2;
			TreeNode root = new TreeNode(nums[rootIndex]);
			root.left = createBSTFromSortedArrayHelper(nums, lo, rootIndex - 1);
			root.right = createBSTFromSortedArrayHelper(nums, rootIndex + 1, hi);

			return root;
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
