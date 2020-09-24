import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// leetcode 199. 
public class BinaryTreeRightSideView {
	public static void main(String[] args) {
		Integer[] nums1 = {1,2,3,null,5,null,4};
		showResults(nums1); // expect {1,3,4}

		Integer[] nums2 = {1,2,null,3};
		showResults(nums2); // expect {1,2,3}
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
		List<Integer> rs = rightSideView(root);
		System.out.println();
		System.out.println(rs.toString() + "\n");
	}

	private static List<Integer> rs;

	// Time: O(h), space: O(h)
	public static List<Integer> rightSideView(TreeNode root) {
		rs = new ArrayList<>();
		dfs(root, 0);
		return rs;
	}
	
	private static void dfs(TreeNode root, int currDepth) {
		if(root == null) return;
		else { // node-right-left traversal
			if(currDepth == rs.size()) rs.add(root.val);
			dfs(root.right, currDepth + 1);
			dfs(root.left, currDepth + 1);
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
