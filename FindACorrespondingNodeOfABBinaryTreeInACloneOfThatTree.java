import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//leetcode 1379. 
public class FindACorrespondingNodeOfABBinaryTreeInACloneOfThatTree {
	public static void main(String[] args) {
		Integer[] nums1 = {7};
		int t1 = 7;
		showResults(nums1, t1); //expect 7

		Integer[] nums2 = {7,4,3,null,null,6,19};
		int t2 = 3;
		showResults(nums2, t2); //expect 3

		Integer[] nums3 = {1,2,3,4,5,6,7,8,9,10};
		int t3 = 5;
		showResults(nums3, t3); //expect 5

		Integer[] nums4 = {1,2,null,3};
		int t4 = 2;
		showResults(nums4, t4); //expect 2
	}

	private static void showResults(Integer[] nums, int t) {
		System.out.println("------ShowResults------\n");
		System.out.println("TARGET: " + t);
		final TreeNode original = createTreeFromArray(nums);
		printBinaryTree(original);
		final TreeNode copy = createTreeFromArray(nums); //a clone of original tree
		final TreeNode target = findTarget(original, t); //reference to target node of original tree

		TreeNode rs = getTargetCopy(original, copy, target);
		printBinaryTree(rs);
		System.out.println(rs != target);
	}

	private static TreeNode findTarget(TreeNode root, int t) {
		if(root == null) return null;
		else if(root.val == t) return root;
		else {
			TreeNode findLeft = findTarget(root.left, t);
			TreeNode findRight = findTarget(root.right, t);
			if(findLeft != null) return findLeft;
			return findRight;
		}
	}
	
	//The values of nodes of the tree are all UNIQUE.
	//time: O(n), space: O(h)
	public static final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
		return dfs(original, cloned, target);
	}

	//helper method, dfs
	private static final TreeNode dfs(final TreeNode original, final TreeNode cloned, final TreeNode target) {
		if(original == null || cloned == null) return null;
		else if(cloned.val == target.val) return cloned;
		else {
            TreeNode left = dfs(original.left, cloned.left, target);
            TreeNode right = dfs(original.right, cloned.right, target);
			
            if(left == null) return right;
            return left;
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
