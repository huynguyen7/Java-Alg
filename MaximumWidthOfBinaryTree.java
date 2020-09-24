import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// leetcode 662
public class MaximumWidthOfBinaryTree {
	public static void main(String[] args) {
		Integer[] nums1 = {1,3,2,5,3,null,9};
		showResults(nums1); // expect 4

		Integer[] nums2 = {1,3,null,5,3};
		showResults(nums2); // expect 2

		Integer[] nums3 = {1,3,2,5};
		showResults(nums3); // expect 2
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
		int maxWidth = widthOfBinaryTree(root);
		System.out.printf("\nMaximum Width: %d\n\n", maxWidth);
	}

	// using bfs
	// Time: O(n), space: O(2^h)
	public static int widthOfBinaryTree(TreeNode root) {
		Deque<NodeIndexPair> queue = new LinkedList<>();
		queue.add(new NodeIndexPair(root, 0));
		
		int maxWidth = Integer.MIN_VALUE; 
	
		while(!queue.isEmpty()) {
			int totalNodesAtCurrDepth = queue.size();
			int width = 0;
			int firstNodeIndex = queue.getFirst().index;

			while(totalNodesAtCurrDepth-- > 0) {
				NodeIndexPair tmp = queue.removeFirst();
				width = tmp.index - firstNodeIndex + 1;
				
				if(tmp.node.left != null)
					queue.addLast(new NodeIndexPair(tmp.node.left, tmp.index * 2 + 1));
				if(tmp.node.right != null)
					queue.addLast(new NodeIndexPair(tmp.node.right, tmp.index * 2 + 2));
			}

			maxWidth = Math.max(maxWidth, width);
		}

			return maxWidth;
	}
	
	static class NodeIndexPair {
		TreeNode node;
		int index;
		
		public NodeIndexPair(TreeNode node, int index) {
			this.node = node;
			this.index = index;
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
