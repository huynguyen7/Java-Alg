import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//leetcode 257
public class BinaryTreePaths {
	public static void main(String[] args) {
		Integer[] nums1 = {1,2,3,null,5};
		showResults(nums1); //expect {"1->2->5", "1->3"}
		
		Integer[] nums2 = {1,3,2};
		showResults(nums2); //expect {"1->3", "1->2"}

		Integer[] nums3 = {1};
		showResults(nums3); //expect {"1"}
		
		Integer[] nums4 = {1,2,null,3};
		showResults(nums4);
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);

		System.out.println("\nOUTPUT");
		List<String> rs = binaryTreePaths(root);
		System.out.println(rs.toString());
	}
	
	//return all root-to-leaf paths.
	//time: O(), space: O()
	public static List<String> binaryTreePaths(TreeNode root) {
		List<String> rs = new ArrayList<>();
		if(root == null) return rs;
		if(root.left == null && root.right == null) {
			rs.add(String.valueOf(root.val));
			return rs;
		}

		dfs(root, rs, new StringBuilder());
		return rs;
	}

	private static void dfs(TreeNode root, List<String> rs, StringBuilder holder) {
		if(root.left == null && root.right == null) rs.add(holder.substring(2) + "->" + root.val);
        else {
			String value = "->" + root.val;
			holder.append(value);
			if(root.left != null) dfs(root.left, rs, holder);
			if(root.right != null) dfs(root.right, rs, holder);
			holder.delete(holder.length() - value.length(), holder.length());
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
