import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

// leetcode 863.
public class AllNodesDistanceKInBinaryTree {
	public static void main(String[] args) {
		Integer[] nums1 = {3,5,1,6,2,0,8,null,null,7,4};
		showResults(nums1, 5, 2); // expect [7,4,1]

		Integer[] nums2 = {1,5,3};
		showResults(nums2, 1, 3); // expect []

		Integer[] nums3 = {1,5,2,12,7,null,11,null,null,1,8,7};
		showResults(nums3, 5, 2); // expect [1,8,2]
	}

	private static void showResults(Integer[] nums, int target, int k) {
		System.out.println("------ShowResults------\n");
		System.out.printf("Target = %d, K = %d\n\n", target, k);
		TreeNode root = createTreeFromArray(nums);
		TreeNode targetNode = findNodeWithValue(root, target);
		printBinaryTree(root);

		List<Integer> rs = distanceK(root, targetNode, k);
		System.out.println("\n" + rs.toString() + "\n");
	}

	// GOOD EXPLANATION
	// https://www.youtube.com/watch?v=nPtARJ2cYrg

	// Time: O(n), space: O(n)
	public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
		if(root == null) return Collections.emptyList();

		Set<TreeNode> visited = new HashSet<>();
		Map<TreeNode,TreeNode> map = new HashMap<>(); // <node - parent node> pair
		constructNodeParent(null, root, map); // convert tree to graph.
		Deque<TreeNode> queue = new LinkedList<>();

		queue.addLast(target);

		int currDepth = 0;
		while(currDepth != k) {
			int size = queue.size();
			while(size-- != 0) {
				TreeNode tmp = queue.removeFirst();
				visited.add(tmp);

				TreeNode parent = map.get(tmp);
				if(parent != null && !visited.contains(parent))
					queue.addLast(parent);
				if(tmp.left != null && !visited.contains(tmp.left))
					queue.addLast(tmp.left);
				if(tmp.right != null && !visited.contains(tmp.right))
					queue.addLast(tmp.right);
			}
			
			currDepth++;
		}
		
		List<Integer> rs = new ArrayList<>();
		while(!queue.isEmpty())
			rs.add(queue.removeFirst().val);

		return rs;
	}

	private static void constructNodeParent(TreeNode parent, TreeNode node, Map<TreeNode, TreeNode> map) {
		if(node == null) return;
		else {
			map.put(node, parent);
			constructNodeParent(node, node.left, map);
			constructNodeParent(node, node.right, map);
		}
	}

	private static TreeNode findNodeWithValue(TreeNode root, int k) {
		if(root == null) return null;
		else if(root.val == k) return root;
		else {
			TreeNode leftFound = findNodeWithValue(root.left, k);
			TreeNode rightFound = findNodeWithValue(root.right, k);

			if(leftFound == null) return rightFound;
			return leftFound;
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

// Constraints:
// - All nodes val are UNIQUE.
