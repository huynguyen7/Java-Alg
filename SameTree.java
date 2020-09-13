import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//leetcode 100
public class SameTree {
	public static void main(String[] args) {
		Integer[] nums1 = {1,2,3};
		Integer[] nums2 = {1,2,3};
		showResults(nums1, nums2); //expect true

		Integer[] nums3 = {1,2};
		Integer[] nums4 = {1,null,2};
		showResults(nums3, nums4); //expect false
		
		Integer[] nums5 = {1,2,1};
		Integer[] nums6 = {1,1,2};
		showResults(nums5, nums6); //expect false
	}

	private static void showResults(Integer[] nums1, Integer[] nums2) {
		System.out.println("------ShowResults------\n");
		TreeNode root1 = createTreeFromArray(nums1);
		printBinaryTree(root1);
		System.out.println();
		TreeNode root2 = createTreeFromArray(nums2);
		printBinaryTree(root2);

		System.out.printf("\nSame Tree: %b\n\n",
							isSameTree(root1, root2));
	}
	
	//time: O(n), space: O(h)
	public static boolean isSameTree(TreeNode p, TreeNode q) {
		return dfs(p, q);
	}

	//helper method, dfs
	private static boolean dfs(TreeNode p, TreeNode q) {
		if(p == null && q == null) return true;
		else if(p == null || q == null) return false;
		else if(p.val != q.val) return false;
		else return dfs(p.left, q.left) &&
					dfs(p.right, q.right);
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
