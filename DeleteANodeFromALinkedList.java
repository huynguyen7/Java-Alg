//element-prog 8.6, 8.7
public class DeleteANodeFromALinkedList {
	public static void main(String args[]) {
		int[] nums1 = {1,2,3,4};
		showResults(nums1, 3); //expect 1 2 4
	}
	

	private static void showResults(int[] nums, int at) {
		System.out.println("----ShowResults----");
		System.out.println("INPUT");
		ListNode l = createLinkedList(nums);
		printLinkedList(l);

		System.out.println("\nOUTPUT");
		deleteFromList(l, at);
		printLinkedList(l);
		System.out.println();
	}

	//time: O(n), space: O(1); n is the length of linked list.
	public static void deleteFromList(ListNode head, int at) {
		if(head == null || at <= 0) return;
		
		int count = 1;
		ListNode curr = head, prev = curr;
		while(count++ != at && curr != null) {
			prev = curr;
			curr = curr.next;
		}
		
		prev.next = curr.next;
	}

	private static ListNode addTwoLinkedList(ListNode headA, ListNode headB) {
		if(headB == null) return headA;
		ListNode dummy = new ListNode();
		dummy.next = headA;
		
		ListNode curr;
		for(curr = headA; curr.next != null; curr = curr.next);
		curr.next = headB;

		return dummy.next;
	}

	private static void printLinkedList(ListNode head) {
		if(head == null) return;
		for(ListNode curr = head; curr != null; curr = curr.next)
			System.out.printf("%d ", curr.val);
		System.out.println();
	}

	private static ListNode createLinkedList(int[] nums) {
		if(nums == null || nums.length == 0) return null;
		ListNode curr = new ListNode();
		ListNode dummy = curr;
		for(int i = 0; i < nums.length; ++i) {
			curr.next = new ListNode(nums[i]);
			curr = curr.next;
		}

		return dummy.next;
	}

	static class ListNode {
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
	}
}
