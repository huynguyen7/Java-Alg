import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//leetcode 107
public class BinaryLevelOrderTraversalII {
	public static void main(String[] args) {
		Integer[] nums1 = {3,9,20,null,null,15,7};
		showResults(nums1); //expect {{15,7}, {9,20}, {3}}
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);

		List<List<Integer>> rs = levelOrderBottom(root);
		System.out.println(rs.toString() + "\n");
	}
	
	//return lists of all nodes in the same depth inside a list
	//time: O(n), space: O(h)
	public static List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> rs = new ArrayList<>();
		if(root == null) return rs;
		int maxDepth = maxDepth(root, 0);
		
		for(int i = 0; i < maxDepth; ++i)
			rs.add(new ArrayList<Integer>());
		fillList(root, 0, rs);
		Collections.reverse(rs);
		return rs;
	}

	//helper method, dfs
	private static void fillList(TreeNode root, int currDepth, List<List<Integer>> rs) {
		if(root == null) return;
		else {
            rs.get(currDepth).add(root.val);
			fillList(root.left, currDepth + 1, rs);
			fillList(root.right, currDepth + 1, rs);
		}
	}

	private static int maxDepth(TreeNode root, int currDepth) {
		if(root == null) return 0;
		else {
			int leftMaxDepth = maxDepth(root.left, currDepth) + 1;
			int rightMaxDepth = maxDepth(root.right, currDepth) + 1;
			return Math.max(leftMaxDepth, rightMaxDepth);
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
