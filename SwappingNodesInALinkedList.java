// leetcode 1721.
public class SwappingNodesInALinkedList {
	public static void main(String[] args) {
		int[] nums1 = {7,9,6,6,7,8,3,0,9,5};
		int k1 = 5;
		showResults(nums1, k1); // expect {7,9,6,6,8,7,3,0,9,5}

		int[] nums2 = {1};
		int k2 = 1;
		showResults(nums2, k2); // expect [1]

		int[] nums3 = {1,2};
		int k3 = 1;
		showResults(nums3, k3); // expect [2,1]

		int[] nums4 = {1,2,3};
		int k4 = 2;
		showResults(nums4, k4); // expect [1,2,3]
	}

	private static void showResults(int[] nums, int k) {
		System.out.println("\t----ShowResults----");
		System.out.printf("k = %d\n", k);
		ListNode head = createLinkedListFromArray(nums);
		printLinkedList(head);
		ListNode rs = swapNodes(head, k);
		printLinkedList(rs);
		System.out.println();
	}

	// Time: O(n), space: O(1)
	public static ListNode swapNodes(ListNode head, int k) {
		ListNode dummy = new ListNode(0, head);

		int length = listLength(head);
		if(length <= 1 || k > length) return head;

		final int k2 = length - k + 1;
		if(k2 == k) return head; // Do nothing because both have same indices.


		ListNode prev1 = dummy, curr1 = dummy;
		int count1 = 0;
		while(count1++ != k) {
			prev1 = curr1;
			curr1 = curr1.next;
		}

		ListNode prev2 = dummy, curr2 = dummy;
		int count2 = 0;
		while(count2++ != k2) {
			prev2 = curr2;
			curr2 = curr2.next;
		}

		prev1.next = curr2;
		prev2.next = curr1;
		ListNode tmpNext = curr1.next;
		curr1.next = curr2.next;
		curr2.next = tmpNext;

		return dummy.next;
	}

	// Time: O(n), space: O(1)
	private static int listLength(ListNode head) {
		int length = 0;
		for(ListNode curr = head; curr != null; curr = curr.next)
			length++;
		return length;
	}

	private static ListNode createLinkedListFromArray(int[] nums) {
		ListNode dummy = new ListNode();
		ListNode curr = dummy;

		for(int i = 0; i < nums.length; ++i) {
			curr.next = new ListNode(nums[i]);
			curr = curr.next;
		}

		return dummy.next;
	}

	private static void printLinkedList(ListNode head) {
		if(head == null) return;

		ListNode curr;
		for(curr = head; curr != null; curr = curr.next)
			System.out.printf("%d->", curr.val);
		System.out.print("NULL\n");
	}

	public static class ListNode {
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
