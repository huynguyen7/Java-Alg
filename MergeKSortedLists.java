import java.util.Arrays;
import java.util.PriorityQueue;

//leetcode 23.
public class MergeKSortedLists {
	public static void main(String args[]) {
		int[][] lists1 = {
			{1,4,5},
			{1,3,4},
			{2,6}
		};
		showResults(lists1); //expect 1->1->2->3->4->4->5->6->NULL

		int[][] lists2 = {};
		showResults(lists2); //expect NULL

		int[][] lists3 = {{}};
		showResults(lists3); //expect NULL
	}
	
	private static void showResults(int[][] lnums) {
		System.out.println("----ShowResults----");
		ListNode[] lists = new ListNode[lnums.length];
		for(int i = 0; i < lnums.length; ++i) {
			lists[i] = constructLinkedListFromArray(lnums[i]);
			printLinkedList(lists[i]);
		}
		System.out.println();

		ListNode rs = mergeKLists(lists);
		printLinkedList(rs);
		System.out.println();
	}

	//time: O(nlogk), space: O(k); k is the number of lists, n is the number of node in k'th list.
	public static ListNode mergeKLists(ListNode[] lists) {
		if(lists.length == 0 || lists == null) return null;

		ListNode dummy = new ListNode();
		ListNode curr = dummy;

		PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
			lists.length, //construct max size for heap.
			(ListNode a1, ListNode a2) -> a1.val - a2.val //lambda expression implement Comparator interface
		);

		for(int i = 0; i < lists.length; ++i) {
			if(lists[i] != null) {
				minHeap.add(lists[i]);
				lists[i] = lists[i].next;
			}
		}
		
		while(!minHeap.isEmpty()) {
			ListNode min = minHeap.poll();
			curr.next = min;
			curr = curr.next;
			
			if(min.next != null) {
				min = min.next;
				minHeap.add(min);
			}
		}
		
		return dummy.next;
	}

	private static ListNode constructLinkedListFromArray(int[] nums) {
		if(nums.length == 0 || nums == null) return null;
		ListNode dummy = new ListNode();
		ListNode curr = dummy;
		
		for(int i = 0; i < nums.length; ++i) {
			curr.next = new ListNode(nums[i]);
			curr = curr.next;
		}
	
		return dummy.next;
	}

	private static void printLinkedList(ListNode head) {
		if(head == null) {
			System.out.println("NULL");	
			return;
		}
		
		for(ListNode x = head; x != null; x = x.next)
			System.out.printf("%d->", x.val);
		System.out.print("NULL\n");
	}

	static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) {this.val = val;}
		ListNode(int val, ListNode next) {this.val = val; this.next = next;}
	}
}
