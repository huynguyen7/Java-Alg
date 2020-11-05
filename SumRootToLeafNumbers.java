import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// leetcode 129.
public class SumRootToLeafNumbers {
	public static void main(String[] args) {
		Integer[] nums1 = {1,2,3};
		showResults(nums1); // expect 25

		Integer[] nums2 = {4,9,0,5,1};
		showResults(nums2); // expect 1026
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
		int sum = sumNumbers(root);
		System.out.printf("\nSum = %d\n\n", sum);
	}

	private static int sum;
	
	// Using DFS.
	// Time: O(n), space: O(n)
	public static int sumNumbers(TreeNode root) {
		if(root == null) return 0;
		
		List<Integer> holder = new ArrayList<>();
		holder.add(root.val);
		sum = 0;
		dfs(root, holder);

		return sum;
	}

	private static void dfs(TreeNode root, List<Integer> holder) {
		if(isLeaf(root)) {
			sum += getNumFromList(holder);
		} else {
			if(root.left != null) {
				holder.add(root.left.val);
				dfs(root.left, holder);
				holder.remove(holder.size() - 1);
			}
			if(root.right != null) {
				holder.add(root.right.val);
				dfs(root.right, holder);
				holder.remove(holder.size() - 1);
			}
		}
	}

	private static boolean isLeaf(TreeNode node) {
		if(node == null) return false;
		return node.left == null && node.right == null;
	}

	private static int getNumFromList(List<Integer> holder) {
		int num = 0;
		int base = 1;
		for(int i = holder.size() - 1; i >= 0; --i) {
			num += (holder.get(i) * base);
			base *= 10;
		}

		return num;
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
