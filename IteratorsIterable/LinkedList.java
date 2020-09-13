import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;

public class LinkedList implements Iterator<ListNode> {
	public static void main(String[] args) {
		Integer[] nums1 = {1,2,3,4,5,6};
		testWithIntegers(nums1);
	}

	private static void testWithIntegers(Integer[] nums) {
		System.out.println(Arrays.toString(nums));
		ListNode<Integer> head = createLinkedList(nums);
		Iterator<ListNode> iter = new LinkedList(head);
		
		while(iter.hasNext()) {
			ListNode<Integer> curr = iter.next();
			System.out.print(curr + "->");
		}
		System.out.print("NULL\n");
	}
	
	private ListNode curr;
	
	public LinkedList(ListNode head) {
		this.curr = head;
	}
	
	@Override
	public boolean hasNext() {
		return curr != null;
	}

	@Override
	public ListNode next() {
		if(!hasNext()) throw new NoSuchElementException();
		ListNode tmp = curr;
		curr = curr.getNext();
		return tmp;
	}

	private static ListNode createLinkedList(Object[] vals) {
		if(vals.length == 0 || vals == null) return null;
		ListNode dummy = new ListNode();
		ListNode curr = dummy;
		
		for(int i = 0; i < vals.length; ++i) {
			curr.setNext(new ListNode(vals[i]));
			curr = curr.getNext();
		}

		return dummy.getNext();
	}
}
