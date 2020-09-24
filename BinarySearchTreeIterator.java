import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;

// leetcode 173
public class BinarySearchTreeIterator {
	public static void main(String[] args) {
		Integer[] nums1 = {7,3,15,null,null,9,20};
		TreeNode root = createTreeFromArray(nums1);
		
		BinarySearchTreeIterator iterator = new BinarySearchTreeIterator(root);
		System.out.println(iterator.next());    // return 3
		System.out.println(iterator.next());    // return 7
		System.out.println(iterator.hasNext()); // return true
		System.out.println(iterator.next());    // return 9
		System.out.println(iterator.hasNext()); // return true
		System.out.println(iterator.next());    // return 15
		System.out.println(iterator.hasNext()); // return true
		System.out.println(iterator.next());    // return 20
		System.out.println(iterator.hasNext()); // return false
	}

	private Iterator<Integer> iterator;

	// Time: O(n), space: O(n)
	public BinarySearchTreeIterator(TreeNode root) {
		List<Integer> nums = new ArrayList<>();
		addingNodesInorder(root, nums);
		iterator = nums.iterator();
	}

	// Time: O(1)
	public int next() {
		return iterator.next();
	}

	// Time: O(1)
	public boolean hasNext() {
		return iterator.hasNext();
	}

	private static void addingNodesInorder(TreeNode root, List<Integer> nums) {
		if(root == null) return;
		else {
			addingNodesInorder(root.left, nums);
			nums.add(root.val);
			addingNodesInorder(root.right, nums);
		}
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
		long startTime = System.nanoTime();
		boolean rs = isSymmetric(root);
		long endTime = System.nanoTime();

		System.out.printf("\nSymmetric Tree: %b, Time: %d ms\n\n",
							rs, endTime - startTime);
		
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
