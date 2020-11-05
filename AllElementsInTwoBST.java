import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;

// leetcode 1305.
public class AllElementsInTwoBST {
	public static void main(String[] args) {
		Integer[] nums1 = {2,1,4};
		Integer[] nums2 = {1,0,3};
		showResults(nums1, nums2); // expect {0,1,1,2,3,4}

		Integer[] nums3 = {0,-10,10};
		Integer[] nums4 = {5,1,7,0,2};
		showResults(nums3, nums4); // expect {-10,0,0,1,2,5,7,10}
		
		Integer[] nums5= {};
		Integer[] nums6= {5,1,7,0,2};
		showResults(nums5, nums6); // expect {0,1,2,5,7}

		Integer[] nums7 = {0,-10,10};
		Integer[] nums8 = {};
		showResults(nums7, nums8); // expect {-10,0,10}

		Integer[] nums9 = {1,null,8};
		Integer[] nums10 = {8,1};
		showResults(nums9, nums10); // expect {1,1,8,8}
	}

	private static void showResults(Integer[] nums1, Integer[] nums2) {
		System.out.println("------ShowResults------\n");
		TreeNode root1 = createTreeFromArray(nums1);
		printBinaryTree(root1);
		System.out.println();
		
		TreeNode root2 = createTreeFromArray(nums2);
		printBinaryTree(root2);
		System.out.println();

		List<Integer> rs = getAllElements(root1, root2);
		System.out.println(rs.toString() + "\n");
	}

	// Time: O((n + m) * log(n + m)), space: O(n + m)
	public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
		List<Integer> holder1 = new ArrayList<>();
		List<Integer> holder2 = new ArrayList<>();
		dfs(root1, holder1);
		dfs(root2, holder2);

		holder1.addAll(holder2);
		Collections.sort(holder1);
		return holder1;
	}

	private static void dfs(TreeNode root, List<Integer> holder) {
		if(root == null) return;
		else {
			holder.add(root.val);
			dfs(root.left, holder);
			dfs(root.right, holder);
		}
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
