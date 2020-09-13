import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

//leetcode 501
public class FindModeInBinarySearchTree {
	public static void main(String[] args) {
		Integer[] nums1 = {1,1,2,2,2,1};
		showResults(nums1);
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);

		int[] rs = findMode(root);
		if(rs != null) System.out.println(Arrays.toString(rs));
	}
	
	//time: O(n), space: O(n)
	public static int[] findMode(TreeNode root) {
		if(root == null) return new int[0];
		Map<Integer, Integer> map = new HashMap<>();
		dfs(root, map);
		
		int maxFreq = Integer.MIN_VALUE;
		int count = 0;
		for(int val: map.values()) {
			if(maxFreq < val)
				maxFreq = val;
		}
		
		List<Integer> rs = new ArrayList<>();
		for(int key: map.keySet()) {
			if(map.get(key) == maxFreq)
				rs.add(key);
		}

		int[] finalRs = new int[rs.size()];
		for(int i = 0; i < finalRs.length; ++i)
			finalRs[i] = rs.get(i);
		
		return finalRs;
	}

	//helper method, dfs
	private static void dfs(TreeNode root, Map<Integer, Integer> map) {
		if(root == null) return;
		else {
			map.put(root.val, map.getOrDefault(root.val, 0) + 1);
			dfs(root.left, map);
			dfs(root.right, map);
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
