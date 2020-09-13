import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

//leetcode 889
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
	public static void main(String[] args) {
		int[] pre1 = {3,9,20,15,7};
		int[] in1 = {9,3,15,20,7};
		showResults(pre1, in1);
	}

	private static void showResults(int[] preorder, int[] inorder) {
		System.out.println("------ShowResults------\n");
		System.out.println("Preorder: " + Arrays.toString(preorder));
		System.out.println("Inorder: " + Arrays.toString(inorder));

		TreeNode rs = buildTree(preorder, inorder);
		System.out.println();
		printBinaryTree(rs);
		System.out.println();
	}

	private static Map<Integer, Integer> map;

	//TreeNode.val is UNIQUE.
	//time: O(n), space: O(n)
	public static TreeNode buildTree(int[] preorder, int[] inorder) {
		map = new HashMap<>();
		for(int i = 0; i < inorder.length; ++i)
			map.put(inorder[i], i);
		return dfs(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
	}

	private static TreeNode dfs(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
		if(preStart > preEnd || inStart > inEnd) return null;
		else {
			TreeNode root = new TreeNode(preorder[preStart]);
			
			int inIndex = map.get(preorder[preStart]);
			int leftLength = inIndex - inStart;
			int rightLength = inEnd - inIndex;
			
			root.left = dfs(preorder, preStart + 1, preStart + leftLength, inorder, inStart, inIndex - 1);
			root.right = dfs(preorder, preEnd - rightLength + 1, preEnd, inorder, inIndex + 1, inEnd);

			return root;
		}
	}

	//time: O(n), space: O(1)
	public static void printInorderWithConstantSpace(TreeNode root) {
		TreeNode prev = null, curr = root, next;
		while(curr != null) {
			if(curr.parent == prev) {
				if(curr.left != null) next = curr.left;
				else {
					System.out.print(curr.val + " ");
					next = curr.right != null ? curr.right : curr.parent;
				}
			} else if(curr.left == prev) {
				System.out.print(curr.val + " ");
				next = curr.right != null ? curr.right : curr.parent;
			} else next = curr.parent;
			
			prev = curr;
			curr = next;
		}
	}

	//time: O(h), space: O(1)
	private static TreeNode rightMost(TreeNode root) {
		TreeNode curr = root;
		while(curr.right != null) curr = curr.right;
		return curr;
	}

	private static TreeNode leftMost(TreeNode root) {
		TreeNode curr = root;
		while(curr.left != null) curr = curr.left;
		return curr;
	}

	private static void addParents(TreeNode root) {
		if(root == null) return;
		addParentsHelper(root, root.left);
		addParentsHelper(root, root.right);
	}

	private static void addParentsHelper(TreeNode parent, TreeNode child) {
		if(child == null) return;
		else {
			child.parent = parent;
			addParentsHelper(child, child.left);
			addParentsHelper(child, child.right);
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
		TreeNode parent;
		
		TreeNode() {}
		TreeNode(int val) {this.val = val;}
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
		TreeNode(int val, TreeNode left, TreeNode right, TreeNode parent) {
			this.val = val;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
	}
}
