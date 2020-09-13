//leetcode 25.
public class ReverseNodesInKGroup {
	public static void main(String args[]) {
		int[] nums1 = {1,2,3,4,5};
		showResults(nums1, 2); //expect 2 1 4 3 5
		showResults(nums1, 3); //expect 3 2 1 4 5 

		int[] nums2 = {1,2,3,4,5,6,7,8};
		showResults(nums2, 4);
	}

	private static void showResults(int[] nums, int k) {
		System.out.println("----ShowResults----");
		System.out.println("INPUT");
		ListNode l = createLinkedList(nums);
		printLinkedList(l);
		System.out.printf("k = %d\n", k);

		System.out.println("\nOUTPUT");
		ListNode rs = reverseKGroup(l, k);
		if(rs != null) printLinkedList(rs);
		System.out.println();
	}

	//time: O(n), space: O(1)
	public static ListNode reverseKGroup(ListNode head, int k) {
		if(head == null || head.next == null) return head;
		
		head = reverse(head, k);
		return head;
	}

	//time: O(n), space: O(1)
	private static ListNode reverse(ListNode head, int k) {
		if(head == null) return null;
		
		ListNode tmp = head;
		int extra;
		for(extra = 0; extra < k && tmp != null; ++extra)
			tmp = tmp.next;

		//if tmp cannot reach the Node to reverse (extra = k)
		//then return head instead
		if(tmp == null && extra != k) return head;

		ListNode prev = reverse(tmp, k);
		ListNode curr = head;
		ListNode after;
		while(curr != tmp) {
			after = curr.next;
			curr.next = prev;
			prev = curr;
			curr = after;
		}

		return prev; //return prev as a tailNode for previous stack to link between nodes
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

//answer: https://leetcode.com/problems/reverse-nodes-in-k-group/discuss/802842/JAVA-Recursive-0ms-(Faster-than-100)-Solution
