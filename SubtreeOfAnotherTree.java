import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//leetcode 572. 
public class SubtreeOfAnotherTree {
	public static void main(String[] args) {
		Integer[] nums1 = {3,4,5,1,2,};
		Integer[] t1 = {4,1,2};
		showResults(nums1, t1); //expect true

		Integer[] nums2 = {3,4,5,1,2,null,null,null,null,0};
		Integer[] t2 = {4,1,2};
		showResults(nums2, t2); //expect false
			
		Integer[] nums3 = {1,1};
		Integer[] t3 = {1};
		showResults(nums3, t3); //expect true
	}

	private static void showResults(Integer[] nums, Integer[] sub) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		System.out.println();
		
		TreeNode subT = createTreeFromArray(sub);
		printBinaryTree(subT);
		System.out.println();
		System.out.printf("\nSubtree: %b\n\n",
							isSubtree(root, subT));
	}
	
    //m is total nodes of tree s, n is total nodes of tree t
	//time: O(m^2 + n^2), space: O(m+n)
	public static boolean isSubtree(TreeNode s, TreeNode t) {
		if(s == null) return false;
		else if(isSameTree(s, t)) return true;
		else return isSubtree(s.left, t) || isSubtree(s.right, t);
	}

	private static boolean isSameTree(TreeNode s, TreeNode t) {
		if(s == null && t == null) return true;
		else if(s == null || t == null) return false;
		else if(s.val != t.val) return false;
		else return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
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
