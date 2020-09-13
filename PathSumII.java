import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//leetcode 113
public class PathSumII {
	public static void main(String[] args) {
		Integer[] nums1 = {5,4,8,11,null,13,4,7,2,null,null,null,null,5,1};
		int sum1 = 22;
		showResults(nums1, sum1); //expect {{5,4,11,2},{5,8,4,5}}
	}

	private static void showResults(Integer[] nums, int sum) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);

		System.out.println();
		List<List<Integer>> rs = pathSum(root, sum);
		for(List<Integer> l: rs)
			System.out.println(l.toString());
		System.out.println();
	}
	
	//time: O(n), space: O(h)
	public static List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> rs = new ArrayList<>();
		if(root == null) return rs;

		dfs(root, 0, sum, rs, new ArrayList<Integer>());
		return rs;
	}

	//helper method, dfs
	private static void dfs(TreeNode root, int pathSum, int sum, List<List<Integer>> rs, List<Integer> holder) {
		if(root.left == null && root.right == null) {
			pathSum += root.val;
			if(pathSum != sum) return;
			else {
				holder.add(root.val);
				rs.add(new ArrayList<>(holder));
				holder.remove(holder.size() - 1);
			}
		} else {
			pathSum += root.val;
			holder.add(root.val);
			
			if(root.left != null) dfs(root.left, pathSum, sum, rs, holder);
			if(root.right != null) dfs(root.right, pathSum, sum, rs, holder);
			holder.remove(holder.size() - 1);
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
