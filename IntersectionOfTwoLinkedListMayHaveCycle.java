import java.util.Arrays;
import java.util.Stack;

//element-prog 8.4.
public class IntersectionOfTwoLinkedListMayHaveCycle {
	public static void main(String args[]) {
		int[] l1 = {2,1,5};  
		int[] l2 = {7,3};
		int[] seg = {4,8,9};

		showResults(l1, l2, seg, 2); //make linked list l2 have starting cycle node at 2nd node.
	}
	
	//time: O(n + m), space: O(1); n is length of l1, m is length of l2 
	public static ListNode findIntersectionNode(ListNode l1, ListNode l2) {
		if(l1 == null || l1.next == null || l2 == null || l2.next == null) return null;
		
		ListNode slow = l1, fast = l1;
		while(fast.next != null && fast != null) {
			fast = fast.next.next;
			slow = slow.next;
			if(slow == fast && slow != null && fast != null) break;
		}
		
		//if(fast == null || fast.next == null) there is no cycle
			
		ListNode cycleNode1 = l1;
		//the distance between slow or fast node to cycleNode is equal to
		//the distance from headNode to cycleNode
		while(cycleNode1 != slow) { //find cycleNode
			slow = slow.next;
			cycleNode1 = cycleNode1.next;
		}

		slow = l2;
		fast = l2;
		while(fast.next != null && fast != null) {
			fast = fast.next.next;
			slow = slow.next;
			if(slow == fast && slow != null && fast != null) break;
		}

		ListNode cycleNode2 = l2;
		while(cycleNode2 != slow) {
			slow = slow.next;
			cycleNode2 = cycleNode2.next;
		}

		int l1Length = 0, l2Length = 0;
		ListNode tail1 = l1, tail2 = l2;
		for( ; tail1 != cycleNode1; tail1 = tail1.next) l1Length++;
		tail1 = tail1.next;
		l1Length++;
		for( ; tail1.next != cycleNode1; tail1 = tail1.next) l1Length++;
		System.out.println("l1Length: " + l1Length);
		
		for( ; tail2 != cycleNode2; tail2 = tail2.next) l2Length++;
		tail2 = tail2.next;
		l2Length++;
		for( ; tail2 != cycleNode2; tail2 = tail2.next) l2Length++;
		System.out.println("l2Length: " + l2Length);
		
		ListNode shorterList = l1Length <= l2Length ? l1 : l2;
		ListNode longerList = l1Length > l2Length ? l1 : l2;
		int shorterListLength = l1Length <= l2Length ? l1Length : l2Length;
		int longerListLength = l1Length > l2Length ? l1Length : l2Length;
		
		//find intersection node for 2 linked list
		while(longerListLength-- != shorterListLength) longerList = longerList.next;
		while(longerList != shorterList) {
			longerList = longerList.next;
			shorterList = shorterList.next;
		}
		
		return longerList;
	}

	private static void showResults(int[] nums1, int[] nums2, int[] seg, int cycleNode) {
		System.out.println("----ShowResults----");
		ListNode l1 = createLinkedList(nums1);
		ListNode l2 = createLinkedList(nums2);
		ListNode segment = createLinkedList(seg);
		makeTwoLinkedListIntersection(l1, l2, segment);
		
		printLinkedList(l1);
		printLinkedList(l2);
		
		makeCycle(l2, cycleNode);
		ListNode intersectionNode = findIntersectionNode(l1, l2);
		
		System.out.println("Intersection: " + intersectionNode.val);
	}

	private static void makeCycle(ListNode head, int at) {
		if(head == null) return;
		ListNode tail = head, cycleNode = head;
		
		int count = 1;
		while(count++ < at) cycleNode = cycleNode.next;
		for( ; tail.next != null; tail = tail.next);
		tail.next = cycleNode;
	}

	private static void makeTwoLinkedListIntersection(ListNode l1, ListNode l2, ListNode segment) {
		ListNode curr1;
		ListNode curr2;

		for(curr1 = l1; curr1.next != null; curr1 = curr1.next);
		curr1.next = segment;
		for(curr2 = l2; curr2.next != null; curr2 = curr2.next);
		curr2.next = segment;
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
