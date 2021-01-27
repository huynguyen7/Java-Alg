import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// leetcode 951.
public class FlipEquivalentBinaryTrees {
	public static void main(String[] args) {
		Integer[] nums1 = {1,2,3,4,5,6,null,null,null,7,8};
        Integer[] nums2 = {1,3,2,null,6,4,5,null,null,null,null,null,null,8,7};
		showResults(nums1, nums2); // expect true

		Integer[] nums3 = {};
		Integer[] nums4 = {};
		showResults(nums3, nums4); // expect true

		Integer[] nums5 = {};
		Integer[] nums6 = {1};
		showResults(nums5, nums6); // expect false

		Integer[] nums7 = {0,null,1};
		Integer[] nums8 = {};
		showResults(nums7, nums8); // expect false

		Integer[] nums9 = {0,null,1};
		Integer[] nums10 = {0,1};
		showResults(nums9, nums10); // expect true
	}

	private static void showResults(Integer[] nums1, Integer[] nums2) {
		System.out.println("------ShowResults------\n");
		TreeNode root1 = createTreeFromArray(nums1);
		TreeNode root2 = createTreeFromArray(nums2);
		printBinaryTree(root1);
		printBinaryTree(root2);
		
        boolean rs = flipEquiv(root1, root2);
        System.out.printf("\nResults: %b\n\n", rs);
	}
	
	// Time: O(n), space: O(n)
    public static boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) return true;
        else if(root1 == null || root2 == null) return false;
        else {
            if(root1.val != root2.val) return false;

            return (flipEquiv(root1.left, root2.right)
                && flipEquiv(root1.right, root2.left))
                || (flipEquiv(root1.left, root2.left)
                && flipEquiv(root1.right, root2.right));

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
