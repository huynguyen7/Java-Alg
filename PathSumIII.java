import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//leetcode 437.
public class PathSumIII {
	public static void main(String[] args) {
		Integer[] nums1 = {10,5,-3,3,2,null,11,3,-2,null,1};
		int sum1 = 8;
		showResults(nums1, sum1); //expect 3
	}

	private static void showResults(Integer[] nums, int sum) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
		System.out.printf("\nNumber of path to get sum of %d: %d\n",
						sum, pathSum(root, sum));
	}

	private static Map<Integer, Integer> map;
	
	//time: O(n), space: O(n)
	public static int pathSum(TreeNode root, int sum) {
		map = new HashMap<>();
		map.put(0, 1); //if node with value equal to target, that can be added to count
		return pathSumHelper(root, sum, 0);
	}

	private static int pathSumHelper(TreeNode root,  int sum, int currSum) {
		if(root == null) return 0;
		else {
			int count = 0;
			currSum += root.val;

			if(map.containsKey(currSum - sum)) count += map.get(currSum - sum);			
			map.put(currSum, map.getOrDefault(currSum, 0) + 1);

			count += pathSumHelper(root.left, sum, currSum);
			count += pathSumHelper(root.right, sum, currSum);
			
			map.put(currSum, map.get(currSum) - 1);
			System.out.println(count);
			return count;
		}
	}
	
	private static int rs;

	//naive approach
	//time: O(n^2), space: O(h)
	public static int pathSum2(TreeNode root, int sum) {
		rs = 0;
		helper(root, sum);
		return rs;
	}

	private static void helper(TreeNode root, int sum) {
		if(root == null) return;
		else {
			rs += numPathSumFromRoot(root, sum);
			helper(root.left, sum);
			helper(root.right, sum);
		}
	}

	private static int countPath;	

	private static int numPathSumFromRoot(TreeNode root, int sum) {
		countPath = 0;
		dfs(root, 0, sum);
		return countPath;
	}

	//helper method, dfs
	private static void dfs(TreeNode root, int pathSum, int sum) {
		if(root == null) return;
		else {
			pathSum += root.val;
			if(pathSum == sum) countPath++;
			dfs(root.left, pathSum, sum);
			dfs(root.right, pathSum, sum);
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
