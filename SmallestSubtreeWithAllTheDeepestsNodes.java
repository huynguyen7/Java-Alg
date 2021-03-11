import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


// leetcode 865.
public class SmallestSubtreeWithAllTheDeepestsNodes {
	public static void main(String[] args) {
		Integer[] nums1 = {3,5,1,6,2,0,8,null,null,7,4};
		showResults(nums1); // expect [2,7,4]

		Integer[] nums2 = {1};
		showResults(nums2); // expect [1]

        Integer[] nums3 = {0,1,3,null,2};
		showResults(nums3); // expect [2]

        Integer[] nums4 = {0,1,null,3,2,null,null,null,5,4};
        showResults(nums4); // expect [1,3,2,6,null,5,4]
	}

	private static void showResults(Integer[] nums) {
		System.out.println("\t----ShowResults----\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
        System.out.println();
		TreeNode rs = subtreeWithAllDeepest(root);
        printBinaryTree(rs);
        System.out.println();
	}

    private static Map<TreeNode, Integer> mapDepth = new HashMap<>();

    // Time: O(n), space: O(n)
    public static TreeNode subtreeWithAllDeepest(TreeNode root) {
        if(root == null) return null;

        Map<TreeNode, Integer> mapDepth = new HashMap<>();

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        if(leftDepth == rightDepth) return root;
        else if(leftDepth > rightDepth) return subtreeWithAllDeepest(root.left);
        else return subtreeWithAllDeepest(root.right);
    }

    private static int maxDepth(TreeNode root) {
        if(root == null) return 0;
        else if(mapDepth.containsKey(root)) return mapDepth.get(root);
        else {
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);
            int depth = Math.max(leftDepth, rightDepth) + 1;

            mapDepth.put(root, depth);
            return depth;
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
