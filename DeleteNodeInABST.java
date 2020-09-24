import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// leetcode 450 , element-prog 15.10
public class DeleteNodeInABST {
	public static void main(String[] args) {
		Integer[] nums1 = {4,2,7,1,3};
		showResults(nums1, 4);

		Integer[] nums2 = {10,5,15,null,null,13,20};
		showResults(nums2, 15);
	}

	private static void showResults(Integer[] nums, int val) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
		System.out.println();
		System.out.printf("Delete: %d\n", val);
		TreeNode rs = deleteNode(root, val);
		printBinaryTree(rs);
		System.out.println();
	}

	// Time: O(h), space: O(h)
	public static TreeNode deleteNode(TreeNode root, int key) {
		return dfs(root, key);
	}

	private static TreeNode dfs(TreeNode root, int key) {
		if(root == null) return null;
		else {
			if(key < root.val) root.left = dfs(root.left, key);
			else if(key > root.val) root.right = dfs(root.right, key);
			else { // found match key
				if(root.right == null) return root.left;
				else if(root.left == null) return root.right;
				
				TreeNode tmp = root;
				root = getLeftMost(tmp.right); // get min
				root.right = deleteMin(tmp.right); // remove min node on the right sub tree
				root.left = tmp.left; // keep the left sub tree
			}

			return root;
		}
	}

	// Time: O(h), space: O(h)
	private static TreeNode deleteMin(TreeNode root) {
		if(root.left == null) return root.right;
		else {
			root.left = deleteMin(root.left);
			return root;
		}
	}

	// Time: O(h), space: O(h)
	private TreeNode deleteMax(TreeNode root) {
		if(root.right == null) return root.left;
		else {
			root.right = deleteMin(root.right);
			return root;
		}
	}

	// Time: O(h), space: O(h)
	private static TreeNode getLeftMost(TreeNode root) {
		if(root == null) return root;
		
		TreeNode curr = root;
		while(curr.left != null) curr = curr.left;
		return curr;
	}
	
	// Time: O(h), space: O(h)
	private static TreeNode getRightMost(TreeNode root) {
		if(root == null) return root;
		
		TreeNode curr = root;
		while(curr.right != null) curr = curr.right;
		return curr;
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
