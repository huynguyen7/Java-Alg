import java.util.*;

public class LinkedList<T> implements Iterable<T> {
	private ListNode<T> head, tail;
	
	public LinkedList() {}
	
	public void add(T data) {
		if(head == null) {
			head = new ListNode<T>(data);
			tail = head;
		} else {
			tail.setNext(new ListNode<T>(data));
			tail = tail.getNext();
		}
	}

	public ListNode<T> getHead() {
		return this.head;
	}

	public ListNode<T> getTail() {
		return this.tail;
	}

	@Override
	public String toString() {
		StringBuilder rs = new StringBuilder();
		ListNode<T> curr = head;
		while(curr != null) {
			rs.append(curr.getVal() + "->");
			curr = curr.getNext();
		}
		rs.append("NULL");
		
		return rs.toString();
	}

	public Iterator<T> iterator() {
		return new Iterator<T>() {
			ListNode<T> curr = head;
			
			@Override
			public boolean hasNext() {
				return curr != null;
			}
			
			@Override
			public T next() {
				ListNode<T> tmp = curr;
				curr = curr.getNext();
				return tmp.getVal();
			}
		};
	}
}
