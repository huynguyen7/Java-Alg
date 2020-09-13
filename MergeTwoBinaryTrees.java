import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//leetcode 617
public class MergeTwoBinaryTrees {
	public static void main(String[] args) {
		Integer[] nums1 = {1,3,2,5};
		Integer[] nums2 = {2,1,3,null,4,null,7};
		showResults(nums1, nums2); //expect {3,4,5,5,4,7}
	}

	private static void showResults(Integer[] nums1, Integer[] nums2) {
		System.out.println("------ShowResults------\n");
		TreeNode root1 = createTreeFromArray(nums1);
		TreeNode root2 = createTreeFromArray(nums2);
		printBinaryTree(root1);
		System.out.println();
		printBinaryTree(root2);

		System.out.println("\nOUTPUT");
		TreeNode rs = mergeTrees(root1, root2);
		printBinaryTree(rs);
		System.out.println();
	}
	
	//The merge rule is that if two nodes overlap,
	//then sum node values up as the new value of the merged node.
	//Otherwise, the NOT null node will be used as the node of new tree.
	//time: O(n), space: O(h)
	public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		TreeNode rs = null;
		return dfs(t1, t2, rs);
	}

	private static TreeNode dfs(TreeNode t1, TreeNode t2, TreeNode rs) {
		if(t1 == null && t2 == null) return null;
		if(t1 == null || t2 == null) return t1 == null ? t2 : t1;
		else {
			rs = new TreeNode(t1.val + t2.val);
			rs.left = dfs(t1.left, t2.left, rs);
			rs.right = dfs(t1.right, t2.right, rs);
			return rs;
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
