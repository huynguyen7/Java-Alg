//element-prog 8.12, leetcode 86.
public class PartitionList {
	public static void main(String args[]) {
		int[] nums1 = {1,4,3,2,5,2};
		int x1 = 3;
		showResults(nums1, x1); //expect 1 2 2 4 3 5
	}
	

	private static void showResults(int[] nums, int x) {
		System.out.println("----ShowResults----");
		System.out.println("INPUT");
		ListNode l = createLinkedList(nums);
		printLinkedList(l);

		System.out.println("\nOUTPUT");
		ListNode rs = partition(l, x);
		if(l != null) printLinkedList(l);
		System.out.println();
	}

	//time: O(n), space: O(1); n is the length of linked list.
	public static ListNode partition(ListNode head, int x) {
		if(head == null || head.next == null) return head;
        
		ListNode smallerStart, largerStart, smallerEnd, largerEnd;
		smallerEnd = new ListNode();
		largerEnd = new ListNode();
		smallerStart = smallerEnd;
		largerStart = largerEnd;

		for(ListNode curr = head; curr != null; curr = curr.next) {
			if(curr.val < x) {
				smallerEnd.next = curr;
				smallerEnd = smallerEnd.next;
			} else {
				largerEnd.next = curr;
				largerEnd = largerEnd.next;
			}
		}
		smallerEnd.next = largerStart.next;
		largerEnd.next = null;
        
		return smallerStart.next;
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
