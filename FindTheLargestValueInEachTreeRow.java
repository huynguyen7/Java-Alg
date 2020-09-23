import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Deque;

//leetcode 101, element-prog 10.2
public class FindTheLargestValueInEachTreeRow {
	public static void main(String[] args) {
		Integer[] nums1 = {1,2,2,3,4,4,3};
		showResults(nums1); //expect {1,2,4}

		Integer[] nums2 = {1,2,2,null,3,null,3};
		showResults(nums2); //expect {1,2,3}
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);

		List<Integer> rs = largestValues(root);
		System.out.println("\n" + rs.toString() + "\n");
	}
	
	// using bfs
	//time: O(n), space: O(2^d)
	// n is total of nodes
	// 2^h is the number of nodes at dephth d
	public static List<Integer> largestValues(TreeNode root) {
		List<Integer> rs = new ArrayList<>();
		if(root == null) return rs;

		Deque<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		
		while(!queue.isEmpty()) { // each loop is going through each depth level
			int maxAtCurrDepth = Integer.MIN_VALUE;
			int totalNodesAtCurrDepth = queue.size();
			int counter = 0;
			
			while(counter++ < totalNodesAtCurrDepth) {
				TreeNode tmp = queue.removeFirst();
				if(tmp.left != null) queue.addLast(tmp.left);
				if(tmp.right != null) queue.addLast(tmp.right);

				maxAtCurrDepth = Math.max(maxAtCurrDepth, tmp.val);
			}

			rs.add(maxAtCurrDepth);
		}

		return rs;
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
