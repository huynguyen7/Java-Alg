import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

// leetcode 103.
public class BinaryTreeZigzagLevelOrderTraversal {
	public static void main(String[] args) {
		Integer[] nums1 = {3,9,20,null,null,15,7};
		showResults(nums1); // expect {{3},{20,9},{15,7}}
		
		Integer[] nums2 = {1,2,3,4,null,null,5};
		showResults(nums2); // expect {{1},{3,2},{4,5}}
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		System.out.println();
		
		List<List<Integer>> rs = zigzagLevelOrder(root);
		for(List<Integer> list: rs)
			System.out.println(list.toString());
		System.out.println();
	}

	// Using BFS.
	// Time: O(n), space: O(n)
	public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		if(root == null) return Collections.emptyList();

		boolean fromLeft = false;
		List<List<Integer>> rs = new ArrayList<>();
		Deque<TreeNode> queue = new LinkedList<>();
		queue.addLast(root);
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> holder = new ArrayList<>();
			while(size-- != 0) {
				TreeNode tmp = queue.removeFirst();
				holder.add(tmp.val);
				if(tmp.left != null) queue.addLast(tmp.left);
				if(tmp.right != null) queue.addLast(tmp.right);
			}
			
			if(fromLeft) Collections.reverse(holder);
			rs.add(holder);
			fromLeft = !fromLeft;
		}

		return rs;
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
