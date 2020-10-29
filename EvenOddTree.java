import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// leetcode 1609.
public class EvenOddTree {
	public static void main(String[] args) {
		Integer[] nums1 = {1,10,4,3,null,7,9,12,8,null,null,6,null,null,2};
		showResults(nums1); // expect true

		Integer[] nums2 = {5,4,2,3,3,7};
		showResults(nums2); // expect false

		Integer[] nums3 = {5,9,1,3,5,7};
		showResults(nums3); // expect false

		Integer[] nums4 = {1};
		showResults(nums4); // expect true

		Integer[] nums5 = {11,8,6,1,3,9,11,30,20,18,16,12,10,4,2,17};
		showResults(nums5); // expect true
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
		boolean rs = isEvenOddTree(root);
		System.out.printf("\nOdd Even Tree: %b\n\n", rs);
	}

	// Using BFS.
	// Time: O(n), space: O(h)
	public static boolean isEvenOddTree(TreeNode root) {
		if(root == null) return true;
		
		int currDepth = 0;
		Deque<TreeNode> queue = new LinkedList<>();
		queue.addLast(root);

		while(!queue.isEmpty()) {
			int currDepthSize = queue.size();
			int prevVal = currDepth % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
			
			while(currDepthSize-- != 0) {
				TreeNode node = queue.removeFirst();
				if(currDepth % 2 == 0) {
					if(node.val <= prevVal || node.val % 2 == 0)
						return false;
				} else {
					if(node.val >= prevVal || node.val % 2 != 0)
						return false;
				}
				prevVal = node.val;
				
				if(node.left != null) queue.addLast(node.left);
				if(node.right != null) queue.addLast(node.right);
			}

			currDepth++;
		}

		return true;
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
