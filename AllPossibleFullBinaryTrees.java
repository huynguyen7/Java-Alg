import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;

// leetcode 894.
public class AllPossibleFullBinaryTrees {
	public static void main(String[] args) {
		final int n = Integer.parseInt(args[0]);
		showResults(n);
	}

	private static void showResults(final int N) {
		System.out.println("------ShowResults------\n");
		System.out.printf("INPUT N = %d\n\n", N);
		
		List<TreeNode> rs = allPossibleFBT(N);
		for(TreeNode root: rs) {
			printBinaryTree(root);
			System.out.println();
		}
		System.out.println();
	}

	// Time: O(2^n), space: O(2^n)
	public static List<TreeNode> allPossibleFBT(int N) {
		if(N <= 0 || N % 2 == 0) return Collections.emptyList();

		List<TreeNode> rs = new ArrayList<>();
		dfs(rs, N);
		return rs;
	}

	private static void dfs(List<TreeNode> rs, int N) {
		if(N == 1) {
			TreeNode node = new TreeNode(0);
			rs.add(node);
		} else {
			for(int i = 1; i < N; i += 2) { // indexing [0, N-1]
				List<TreeNode> leftTrees = allPossibleFBT(i);
				List<TreeNode> rightTrees = allPossibleFBT(N - i - 1);

				for(TreeNode left: leftTrees) {
					for(TreeNode right: rightTrees) {
						TreeNode node = new TreeNode(0);
						node.left = left;
						node.right = right;
						rs.add(node);
					}
				}
			}
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
