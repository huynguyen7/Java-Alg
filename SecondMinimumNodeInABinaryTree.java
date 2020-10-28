import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.PriorityQueue;

// leetcode 671.
public class SecondMinimumNodeInABinaryTree {
	public static void main(String[] args) {
		Integer[] nums1 = {2,2,5,null,null,5,7};
		showResults(nums1); // expect 5

		Integer[] nums2 = {2,2,2};
		showResults(nums2); // expect -1

		Integer[] nums3 = {1};
		showResults(nums3); // expect -1
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);
		
		int rs = findSecondMinimumValue(root);
		System.out.printf("\nSecond Minimum Value: %d\n\n", rs);
	}

	// Constraints:
	// - TreeNode val is non-negative.
	// - TreeNode always have two or zero children node.

	// Time: O(n), space: O(logn)
	public static int findSecondMinimumValue(TreeNode root) {
		if(root == null) return -1;

		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
			(a, b) -> Integer.compare(b, a)
		);

		maxHeap.add(root.val);
		dfs(root, maxHeap);

		if(maxHeap.size() < 2) return -1;
		return maxHeap.peek();
	}

	private static void dfs(TreeNode root, PriorityQueue<Integer> maxHeap) {
		if(root.left == null && root.right == null) return;
		else {
			if(!maxHeap.contains(root.left.val)) {
				maxHeap.add(root.left.val);
				if(maxHeap.size() == 3)
					maxHeap.poll();
			}
			if(!maxHeap.contains(root.right.val)) {
				maxHeap.add(root.right.val);
				if(maxHeap.size() == 3)
					maxHeap.poll();
			}
			
			dfs(root.left, maxHeap);
			dfs(root.right, maxHeap);
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
