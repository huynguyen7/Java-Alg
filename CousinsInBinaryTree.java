import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// leetcode 993.
public class CousinsInBinaryTree {
	public static void main(String[] args) {
		Integer[] nums1 = {1,2,3,4};
		showResults(nums1, 4, 3); // expect false

		Integer[] nums2 = {1,2,3,null,4,null,5};
		showResults(nums2, 5, 4); // expect true

		Integer[] nums3 = {1,2,3,null,4};
		showResults(nums3, 2, 3); // expect false

		Integer[] nums4 = {1,2,3,null,null,null,4,null,5};
		showResults(nums4, 3, 2); // expect false
	}

	private static void showResults(Integer[] nums, int x, int y) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
		System.out.printf("\n x = %d, y = %d\n", x, y);
		boolean rs = isCousins(root, x, y);
		System.out.printf("Result: %b\n\n", rs);
	}

	// Constraints:
	// - x != y
	// - Node.val is UNIQUE.

	// Using BFS.
	// Time: O(n), space: O(2^h)
	public static boolean isCousins(TreeNode root, int x, int y) {
		if(root == null || root.val == x || root.val == y)
			return false;
		
		Deque<TreeNode> queue = new LinkedList<>();
		queue.addLast(root);
		int depthX = -1, depthY = -1;
		int currDepth = 0;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-- != 0) {
				TreeNode tmp = queue.removeFirst();
				if(tmp.val == x) depthX = currDepth;
				else if(tmp.val == y) depthY = currDepth;
				
				// check if same parent for x and y.
				if(tmp.left != null && (tmp.left.val == x || tmp.left.val == y) &&
					tmp.right != null && (tmp.right.val == x || tmp.right.val == y))
					return false;

				if(tmp.left != null)
					queue.addLast(tmp.left);
				if(tmp.right != null)
					queue.addLast(tmp.right);
			}

			currDepth++;
		}
		
		return depthX == depthY;
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
