//leetcode 24
public class SwapNodesInPairs {
	public static void main(String args[]) {
		int[] nums1 = {1,2,3,4}; //expect 2 1 4 3
		int[] nums2 = {1,2,3}; //expect 2 1 3
		int[] nums3 = {1,2,3,4,5};
		
		showResults(nums1);
		showResults(nums2);
		showResults(nums3);
	}
	
	//time: O(n), space: O(1); n is the length of linked list.
	public static ListNode swapPairs(ListNode head) {
		if(head == null || head.next == null) return head;
		
		ListNode first, prevFirst, prev, curr, after;
		boolean firstIteration = true;

		prevFirst = head;
		curr = head;
		first = head;
		while(curr != null) {
			prev = null;
			first = curr;
			for(int i = 1; i <= 2 && curr != null; ++i) {
				after = curr.next;
				curr.next = prev;
				prev = curr;
				curr = after;
			}
			if(firstIteration) {
				firstIteration = false;
				head = prev;
			} else prevFirst.next = prev;
			
			prevFirst = first;
		}

		return head;
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println("INPUT");
		ListNode l = createLinkedList(nums);
		printLinkedList(l);

		System.out.println("\nOUTPUT");
		ListNode rs = swapPairs(l);
		if(rs != null) printLinkedList(rs);
		System.out.println();
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
