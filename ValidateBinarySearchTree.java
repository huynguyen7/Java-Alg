import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Deque;

//leetcode 98, element-prog 15.1
public class ValidateBinarySearchTree {
	public static void main(String[] args) {
		Integer[] nums1 = {1,null,3};
		showResults(nums1); // expect true
		
		Integer[] nums2 = {5,1,4,null,null,3,6};
		showResults(nums2); // expect false

		Integer[] nums3 = {2,1,3};
		showResults(nums3); // expect true
		
		Integer[] nums4 = {1};
		showResults(nums4); // expect true

		Integer[] nums5 = {2,1};
		showResults(nums5); // expect true

		Integer[] nums6 = {1,2,3};
		showResults(nums6); // expect false

		Integer[] nums7 = {10,5,15,null,null,6,20};
		showResults(nums7); // expect false

		Integer[] nums8 = {1,1};
		showResults(nums8); // expect false
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);

		boolean rs = isValidBST(root);
		System.out.printf("\nThis is valid BST: %b\n\n", rs);
	}
	
	// Using dfs
	// Time: O(n), space: O(h)
	public static boolean isValidBST(TreeNode root) {
		if(root == null) return true;
		return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private static boolean dfs(TreeNode root, Integer lower, Integer upper) {
		if(root == null) return true;
		else if(Integer.compare(root.val, lower) <= 0 ||
				Integer.compare(root.val, upper) >= 0)
			return false;
		else return dfs(root.left, lower, root.val) &&
					dfs(root.right, root.val, upper);
	}

	public static void printBFS(TreeNode root) {
		Deque<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			int totalNodesAtCurrDepth = queue.size();
			int counter = 0;
			TreeNode tmp =  queue.peekFirst();
			while(counter++ < totalNodesAtCurrDepth) {
				tmp = queue.removeFirst();
				System.out.printf("%d ", tmp.val);
				if(tmp.left != null) queue.addLast(tmp.left);
				if(tmp.right != null) queue.addLast(tmp.right);
			}
			System.out.println();
		}

		System.out.println();
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
