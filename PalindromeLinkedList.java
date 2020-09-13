//element-prog 8.11, leetcode 234.
public class PalindromeLinkedList {
	public static void main(String args[]) {
		int[] nums1 = {1,2}; //expect false
		showResults(nums1);
		
		int[] nums2 = {1,2,2,1}; //expect true
		showResults(nums2);

		int[] nums3 = {1,2,3}; //expect false
		showResults(nums3);
	}
	

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println("INPUT");
		ListNode l = createLinkedList(nums);
		printLinkedList(l);

		System.out.println("Palindrome: "+ isPalindrome(l) + "\n");
	}

	//time: O(n), space: O(1); n is the length of linked list.
	public static boolean isPalindrome(ListNode head) {
		if(head == null) return false;
		
		int listLength = 0;
		for(ListNode x = head; x != null; x = x.next) listLength++;
		ListNode mid = head, prev = mid;
		for(int i = 0; i < listLength / 2; ++i) {
			prev = mid;
			mid = mid.next;
		}
		prev.next = null;
		ListNode reverseMid = reverseLinkedList(mid);
		
		while(reverseMid != null && head != null) {
			if(reverseMid.val != head.val) return false;
			reverseMid = reverseMid.next;
			head = head.next;
		}
		
		return true;
	}

	//time: O(n), space: O(1)
	private static ListNode reverseLinkedList(ListNode head) {
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
