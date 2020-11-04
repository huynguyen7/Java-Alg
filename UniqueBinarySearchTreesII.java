import java.util.Iterator;
import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;

// leetcode 95.
public class UniqueBinarySearchTreesII {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		showResults(n);
	}

	private static void showResults(int n) {
		System.out.println("------ShowResults------\n");
		System.out.printf("n = %d\n\n", n);
		List<TreeNode> rs = generateTrees(n);
		for(TreeNode root: rs) {
			printBinaryTree(root);
			System.out.println();
		}
		System.out.println();
	}

    // Time: O(n*2^n), space: O(2^n)
    public static List<TreeNode> generateTrees(int n) {
        if(n == 0) return Collections.emptyList();
        return dfs(1, n);
    }
    
    private static List<TreeNode> dfs(int from, int to) {
        List<TreeNode> rs = new ArrayList<>();
        if(from > to) {
            rs.add(null);
            return rs;
        } else {
            for(int i = from; i <= to; ++i) {
                List<TreeNode> leftSubtrees = dfs(from, i - 1);
                List<TreeNode> rightSubtrees = dfs(i + 1, to);
                
                for(TreeNode leftSubtree: leftSubtrees) {
                    for(TreeNode rightSubtree: rightSubtrees)
                        rs.add(new TreeNode(i, leftSubtree, rightSubtree));
                }
            }
            
            return rs;
    }

	// Time: O(n), space: O(n)
	private static TreeNode cloneTree(TreeNode root) {
		if(root == null) return null;
		else {
			TreeNode cloneRoot = new TreeNode(root.val);
			cloneRoot.left = cloneTree(root.left);
			cloneRoot.right = cloneTree(root.right);
			
			return cloneRoot;
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
