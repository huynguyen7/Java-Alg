//leetcode 82
public class RemoveDuplicatesFromSortedListII {
	public static void main(String args[]) {
		//all input should be sorted arrs.
		int[] nums1 = {1,2,3,3,4,4,5};
		showResults(nums1); //expect 1 2 5
		
		int[] nums2 = {1,1,1,2,3};
		showResults(nums2); //expect 2 3

		int[] nums3 = {1,1,2,2,3,4,5,5};
		showResults(nums3); //expect 1 2 3 4 5
		
		int[] nums4 = {1,1};
		showResults(nums4); //expect empty

		int[] nums5 = {1,1,2};
		showResults(nums5); //expect 2
	}
	

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println("INPUT");
		ListNode l = createLinkedList(nums);
		printLinkedList(l);

		System.out.println("\nOUTPUT");
		ListNode rs = deleteDuplicates(l);
		printLinkedList(rs);
		System.out.println();
	}

	//time: O(n), space: O(1); n is the length of linked list.
	public static ListNode deleteDuplicates(ListNode head) {
		if(head == null || head.next == null) return head;
		
		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		dummy.next = head;
		ListNode curr = dummy, prev = dummy;
		ListNode tmp;
		while(curr != null) {
			tmp = curr.next;
			if(tmp != null && tmp.val != curr.val) {
				prev = curr;
				curr = curr.next;
			} else if(tmp == null) {
				curr.next = tmp;
				curr = tmp;
			} else { 
				while(tmp != null && tmp.val == curr.val) tmp = tmp.next;
				prev.next = tmp;
				curr = tmp;
			}
		}
		
		return dummy.next;
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
