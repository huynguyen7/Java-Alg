import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// element-prog 15.3
public class KthLargestElementInBST {
	public static void main(String[] args) {
		Integer[] nums1 = {2,1,3};
		showResults(nums1, 1); // expect 3
		showResults(nums1, 3); // expect 1
		
		Integer[] nums2 = {10,5,15,null,null,13,20};
		showResults(nums2, 4); // expect 10
		showResults(nums2, 3); // expect 13
		showResults(nums2, 2); // expect 15
	}

	// CONSTRAINTS:
	// k is always valid (k <= number of nodes)

	private static void showResults(Integer[] nums, int k) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
		TreeNode rs = kthSmallest(root, k);
		if(rs != null) System.out.printf("\nk: %d, rs: %d\n\n", k, rs.val);
		else System.out.printf("k : %d, rs: null\n\n", k);
	}
	
	// Time: O(h + k), space: O(k)
	public static TreeNode kthSmallest(TreeNode root, int k) {
		List<TreeNode> kLargestElements = new ArrayList<>();
		dfs(root, k, kLargestElements);
		return kLargestElements.get(k-1);
	}

	private static void dfs(TreeNode root, int k, List<TreeNode> kLargestElements) {
		// NOTES: Using reversed inorder traversal (Right-Node-Left)
		// to get the k'th max elements.
		if(root != null && kLargestElements.size() < k) {
			dfs(root.right, k, kLargestElements);
			if(kLargestElements.size() < k) {
				kLargestElements.add(root);
				dfs(root.left, k, kLargestElements);
			}
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
