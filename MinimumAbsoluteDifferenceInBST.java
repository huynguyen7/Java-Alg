import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// leetcode 530
public class MinimumAbsoluteDifferenceInBST {
	public static void main(String[] args) {
		Integer[] nums1 = {1,null,3,null,null,2};
		showResults(nums1); // expect 1

		Integer[] nums2 = {4,2,6,1,3};
		showResults(nums2); // expect 1

		Integer[] nums3 = {30,15,45,1,null,32};
		showResults(nums3); // expect 2
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
		int rs = getMinimumDifferenceI(root);
		System.out.printf("\nMinimum Absolute Diff: %d\n\n", rs);
	}

	private static int minDiff;	
	private static int prev;
	
	// better approach
	// Time: O(n), space: O(h)
	public static int getMinimumDifferenceI(TreeNode root) {
		minDiff = Integer.MAX_VALUE;
		prev = Integer.MAX_VALUE; // MAX_VALUE means there is no previous value
		dfsI(root);
		return minDiff;
	}

	private static void dfsI(TreeNode root) {
		if(root == null) return;
		else { // inorder traversal shows up a SORTEDNESS
			dfsI(root.left);
			if(prev != Integer.MAX_VALUE)
				minDiff = Math.min(minDiff, Math.abs(prev - root.val));
			prev = root.val;
			dfsI(root.right);
		}
	}

	// brute-force approach
	// Time: O(n*h), space: O(h)
	public static int getMinimumDifferenceII(TreeNode root) {
		minDiff = Integer.MAX_VALUE;
		dfsII(root);
		return minDiff;
	}

	private static void dfsII(TreeNode root) {
		if(root == null) return;
		else {
			TreeNode maxLeftSubtree = getMax(root.left);
			if(maxLeftSubtree != null)
				minDiff = Math.min(minDiff, Math.abs(root.val - maxLeftSubtree.val));
			
			TreeNode minRightSubtree = getMin(root.right);
			if(minRightSubtree != null)
				minDiff = Math.min(minDiff, Math.abs(root.val - minRightSubtree.val));
			
			dfsII(root.left);
			dfsII(root.right);
		}
	}

	// Time: O(h), space: O(1)
	private static TreeNode getMin(TreeNode root) {
		if(root == null) return null;
		TreeNode min = root;
		while(min.left != null) min = min.left;

		return min;
	}

	// Time: O(h), space: O(1)
	private static TreeNode getMax(TreeNode root) {
		if(root == null) return null;
		TreeNode max = root;
		while(max.right != null) max = max.right;
		
		return max;
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
