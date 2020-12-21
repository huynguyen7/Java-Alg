import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

//leetcode 109.
public class ConvertSortedListToBST {
	public static void main(String[] args) {
		int[] nums1 = {-10,-3,0,5,9};
		showResults(nums1); // expect [0,-3,9,-10,null,5]

		int[] nums2 = {};
		showResults(nums2); // expect EMPTY

		int[] nums3 = {0};
		showResults(nums3); // expect [0]

		int[] nums4 = {1,3};
		showResults(nums4); // expect [3,1]
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		ListNode head = toLinkedList(nums);
		printLinkedList(head);
		System.out.println("\nOUTPUT: ");
		TreeNode rs = sortedListToBST(head);
		printBinaryTree(rs);
		System.out.println();
	}

	// Time: O(n), space: O(n)
	public static TreeNode sortedListToBST(ListNode head) {
		if(head == null) return null;

		List<ListNode> list = toList(head); // time: O(n), space: O(n)

		int left = 0, right = list.size() - 1;
		int mid = (left + right) / 2;

		return dfs(list, left, right);
	}

	private static TreeNode dfs(List<ListNode> list, int left, int right) {
		if(left > right) return null;
		else {
			int mid = (left + right) / 2;
			TreeNode node = new TreeNode(list.get(mid).val);
			node.left = dfs(list, left, mid - 1);
			node.right = dfs(list, mid + 1, right);
			return node;
		}
	}

	// Time: O(n), space: O(n)
	private static List<ListNode> toList(ListNode head) {
		List<ListNode> rs = new ArrayList<>();
		ListNode x = head;

		while(x != null) {
			rs.add(x);
			x = x.next;
		}

		return rs;
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

	private static class TreeNode {
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

	private static ListNode toLinkedList(int[] arr) {
		ListNode dummy = new ListNode();
		ListNode curr = dummy;

		for(int i = 0; i < arr.length; ++i) {
			curr.next = new ListNode(arr[i]);
			curr = curr.next;
		}

		return dummy.next;
	}

	private static void printLinkedList(ListNode head) {
		ListNode x = head;
		while(x != null) {
			System.out.printf("%s->", x);
			x = x.next;
		}
		System.out.print("NULL\n");
	}

	private static class ListNode {
		int val;
		ListNode next;

		ListNode() {}
		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}

		@Override
		public String toString() {
			return String.format("%d", val);
		}
	}
}
