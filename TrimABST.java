import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// leetcode 669.
public class TrimABST {
	public static void main(String[] args) {
		Integer[] nums1 = {1,0,2};
		showResults(nums1, 1, 2); // expect {1,null,2}

		Integer[] nums2 = {3,0,4,null,2,null,null,null,null,1};
		showResults(nums2, 1, 3); // expect {3,2,null,1}

		Integer[] nums3 = {1};
		showResults(nums3, 1, 2); // expect {1}

		Integer[] nums4 = {1,null,2};
		showResults(nums4, 1, 3); // expect {1,null,2}

		Integer[] nums5 = {1,null,2};
		showResults(nums5, 2, 4); // expect {2}

		Integer[] nums6 = {3,1,4,null,2};
		showResults(nums6, 3, 4);
	}

	private static void showResults(Integer[] nums, int low, int high) {
		System.out.println("------ShowResults------\n");
		System.out.printf("low = %d, high = %d\n\n", low, high);
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		System.out.println();
		
		TreeNode rs = trimBST(root, low, high);
		printBinaryTree(rs);
		System.out.println();
	}
	
	// Time: O(n), space: O(h)
	public static TreeNode trimBST(TreeNode root, int low, int high) {
		if(root == null) return null;
		return dfs(root, low, high);
	}

	private static TreeNode dfs(TreeNode root, int low, int high) {
		if(root == null) return null;
		else if(root.val < low) return dfs(root.right, low, high);
		else if(root.val > high) return dfs(root.left, low, high);
		else {
			root.left = dfs(root.left, low, high);
			root.right = dfs(root.right, low, high);
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
