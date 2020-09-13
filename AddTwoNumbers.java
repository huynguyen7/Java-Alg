//element-prog 8.13, leetcode 2.
public class AddTwoNumbers {
	public static void main(String args[]) {
		int[] nums1 = {2,4,3}; 
		int[] nums2 = {5,6,4};
		showResults(nums1, nums2); //expect 7 0 8

		int[] nums3 = {9};
		int[] nums4 = {1};
		showResults(nums3, nums4);

		int[] nums5 = {9,9,9};
		int[] nums6 = {1};
		showResults(nums5, nums6);
	}
	

	private static void showResults(int[] nums1, int[] nums2) {
		System.out.println("----ShowResults----");
		System.out.println("INPUT");
		ListNode l1 = createLinkedList(nums1);
		printLinkedList(l1);
		ListNode l2 = createLinkedList(nums2);
		printLinkedList(l2);
		
		System.out.println("\nOUTPUT");
		ListNode rs = addTwoNumbers(reverse(l1), reverse(l2));
		if(rs != null) printLinkedList(reverse(rs));
		System.out.println();
	}

	//only works with positive integers
	//time: O(), space: O(n);
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if(l1 == null && l2 != null) return l2;
		if(l2 == null && l1 != null) return l1;

		ListNode dummy = new ListNode();
		ListNode curr = dummy;
		boolean mem = false;
		
		int val;
		while(l1 != null || l2 != null) {
			if(l1 == null) {
				val = l2.val;
				curr.next = new ListNode(mem ? (val + 1) % 10 : val);
				mem = val >= 10 || (mem && val + 1 >= 10);
				curr = curr.next;
				l2 = l2.next;
			} else if(l2 == null) {
				val = l1.val;
				curr.next = new ListNode(mem ? (val + 1) % 10 : val);
				mem = val >= 10 || (mem && val + 1 >= 10);
				curr = curr.next;
				l1 = l1.next;
			} else {
				val = l1.val + l2.val;
				if(mem) curr.next = new ListNode((val + 1) % 10);
				else curr.next = new ListNode(val % 10);
				mem = val >= 10 || (mem && val + 1 >= 10);
				curr = curr.next;
				l1 = l1.next;
				l2 = l2.next;
			}
		}
		if(mem) {
			curr.next = new ListNode(1);
			curr = curr.next;
		}
		curr.next = null;
		
		return dummy.next;	
	}

	private static ListNode reverse(ListNode head) {
		if(head == null || head.next == null) return head;
		
		ListNode prev, curr, after;
		curr = head;
		prev = curr;
		while(curr != null) {
			after = curr.next;
			curr.next = prev;
			prev = curr;
			curr = after;
		}
		head.next = null;

		return prev;
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
