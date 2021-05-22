import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

// leetcode 979.
public class DistributeCoinsInBinaryTree {
	public static void main(String[] args) {
		Integer[] nums1 = {3,0,0};
		showResults(nums1); // expect 2

		Integer[] nums2 = {0,3,0};
		showResults(nums2); // expect 3

        Integer[] nums3 = {1,0,2};
        showResults(nums3); // expect 2

        Integer[] nums4 = {1,0,0,null,3};
        showResults(nums4); // expect 4
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
        int rs = distributeCoins(root);
        System.out.printf("\nOUTPUT = %d\n\n", rs);
	}

    /**
     * Constraints:
     * - The number of nodes in the tree is n.
     * - 1 <= n <= 100
     * - 0 <= Node.val <= n
     * - The sum of all Node.val is n.
     */

    private static int rs;

    // Time: O(n), space: O(h)
    public static int distributeCoins(TreeNode root) {
        rs = 0;
        dfs(root);
        return rs;
    }

    private static int dfs(TreeNode root) {
        if(root == null) return 0;
        else {
            int left = dfs(root.left);
            int right = dfs(root.right);
            rs += Math.abs(left) + Math.abs(right);
            return root.val+left+right-1;
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
