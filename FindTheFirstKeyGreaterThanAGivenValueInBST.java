import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// element-prog 15.2
public class FindTheFirstKeyGreaterThanAGivenValueInBST {
	public static void main(String[] args) {
		Integer[] nums1 = {2,1,3};
		showResults(nums1, 1); // expect 2

		Integer[] nums2 = {10,5,15,null,null,13,20};
		showResults(nums2, 19); // expect 20
		showResults(nums2, 12); // expect 13

		Integer[] nums3 = {4,2,7,1,3,6,11};
		showResults(nums3, 12); // expect null
	}

	private static void showResults(Integer[] nums, int k) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
		TreeNode rs = findFirstGreaterThanKInorder(root, k);
		if(rs == null) System.out.printf("\n k: %d, rs: null\n\n", k);
		else System.out.printf("\nk: %d, rs: %d\n\n", k, rs.val);
	}
	
	// getting the first node in INORDER traversal which has val greater than k
	// using iterative
	// Time: O(h), space: O(1)
	public static TreeNode findFirstGreaterThanKInorder(TreeNode root, int k) {
		TreeNode curr = root, firstSoFar = null;

		while(curr != null) {
			if(curr.val <= k) curr = curr.right;
			else { // curr.val > k
				firstSoFar = curr;
				curr = curr.left;
			}
		}
		
		return firstSoFar;
	}

	// getting the first node in PREORDER traversal which has val greater than k
	// using recursive
	// Time: O(h), space: O(h)
	public static TreeNode findFirstGreaterThanKPreorder(TreeNode root, int k) {
		if(root == null) return root;
		else if(root.val > k) return root;
		else return findFirstGreaterThanKPreorder(root.right, k);
	}

	// Time: O(n), space: O(h); n is total nodes, h is height of the tree
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
