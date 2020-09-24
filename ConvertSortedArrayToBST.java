import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;

// element-prog 15.9
public class ConvertSortedArrayToBST {
	public static void main(String[] args) {
		int[] nums1 = {2,3,5,7,11,13,17,19,23};
		showResults(nums1);
		
		int[] nums2 = {3,5,6,10,13,15,20};
		showResults(nums2);
		
		int[] nums3 = {1,2,3,4,5,6};
		showResults(nums3);
		
		int[] nums4 = {5,10,13,15,20};
		showResults(nums4);
	}

	private static void showResults(int[] nums) {
		System.out.println("------ShowResults------\n");
		System.out.println(Arrays.toString(nums));
		
		System.out.println();
		TreeNode rs = buildMinHeightBSTFromSortedArray(nums);
		printBinaryTree(rs);
		System.out.println();
	}

	// Time: O(n), space: O(n)
	public static TreeNode buildMinHeightBSTFromSortedArray(int[] nums) {
		if(nums.length == 0) return null;
		return dfs(nums, 0, nums.length - 1);
	}

	private static TreeNode dfs(int[] nums, int start, int end) {
		if(start > end) return null;
		else {
			int rootIndex = start + (end - start) / 2;
			TreeNode root = new TreeNode(nums[rootIndex]);
			root.left = dfs(nums, start, rootIndex - 1);
			root.right = dfs(nums, rootIndex + 1, end);

			return root;
		}
	}

	private static List<Integer> transformToList(int[] nums) {
		List<Integer> rs = new ArrayList<>();
		for(int i = 0; i < nums.length; ++i)
			rs.add(nums[i]);
		return rs;
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
