/**
 * Elements of Programming Interview 8.10
 * leetcode 328.
 */
public class OddEvenList {
	public static void main(String args[]) {
		int[] nums1 = {1,2,3,4,5};
		showResults(nums1); //expect 1 3 5 2 4
	}
	

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println("INPUT");
		ListNode l = createLinkedList(nums);
		printLinkedList(l);

		System.out.println("\nOUTPUT");
		ListNode rs = oddEvenList(l);
		if(l != null) printLinkedList(l);
		System.out.println();
	}

	//time: O(n), space: O(1); n is the length of linked list.
	public static ListNode oddEvenList(ListNode head) {
		if(head == null || head.next == null) return head;

		int count = 1;
		ListNode oddStart, oddEnd, evenStart, evenEnd;
		oddEnd = new ListNode();
		evenEnd = new ListNode();
		oddStart = oddEnd;
		evenStart = evenEnd;
		
		ListNode curr = head;
		while(curr != null) {
			if(count % 2 != 0) { //odd
				oddEnd.next = curr;
				curr = curr.next;
				oddEnd = oddEnd.next;
			} else { //even
				evenEnd.next = curr;
				curr = curr.next;
				evenEnd = evenEnd.next;
			}
			count++;
		}
		oddEnd.next = evenStart.next;
		evenEnd.next = null;
		
		return oddStart.next;
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
			System.out.printf("%d->", curr.val);
		System.out.print("NULL");
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
