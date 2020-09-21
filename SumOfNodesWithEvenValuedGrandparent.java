import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//leetcode 1315
public class SumOfNodesWithEvenValuedGrandparent {
	public static void main(String[] args) {
		Integer[] nums1 = {6,7,8,2,7,1,3,9,null,1,4,null,null,null,5};
		showResults(nums1); //expect 18
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
		int rs = sumEvenGrandparent(root);
		System.out.printf("\nSum: %d\n\n", rs);
	}
	
	//time: O(n), space: O(h)
	public static int sumEvenGrandparent(TreeNode root) {
		if(root == null) return 0;
		return dfs(null, root);
	}

	private static int dfs(TreeNode parent, TreeNode child) {
		if(child == null) return 0;
		else if(parent != null && parent.val % 2 == 0 && child != null) {
			int leftVal = child.left != null ? child.left.val : 0;
			int rightVal = child.right != null ? child.right.val : 0;
			int sum = leftVal + rightVal;
			
			int leftSum = dfs(child, child.left);
			int rightSum = dfs(child, child.right);
			
			return sum + leftSum + rightSum;
		} else {
			int leftSum = dfs(child, child.left);
			int rightSum = dfs(child, child.right);
			return leftSum + rightSum;
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
