import java.util.Arrays;

// leetcode 1669.
public class MergeInBetweenLinkedLists {
	public static void main(String[] args) {
		int[] list11 = {0,1,2,3,4,5};
		int a1 = 3, b1 = 4;
		int[] list12 = {1000000,1000001,1000002};
		showResults(list11, a1, b1, list12); // expect [0,1,2,1000000,1000001,1000002,5]

		int[] list21 = {0,1,2,3,4,5,6};
		int a2 = 2, b2 = 5;
		int[] list22 = {1000000,1000001,1000002,1000003,1000004};
		showResults(list21, a2, b2, list22); // expect [0,1,1000000,1000001,1000002,1000003,1000004,6]
	}

	private static void showResults(int[] nums1, int a, int b, int[] nums2) {
		System.out.println("----ShowResults----");
		System.out.printf("a = %d, b = %d\n", a, b);
		ListNode head1 = toLinkedList(nums1);
		printLinkedList(head1);

		ListNode head2 = toLinkedList(nums2);
		printLinkedList(head2);
		
		ListNode rs = mergeInBetween(head1, a, b, head2);
		printLinkedList(rs);
	}

	// Constraints:
	// 1 <= a <= b < list1.length - 1
	// 1 <= list2.length

	// Time: O(n + m), space: O(1)
	public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
		if(list1 == null || list2 == null) return null;

		ListNode tail2 = getTail(list2);

		ListNode x = list1;
		int count = 1;
		for( ; count < a; ++count)
			x = x.next;

		ListNode y = x;
		count = a;
		for(; count <= b; ++count)
			y = y.next;

		x.next = list2;
		tail2.next = y.next;

		return list1;
	}

	private static ListNode getTail(ListNode list) {
		ListNode x = list;
		for( ; x.next != null; x = x.next);
		return x;
	}

	private static ListNode toLinkedList(int[] nums) {
		ListNode dummy = new ListNode();
		ListNode curr = dummy;
		
		for(int i = 0; i < nums.length; ++i) {
			curr.next = new ListNode(nums[i]);
			curr = curr.next;
		}

		return dummy.next;
	}

	private static void printLinkedList(ListNode head) {
		for(ListNode x = head; x != null; x = x.next)
			System.out.printf("%s->", x);
		System.out.println("NULL\n");
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

		@Override
		public String toString() {
			return String.format("%d", val);
		}
	}
}
