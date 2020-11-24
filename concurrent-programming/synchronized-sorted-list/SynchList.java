import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

// Sorted linked list.

/*
	This implementation uses 'synchronized' keyword (structured lock).
**/
public class SynchList<T extends Comparable> implements ListSet<T>{
	private ListNode<T> head;

	public SynchList() {}

	// Time: O(n), space: O(1)
	public synchronized boolean contains(T val) {
		if(isEmpty()) return false;

		ListNode<T> x = head;
		while(x != null) {
			if(val.compareTo(x.val) == 0)
				return true;
			x = x.next;
		}

		return false;
	}

	// Time: O(n), space: O(1)
	public synchronized void add(T val) {
		ListNode<T> node = new ListNode(val);

		if(isEmpty()) {
			head = node;
		} else if(val.compareTo(head.val) < 0) {
			node.next = head;
			head = node;
		} else {
			ListNode<T> curr = head, prev = head;

			while(curr != null) {
				if(val.compareTo(curr.val) < 0) { 
					node.next = curr;
					prev.next = node;
					return;
				} else {
					prev = curr;
					curr = curr.next;
				}
			}

			if(curr == null) // reach the end and the node is not being added.
				prev.next = node;
		}
	}

	// Time: O(n), space: O(1)
	public synchronized void remove(T val) {
		if(isEmpty()) return;
		else if(val.compareTo(head.val) == 0) {
			head = head.next;
		} else {
			ListNode<T> curr = head, prev = head;
			
			while(curr != null) {
				if(val.compareTo(curr.val) == 0) {
					prev.next = curr.next;
					return;
				} else {
					prev = curr;
					curr = curr.next;
				}
			}
		}
	}

	// Time: O(1), space: O(1)
	public synchronized boolean isEmpty() {
		return head == null;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		ListNode<T> x = head;
		while(x != null) {
			s.append(x.toString() + "->");
			x = x.next;
		}
		s.append("NULL");
		
		return s.toString();
	}

	private static class ListNode<T extends Comparable> {
		public T val;
		public ListNode<T> next;
		
		public ListNode(T val) {
			this.val = val;
		}

		public ListNode(T val, ListNode<T> next) {
			this.val = val;
			this.next = next;
		}

		@Override
		public String toString() {
			return String.format("%s", val.toString());
		}
	}
}
