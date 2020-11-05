import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// leetcode 872.
public class LeafSimilarTrees {
	public static void main(String[] args) {
		Integer[] nums1 = {3,5,1,6,2,9,8,null,null,7,4};
		Integer[] nums2 = {3,5,1,6,7,4,2,null,null,null,null,null,null,9,8};
		showResults(nums1, nums2); // expect true

		Integer[] nums3 = {1,2,3};
		Integer[] nums4 = {1,3,2};
		showResults(nums3, nums4); // expect false
	}

	private static void showResults(Integer[] nums1, Integer[] nums2) {
		System.out.println("------ShowResults------\n");
		TreeNode root1 = createTreeFromArray(nums1);
		printBinaryTree(root1);
		System.out.println();
		TreeNode root2 = createTreeFromArray(nums2);
		printBinaryTree(root2);
		
		boolean rs = leafSimilar(root1, root2);
		System.out.printf("\n Leaf Similar: %b\n\n", rs);
	}

	// n is the number of nodes in root1 tree.
	// m is the number of nodes in root2 tree.
	// Time: O(n+m), space: O(logn + logm)
	public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
		if(root1 == null && root2 == null) return true;
		if(root1 == null || root2 == null) return false;

		List<Integer> leaves1 = new ArrayList<>();
		getLeaves(root1, leaves1);
		List<Integer> leaves2 = new ArrayList<>();
		getLeaves(root2, leaves2);

		if(leaves1.size() != leaves2.size()) return false;
		for(int i = 0; i < leaves1.size(); ++i) {
			if(Integer.compare(leaves1.get(i), leaves2.get(i)) != 0)
				return false;
		}
		
		return true;
	}

	private static void getLeaves(TreeNode root, List<Integer> leaves) {
		if(isLeaf(root)) {
			leaves.add(root.val);
		} else {
			if(root.left != null) getLeaves(root.left, leaves);
			if(root.right != null) getLeaves(root.right, leaves);
		}
	}

	private static boolean isLeaf(TreeNode node) {
		if(node == null) return false;
		return node.left == null && node.right == null;
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
