import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

// Sorted linked list.

/*
	This implementation uses ReentrantReadWriteLock (unstructured lock).
**/
public class RWCoarseList<T extends Comparable> implements ListSet<T>{
	public static void main(String[] args) {
		CoarseList<Integer> list = new CoarseList<>();

		list.add(11);
		list.add(2);
		list.add(9);
		System.out.println(list.toString());
		
		list.remove(2);
		System.out.println(list.toString());
		list.remove(11);
		System.out.println(list.toString());
	}

	private ListNode<T> head;
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public RWCoarseList() {}

	// Time: O(n), space: O(1)
	public boolean contains(T val) {
		try {
			lock.readLock().lock(); // acquire lock in READ MODE
			if(isEmpty()) return false;

			ListNode<T> x = head;
			while(x != null) {
				if(val.compareTo(x.val) == 0)
					return true;
				x = x.next;
			}
			
			return false;
		} finally {
			lock.readLock().unlock();
		}
	}

	// Time: O(n), space: O(1)
	public void add(T val) {
		try {
			lock.writeLock().lock(); // acquire lock in WRITE MODE
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
		} finally {
			lock.writeLock().unlock();
		}
	}

	// Time: O(n), space: O(1)
	public void remove(T val) {
		try {
			lock.writeLock().lock(); // acquire lock in WRITE MODE
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
		} finally {
			lock.writeLock().unlock();
		} 
	}

	// Time: O(1), space: O(1)
	public boolean isEmpty() {
		try {
			lock.readLock().lock();
			return head == null;
		} finally {
			lock.readLock().unlock();
		}
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
