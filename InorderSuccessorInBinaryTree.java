import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//element-prog 10.10
public class InorderSuccessorInBinaryTree {
	public static void main(String[] args) {
		Integer[] nums1 = {1,2,3,null,5,6,7,null,null,null,8};
		showResults(nums1, Integer.parseInt(args[0]));
	}

	private static void showResults(Integer[] nums, int k) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		addParents(root);
		printBinaryTree(root);

		TreeNode x = findNode(root, k);
		TreeNode rs = findInorderSuccessor2(root, x);

		if(x != null && rs != null) System.out.printf("\nInorder successor of %d: %d\n\n",
							x.val, rs.val);
	}

	//approach when tree have 'parent' attr.
	public static TreeNode findInorderSuccessor2(TreeNode root, TreeNode x) {
		if(x.right != null) return leftMostNode(x.right);
		
		TreeNode curr = x;
		while(curr.parent != null && curr.parent.right == curr) 
			curr = curr.parent;
		
		return curr.parent;
	}

	private static TreeNode n = null; //use this pointer as successor finder
	private static boolean nodeFound = false;

	//time: O(n), space: O(h)
	public static TreeNode findInorderSuccessor(TreeNode root, TreeNode x) {
		n = null;
		nodeFound = false;
		
		if(x.right != null) return leftMostNode(x.right); //case 1: if x.right != null
		dfs(root, x); //case 2
		return n;
	}

	private static TreeNode dfs(TreeNode root, TreeNode x) {
		if(root == null || nodeFound) return null;
		else if(root == x || (n = dfs(root.left, x)) != null ||
			(n = dfs(root.right, x)) != null) {
			if(n != null) {
				if(root.left == n) {
					nodeFound = true;
					n = root;
					return null;
				}
			return root;
			}
		}

		return root;
	}

	private static TreeNode leftMostNode(TreeNode node) {
		while(node.left != null) node = node.left;
		nodeFound = true;
		return node;
	}

	private static void addParents(TreeNode root) {
		if(root == null) return;
		else {
			if(root.left != null) root.left.parent = root;
			if(root.right != null) root.right.parent = root;
			addParents(root.left);
			addParents(root.right);
		}
	}

	//time: O(n), space: O(h)
	public static TreeNode findNode(TreeNode root, int k) {
		return findHelper(root, k);
	}

	//helper method, dfs
	private static TreeNode findHelper(TreeNode root, int k) {
		if(root == null) return null;
		else if(root.val == k) return root;
		else {
			TreeNode leftFound = findHelper(root.left, k);
			TreeNode rightFound = findHelper(root.right, k);
			if(leftFound == null) return rightFound;
			return leftFound;
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
		TreeNode parent;
		
		TreeNode() {}
		TreeNode(int val) {this.val = val;}
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
		TreeNode(int val, TreeNode left, TreeNode right, TreeNode parent) {
			this.val = val;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
	}
}
