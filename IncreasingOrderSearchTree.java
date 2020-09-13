import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//leetcode 897. 
public class IncreasingOrderSearchTree {
	public static void main(String[] args) {
		Integer[] nums1 = {5,3,6,2,4,null,8,
						1,null,null,null,null,null,7,9};
		showResults(nums1);
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
		System.out.println();
		TreeNode rs = increasingBST(root);
		if(rs != null) printBinaryTree(rs);
	}
	
	private static TreeNode curr;

	//better approach
	//time: O(n), space: O(h)
	public static TreeNode increasingBST(TreeNode root) {
		TreeNode dummy = new TreeNode(0);
		curr = dummy;
		
		inOrderRelinking(root);
		return dummy.right;
	}
	
	//helper method, using In-order traversal
	private static void inOrderRelinking(TreeNode node) {
		if(node == null) return;
		else {
			inOrderRelinking(node.left);
			node.left = null;
			curr.right = node;
			curr = node;
			inOrderRelinking(node.right);
		}
	}
	
	//time: O(n), space: O(n)
	public static TreeNode increasingBST2(TreeNode root) {
		List<Integer> holder = new ArrayList<>(); //space: O(n)
		dfs(root, holder);
		
		TreeNode node = new TreeNode(0);
		TreeNode dummy = node;
		
		for(int i = 0; i < holder.size(); ++i) {
			node.right = new TreeNode(holder.get(i));
			node = node.right;
		}
		
		return dummy.right;
	}

	//helper method, dfs, using Inorder traversal
	private static void dfs(TreeNode root, List<Integer> holder) {
		if(root == null) return;
		else {
			dfs(root.left, holder);
			holder.add(root.val);
			dfs(root.right, holder);
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
