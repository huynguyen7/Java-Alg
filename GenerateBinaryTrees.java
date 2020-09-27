import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.ArrayList;

// element-prog 16.8
public class GenerateBinaryTrees {
	public static void main(String[] args) {
		showResults(Integer.parseInt(args[0]));
	}

	private static void showResults(int numNodes) {
		System.out.println("------ShowResults------\n");
		System.out.printf("Number of Nodes: %d\n\n", numNodes);

		List<TreeNode> rs = generateBinaryTrees(numNodes);
		System.out.printf("Total generated trees: %d\n\n", rs.size());
		for(TreeNode root: rs) {
			printBinaryTree(root);
			System.out.println();
		} 
	}

	// Constraints:
	// all treenode's value should be 0.

	// C(n) is Catalan's number
	// n = numNodes
	// Time: O(C(n)), space: O(C(n))
	public static List<TreeNode> generateBinaryTrees(int numNodes) {
		List<TreeNode> rs = new ArrayList<>();
		if(numNodes == 0) rs.add(null); // Empty tree, add as an null.
		
		for(int numLeftTreeNodes = 0; numLeftTreeNodes < numNodes; ++numLeftTreeNodes) {
			int numRightTreeNodes = numNodes - 1 - numLeftTreeNodes;
			List<TreeNode> leftSubtrees = generateBinaryTrees(numLeftTreeNodes);
			List<TreeNode> rightSubtrees = generateBinaryTrees(numRightTreeNodes);

			// Generates all combinations for left and right subtrees.
			for(TreeNode left: leftSubtrees) {
				for(TreeNode right: rightSubtrees)
					rs.add(new TreeNode(0, left, right)); // value 0 as default
			}
		}

		return rs;
	}
	
	//time: O(n), space: O(h)
	public static boolean isSymmetric(TreeNode root) {
		if(root == null) return true;
		if(root.right == null && root.left == null) return true;
		return validateSymmetric(root.left, root.right);
	}

	//helper method, dfs
	private static boolean validateSymmetric(TreeNode left, TreeNode right) {
		if(left == null && right == null) return true;
		if(left == null || right == null) return false;
		else {
			if(left.val != right.val) return false;
			boolean x = validateSymmetric(left.left, right.right);
			boolean y = validateSymmetric(left.right, right.left);
			
			return x && y;
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
