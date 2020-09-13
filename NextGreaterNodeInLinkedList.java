import java.util.Arrays;
import java.util.Stack;

//leetcode 1019.
public class NextGreaterNodeInLinkedList {
	public static void main(String args[]) {
		int[] nums1 = {2,1,5}; //expect [5,5,0] 
		int[] nums2 = {2,7,4,3,5}; //expect [7,0,5,5,0]
		int[] nums3 = {1,7,5,1,9,2,5,1}; //expect [7,9,9,9,0,5,0,0]
		
		showResults(nums1);
		showResults(nums2);
		showResults(nums3);
	}
	
	//time: O(n), space: O(n); n is the length of linked list.
	public static int[] nextLargerNodes(ListNode head) {
		if(head == null) return new int[0];
		
		int listLength = 0;
		for(ListNode curr = head; curr != null; curr = curr.next)
			listLength++;
		int[] rs = new int[listLength];
		
		Stack<int[]> stack = new Stack<>();
		int p = 0;
		while(head != null) {
			int v = head.val;
			while(!stack.isEmpty() && stack.peek()[0] < v) {
				rs[stack.peek()[1]] = v;
				stack.pop();
			}

			stack.push(new int[] {v, p++});
			head = head.next;
		}

		while(!stack.isEmpty()) {
			int[] node = stack.pop();
			rs[node[1]] = 0;
		}
		
		return rs;
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		ListNode l = createLinkedList(nums);
		printLinkedList(l);
		int[] rs = nextLargerNodes(l);
		System.out.println(Arrays.toString(rs) + "\n");
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
