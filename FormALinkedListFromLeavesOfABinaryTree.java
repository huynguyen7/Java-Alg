import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//element-prog 10.14
public class FormALinkedListFromLeavesOfABinaryTree {
	public static void main(String[] args) {
		Integer[] nums1 = {1,2,3,4,5};
		showResults(nums1); //expect 4->5

		Integer[] nums2 = {1,2,3};
		showResults(nums2); //expect 2->3
		
		Integer[] nums3 = {1,2,3,null,5,null,6};
		showResults(nums3); //expect 5->6
	}

	private static void showResults(Integer[] nums) {
		System.out.println("------ShowResults------\n");
		TreeNode root = createTreeFromArray(nums);
		printBinaryTree(root);

		System.out.println();
		ListNode rs = formLinkedListFromLeaves(root);
		printLinkedList(rs);
	}
	
	private static ListNode dummy;
	private static ListNode curr;
	
	//time: O(n), space: O(h)
	public static ListNode formLinkedListFromLeaves(TreeNode root) {
		dummy = new ListNode();
		curr = dummy;
		dfs(root);
		return dummy.next;
	}

	private static void dfs(TreeNode root) {
		if(root.left == null && root.right == null) {
			curr.next = new ListNode(root.val);
			curr = curr.next;
			return;
		}
		else {
			if(root.left != null) dfs(root.left);
			if(root.right != null) dfs(root.right);
		}
	}

	private static void printLinkedList(ListNode head) {
		if(head == null) {
			System.out.println("NULL");
			return;
		} else {
			ListNode curr = head;
			for( ; curr != null; curr = curr.next)
				System.out.printf("%d->", curr.val);
			System.out.print("NULL\n");
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

	static class ListNode {
		int val;
		ListNode next;
		
		ListNode() {}
		ListNode(int val) {this.val = val;}
		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}
}
