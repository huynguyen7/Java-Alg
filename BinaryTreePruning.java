import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// leetcode 814.
public class BinaryTreePruning {
	public static void main(String[] args) {
		Integer[] nums1 = {1,null,0,null,null,0,1};
		showResults(nums1); // expect [1,null,0,null,null,null,1]

		Integer[] nums2 = {1,0,1,0,0,0,1};
		showResults(nums2); // expect [1,null,1,null,null,null,1]
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
	    System.out.println();
        TreeNode rs = pruneTree(root);
        if(rs != null) {
            printBinaryTree(rs);
            System.out.println();
        }
	}

    // Time: O(n), space: O(n)
    public static TreeNode pruneTree(TreeNode root) {
        if(root == null) return root;
        else if(!containingOnes(root)) return null;
        else return root;
    }

    private static boolean containingOnes(TreeNode root) {
        if(root == null) return false;
        else {
            boolean rootHasOne = root.val == 1;
            boolean leftCheck = containingOnes(root.left);
            boolean rightCheck = containingOnes(root.right);

            if(!leftCheck && root != null) root.left = null;
            if(!rightCheck && root != null) root.right = null;
            if(!leftCheck && !rightCheck && root != null) root = null;

            return rootHasOne || leftCheck || rightCheck;
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
