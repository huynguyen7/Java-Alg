import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//leetcode 236, element-prog 10.3
public class LowestCommonAncestorOfABinaryTree {
	public static void main(String[] args) {
		Integer[] nums1 = {3,5,1,6,2,0,8,null,null,7,4};
		int p1 = 5, q1 = 1;
		showResults(nums1, p1, q1); //expect 3

		int p2 = 5, q2 = 4;
		showResults(nums1, p2, q2); //expect 5
	}

	//constraints:
	//all the nodes' values will be UNIQUE.
	//p and q are different and both values always exist in the tree.
	private static void showResults(Integer[] nums, int p, int q) {
		System.out.println("------ShowResults------\n");
		System.out.printf("p: %d, q: %d\n", p, q);
		TreeNode root = createTreeFromArray(nums);
		TreeNode pNode = new TreeNode(p);
		TreeNode qNode = new TreeNode(q);
		printBinaryTree(root);

		TreeNode rs = lowestCommonAncestor(root, pNode, qNode);
		System.out.println("\nCommon ancestor node is: " + rs.val + "\n");
	}
	
	//The lowest common ancestor(LCA) is
	//defined between two nodes p and q as the lowest node
	//that has both p and q as descendants
	//where we allow a node to be a descendant of itself.
	//time: O(n), space: O(h)
	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		return dfs(root, p, q);
	}

	//helper method, dfs
	private static TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
		if(root == null) return null;
		else if(root.val == p.val || root.val == q.val) return root;
		else {
			TreeNode leftSearch = dfs(root.left, p, q);
			TreeNode rightSearch = dfs(root.right, p, q);

			if(leftSearch == null) return rightSearch;
			if(rightSearch == null) return leftSearch;
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
