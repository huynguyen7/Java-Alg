import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// leetcode 235, element-prog 15.4
public class LowestCommonAncestorOfABinarySearchTree {
	public static void main(String[] args) {
		Integer[] nums1 = {6,2,8,0,4,7,9,null,null,3,5};
		int p1 = 2, q1 = 8;
		showResults(nums1, p1, q1); //expect 6

		int p2 = 2, q2 = 4;
		showResults(nums1, p2, q2); //expect 2
	}
	// The lowest common ancestor(LCA) is
	// defined between two nodes p and q as the lowest node
	// that has both p and q as descendants
	// where we allow a node to be a descendant of itself.

	// Constraints:
	// all the nodes' values will be UNIQUE.
	// p and q are different and both values always exist in the tree.
	private static void showResults(Integer[] nums, int p, int q) {
		System.out.println("------ShowResults------\n");
		System.out.printf("p: %d, q: %d\n", p, q);
		TreeNode root = createTreeFromArray(nums);
		TreeNode pNode = new TreeNode(p);
		TreeNode qNode = new TreeNode(q);
		printBinaryTree(root);

		TreeNode rs = lowestCommonAncestorI(root, pNode, qNode);
		System.out.println("\nCommon ancestor node is: " + rs.val + "\n");
	}

	// iterative approach
	// Time: O(h), space: O(1)
	public static TreeNode lowestCommonAncestorI(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode curr = root;
		
		while(curr != null) {
			if(curr.val < p.val && curr.val < q.val) curr = curr.right;
			else if(curr.val > p.val && curr.val > q.val) curr = curr.left;
			else return curr;	
		}
		
		return curr;
	}

	// recursive approach
	// Time: O(h), space: O(h)
	public static TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
		if(root == null) return null;
		return dfs(root, p, q);
	}

	//helper method, dfs
	private static TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
		if(root.val > p.val && root.val > q.val)
			return dfs(root.left, p, q);
		else if(root.val < p.val && root.val < q.val)
			return dfs(root.right, p, q);
		else return root;
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
