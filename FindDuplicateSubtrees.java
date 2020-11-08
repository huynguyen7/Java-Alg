import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

// leetcode 652.
public class FindDuplicateSubtrees {
	public static void main(String[] args) {
		Integer[] nums1 = {1,2,3,4,null,2,4,null,null,null,null,4};
		showResults(nums1); // expect {{2,4},{4}}

		Integer[] nums2 = {2,1,1};
		showResults(nums2); // expect {{1}}
		
		Integer[] nums3 = {2,2,2,3,null,3};
		showResults(nums3); // expect {{2,3},{3}}
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		System.out.println("\nSubtrees that have dups:");
		
		List<TreeNode> rs = findDuplicateSubtrees(root);
		rs.forEach(tree -> {
			printBinaryTree(tree);
			System.out.println();
		});
	}

	// Trick: Concatenate String to keep check
	// subtrees nodes with preorder traversal.

	// Time: O(n), space: O(n)
	public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		if(root == null) return Collections.emptyList();

		Map<String, Integer> map = new HashMap<>(); // <subtree string - num occurrences>
		List<TreeNode> rs = new ArrayList<>();
		dfs(root, rs, map);

		return rs;
	}

	private static String dfs(TreeNode root, List<TreeNode> rs, Map<String, Integer> map) {
		if(root == null) return "";
		else {
			String currTree = new StringBuilder()
					.append(root.val).append(':')
					.append(dfs(root.left, rs, map)).append(',')
					.append(dfs(root.right, rs, map)).append(';')
					.toString();
		
			int numOccurrences = map.getOrDefault(currTree, 0);
			if(numOccurrences == 1) rs.add(root);
			map.put(currTree, numOccurrences + 1);

			return currTree;
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

		@Override
		public String toString() {
			return String.format("%d", val);
		}
	}
}
