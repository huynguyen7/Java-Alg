import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// leetcode 1448.
public class CountGoodNodesInBinaryTree {
	public static void main(String[] args) {
		Integer[] nums1 = {3,1,4,3,null,1,5};
		showResults(nums1); // expect 4

		Integer[] nums2 = {3,3,null,4,2};
		showResults(nums2); // expect 3

		Integer[] nums3 = {1};
		showResults(nums3); // expect 1
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
		int rs = goodNodes(root);
		System.out.printf("\nNumber of good nodes: %d\n\n", rs);
	}

	// Node X in the tree is named GOOD:
	// If in the path from root to X,
	// there are no nodes with a value greater than X.

	private static int goodNodesCount;
	
	// Time: O(n), space: O(h)
	public static int goodNodes(TreeNode root) {
		if(root == null) return 0;
		
		goodNodesCount = 0;
		dfs(root, root.val);
		return goodNodesCount;
	}

	private static void dfs(TreeNode root, int prevMax) {
		if(root == null) return;
		else if(root.val < prevMax) {
			dfs(root.left, prevMax);
			dfs(root.right, prevMax);
		} else {
			goodNodesCount++;
			dfs(root.left, root.val);
			dfs(root.right, root.val);
		}
	}

	private static boolean isLeaf(TreeNode node) {
		return node.left == null && node.right == null;
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
