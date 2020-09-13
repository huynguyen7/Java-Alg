//leetcode 83, element-prog 8.8
public class RemoveDuplicatesFromSortedListI {
	public static void main(String args[]) {
		//all input should be sorted arrs.
		int[] nums1 = {1,1,2,2,3,3,3,3,4,4};
		showResults(nums1); //expect 1 2 3 4
		
		int[] nums2 = {1,2,2,3,4,4};
		showResults(nums2); //expect 1 2 3 4
	}
	

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println("INPUT");
		ListNode l = createLinkedList(nums);
		printLinkedList(l);

		System.out.println("\nOUTPUT");
		deleteDuplicates(l);
		printLinkedList(l);
		System.out.println();
	}

	//time: O(n), space: O(1); n is the length of linked list.
	public static ListNode deleteDuplicates(ListNode head) {
		if(head == null || head.next == null) return head;
		
		ListNode curr = head;
		ListNode tmp;
		while(curr != null) {
			tmp = curr.next;
			while(tmp != null && tmp.val == curr.val)
				tmp = tmp.next;
			curr.next = tmp;
			curr = tmp;
		}

		return head;
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
