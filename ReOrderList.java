//leetcode 143
public class ReOrderList {
	public static void main(String args[]) {
		//all input should be sorted arrs.
		int[] nums1 = {1,2,3,4};
		showResults(nums1); //expect 1 4 2 3
		
		int[] nums2 = {1,2,3,4,5};
		showResults(nums2); //expect 1 5 2 4 3
	}
	

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println("INPUT");
		ListNode l = createLinkedList(nums);
		printLinkedList(l);

		System.out.println("\nOUTPUT");
		ListNode rs = reorderList(l);
		printLinkedList(rs);
		System.out.println();
	}

	//time: O(n), space: O(1); n is the length of linked list.
	public static ListNode reorderList(ListNode head) {
		if(head == null || head.next == null) return head;
		
		ListNode dummy = new ListNode();
		
		ListNode tail = head;
		int listLength = 0;
		for( ; tail != null; tail = tail.next) listLength++;
		
		ListNode mid = head;
		
		for(int i = 0; i < listLength/2; ++i)
			mid = mid.next;
		ListNode reverseMid = reverseLinkedList(mid);
		ListNode curr = dummy;

		ListNode p1 = reverseMid;
		ListNode p2 = head;
		
		int count = 0;
		while(p2 != null && p1 != null) {
			if(p1 == p2) {
				curr.next = p1;
				curr = curr.next;
				break;
			} else if(p2 == null) {
				curr.next = p1;
				curr = curr.next;
				p1 = p1.next;
			} else if(p1 == null) {
				curr.next = p2;
				curr = curr.next;
				p2 = p2.next;
			} else if(count % 2 == 0) {
				curr.next = p2;
				curr = curr.next;
				p2 = p2.next;
			} else {
				curr.next = p1;
				curr = curr.next;
				p1 = p1.next;
			}
			count++;
		}
		
		curr.next = null;
		
		return dummy.next;
	}

	private static ListNode reverseLinkedList(ListNode head) {
		if(head == null || head.next == null) return head;
		
		ListNode dummy = new ListNode();
		dummy.next = head;
		
		ListNode prev, curr, after;
		prev = head;
		curr = head;
		
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
