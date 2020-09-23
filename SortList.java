import java.util.Arrays;

// leetcode 148, element-prog 14.9
public class SortList {
	public static void main(String[] args) {
		int[] nums1 = {4,2,1,3};
		showResults(nums1);
		
		int[] nums2 = {-1,5,3,4,0};
		showResults(nums2);

		int[] nums3 = new int[0];
		showResults(nums3);
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		ListNode head = toLinkedList(nums);
		printLinkedList(head);
		
		ListNode sortedList = sortList(head);
		printLinkedList(sortedList);
	}

	public static ListNode sortList(ListNode head) {
		return mergeSort(head);
	}

	// Using merge sort
	// Time: O(nlogn), space: O(logn)
	// space is logn for call stack
	public static ListNode mergeSort(ListNode head) {
		if(head == null || head.next == null) return head;
		
		ListNode slow = head; // This point to the end node of first half
		ListNode fast = head.next; // This point to the end node of second half
		
		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		ListNode rightHalf = mergeSort(slow.next);
		slow.next = null; // This prevent leftHalf go over the rightHalf
		ListNode leftHalf = mergeSort(head);

		return merge(leftHalf, rightHalf);
	}

	private static ListNode merge(ListNode leftHalf, ListNode rightHalf) {
		ListNode dummy = new ListNode();
		ListNode curr = dummy;

		while(leftHalf != null || rightHalf != null) {
			if(leftHalf != null && rightHalf != null) {
				if(leftHalf.val <= rightHalf.val) {
					curr.next = leftHalf;
					leftHalf = leftHalf.next;
				}
				else {
					curr.next = rightHalf;
					rightHalf = rightHalf.next;
				}
			} else if(leftHalf != null) {
				curr.next = leftHalf;
				leftHalf = leftHalf.next;
			} else {
				curr.next = rightHalf;
				rightHalf = rightHalf.next;
			}
			
			curr = curr.next;
		}

		return dummy.next;
	}

	private static ListNode toLinkedList(int[] nums) {
		ListNode dummy = new ListNode();
		ListNode curr = dummy;
		
		for(int i = 0; i < nums.length; ++i) {
			curr.next = new ListNode(nums[i]);
			curr = curr.next;
		}

		return dummy.next;
	}

	private static void printLinkedList(ListNode head) {
		for(ListNode x = head; x != null; x = x.next)
			System.out.printf("%d->", x.val);
		System.out.println("NULL\n");
	}

	static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) {this.val = val;}
		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}
}
