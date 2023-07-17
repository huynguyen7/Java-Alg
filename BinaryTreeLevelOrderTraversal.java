import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Elements of Programming 9.7,
 * leetcode 102.
 */
public class BinaryTreeLevelOrderTraversal {
	public static void main(String[] args) {
		Integer[] nums1 = {1,2,3,4,5,6,7};
		showResults(nums1);

		Integer[] nums2 =  {3,9,20,null,null,15,7};
		showResults(nums2);
	}

	//time: O(n), space: O(m); n is total nodes, m is maximum depth
	//of the tree.
	public static List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> rs = new ArrayList<>();
        if(root == null) return rs;
		Deque<TreeNode> queue = new LinkedList<>();

		bfs(rs, queue, root); //using breadth first search
		
		return rs;
	}

	private static void bfs(List<List<Integer>> rs, Deque<TreeNode> queue, TreeNode root) {	
		queue.addLast(root); //init queue with root node
        List<Integer> holder;
        
		while(!queue.isEmpty()) {
			int currentLevelSize = queue.size();
			holder = new ArrayList<>();

			for(int i = 0; i < currentLevelSize; ++i) {
				TreeNode curr = queue.removeFirst();
				holder.add(curr.val);
				if(curr.left != null) queue.addLast(curr.left);
				if(curr.right != null) queue.addLast(curr.right);
			}

			rs.add(holder);
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

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);

		System.out.println("OUTPUT");
		List<List<Integer>> rs = levelOrder(root);
		if(rs == null) {
			System.out.println("NULL\n");
			return;
		}
		
		for(List<Integer> l: rs)
			System.out.println(l.toString());
		System.out.println();
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
