import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

// leetcode 146, element-prog 13.3
public class LRUCache {
	public static void main(String[] args) {
		LRUCache lRUCache = new LRUCache(2);

		lRUCache.put(1, 1); // cache is {1=1}
		lRUCache.put(2, 2); // cache is {1=1, 2=2}
		System.out.println(lRUCache.get(1));    // return 1

		lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
		
		System.out.println(lRUCache.get(2));    // returns -1 (not found)
		
		lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
		System.out.println(lRUCache.get(1));    // return -1 (not found)
		System.out.println(lRUCache.get(3));    // return 3
		System.out.println(lRUCache.get(4));    // return 4
	}


	// Space: O(n); n is capacity
	private ListNode dummyHead;
	private ListNode dummyTail;
	private int size;
	private int capacity;
	private Map<Integer, ListNode> map;
	
	public LRUCache(int capacity) {
		this.capacity = capacity;
		map = new HashMap<>();
		size = 0;
		dummyHead = new ListNode();
		dummyTail = new ListNode();
		dummyHead.next = dummyTail;
		dummyTail.prev = dummyHead;
	}
	
	// Using hash table data structure
	// for O(1) time lookups
	// Time: O(1)
	public int get(int key) {
		if(!map.containsKey(key)) return -1; // -1 means key not found
		else {
			ListNode node = map.get(key);
			ListNode tmpPrev = node.prev;
			ListNode tmpNext = node.next;
			tmpPrev.next = tmpNext;
			tmpNext.prev = tmpPrev;

			ListNode tmp = dummyHead.next;
			dummyHead.next = node;
			node.next = tmp;
			node.prev = dummyHead;
			tmp.prev = node;

			return node.value;
		}
	}

	// Using doubly linked list data structure for
	// adding and removing with O(1) time
	// Time: O(1)
	public void put(int key, int value) {
		if(size < capacity) {
			if(!map.containsKey(key)) {
				ListNode node = new ListNode(key, value);
				node.prev = dummyHead;
				ListNode tmp = dummyHead.next;
				dummyHead.next = node;
				node.next = tmp;
				tmp.prev = node;
				size++;
				
				map.put(key, node);
			} else {
				ListNode node = map.get(key);
				ListNode tmpPrev = node.prev;
				ListNode tmpNext = node.next;
				tmpPrev.next = tmpNext;
				tmpNext.prev = tmpPrev;
				
				ListNode tmp = dummyHead.next;
				dummyHead.next = node;
				node.next = tmp;
				node.prev = dummyHead;
				tmp.prev = node;
				
				node.value = value;
				map.put(key, node);
			}
		} else {
			if(!map.containsKey(key)) {
				ListNode node = new ListNode(key, value);
				ListNode tmp = dummyHead.next;
				dummyHead.next = node;
				node.next = tmp;
				node.prev = dummyHead;
				tmp.prev = node;

				// remove node at tail
				map.remove(dummyTail.prev.key);
				ListNode tmpTail = dummyTail.prev.prev;
				tmpTail.next = dummyTail;
				dummyTail.prev = tmpTail;

				map.put(key, node);
			} else {
				ListNode node = map.get(key);
				ListNode tmpPrev = node.prev;
				ListNode tmpNext = node.next;
				tmpPrev.next = tmpNext;
				tmpNext.prev = tmpPrev;
			
				ListNode tmp = dummyHead.next;
				dummyHead.next = node;
				node.next = tmp;
				node.prev = dummyHead;
				tmp.prev = node;
				
				node.value = value;
				map.put(key, node);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder rs = new StringBuilder();
		ListNode curr = dummyHead.next;
		while(curr != null) {
			if(curr.value == Integer.MIN_VALUE) {
				curr = curr.next;
				continue;
			}
			rs.append(curr.value + "->");
			curr = curr.next;
		}
		rs.append("NULL");
		return rs.toString();
	}
	
	public Map<Integer, ListNode> getMap() {
		return map;
	}

	class ListNode {
		int key;
		int value;
		ListNode next;
		ListNode prev;
		
		ListNode() {
			value = Integer.MIN_VALUE;
			next = null;
			prev = null;
		}

		ListNode(int key, int value) {
			this.key = key;
			this.value = value;
			next = null;
			prev = null;
		}

		ListNode(int key, int value, ListNode next, ListNode prev) {
			this.key = key;
			this.value = value;
			this.next = next;
			this.prev = prev;
		}
		
		@Override
		public String toString() {
			return String.format("[%d, %d]", key, value);
		}
	}
}
