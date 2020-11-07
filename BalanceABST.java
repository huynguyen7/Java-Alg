import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// leetcode 1382.
public class BalanceABST {
	public static void main(String[] args) {
		Integer[] nums1 = {1,null,2,null,null,null,3};
		showResults(nums1); // expect {2,1,3}
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
		System.out.println();
		TreeNode rs = balanceBST(root);
		printBinaryTree(rs);
		System.out.println();
	}

	// Time: O(n), space: O(n)
	public static TreeNode balanceBST(TreeNode root) {
		if(root == null) return null;

		List<Integer> holder = new ArrayList<>();
		getInorderBST(root, holder);
		return constructBSTFromList(holder, 0, holder.size() - 1);
	}

	private static TreeNode constructBSTFromList(List<Integer> holder, int lo, int hi) {
		if(lo > hi) return null;
		else {
			int mid = (lo + hi) / 2;
			TreeNode root = new TreeNode(holder.get(mid));
			root.left = constructBSTFromList(holder, lo, mid - 1);
			root.right = constructBSTFromList(holder, mid + 1, hi);
			return root;
		}
	}

	private static void getInorderBST(TreeNode root, List<Integer> holder) {
		if(root == null) return;
		else {
			getInorderBST(root.left, holder);
			holder.add(root.val);
			getInorderBST(root.right, holder);
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
