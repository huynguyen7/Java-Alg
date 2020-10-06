// leetcode 707.
public class DesignLinkedList {
	public static void main(String[] args) {
		DesignLinkedList myLinkedList = new DesignLinkedList();
		
		myLinkedList.addAtHead(7);
		myLinkedList.addAtHead(2);
		myLinkedList.addAtHead(3);
		myLinkedList.printLinkedList(); // 3->2->7

		myLinkedList.addAtIndex(3,0);
		myLinkedList.printLinkedList(); // 3->2->7->0

		myLinkedList.deleteAtIndex(2);
		myLinkedList.printLinkedList(); // 3->2->0

		myLinkedList.addAtHead(6);
		myLinkedList.printLinkedList(); // 6->3->2->0

		myLinkedList.addAtTail(4);
		myLinkedList.printLinkedList(); // 6->3->2->0->4

		System.out.println(myLinkedList.get(4)); // expect 4

		myLinkedList.deleteAtIndex(2);
		myLinkedList.printLinkedList(); // 6->3->0->4
		
		myLinkedList.addAtHead(4);
		myLinkedList.printLinkedList(); // 4->6->3->0->4
		
		myLinkedList.addAtIndex(5,0);
		myLinkedList.printLinkedList(); // 4->6->3->0->4->0

		myLinkedList.addAtHead(6);
		myLinkedList.printLinkedList(); // 6->4->6->3->0->4->0
	}

	ListNode head;

	public DesignLinkedList() {
		
	}

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        int currIndex = 0;
		ListNode curr = head;
		while(currIndex++ != index) {
			if(curr == null) return -1;
			curr = curr.next;
		}

		if(curr == null) return -1;
		return curr.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
		node.next = head;
		head = node;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        if(head == null) {
            head = new ListNode(val);
            return;
        }
        
		ListNode curr = head;
		for( ; curr.next != null; curr = curr.next);
		
		curr.next = new ListNode(val);
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(head == null) {
            head = new ListNode(val);
            return;
        }
        
		ListNode curr = head;
		ListNode prev = curr;
		
		while(index-- != 0) { // find the node at index.
			if(curr == null) return;
			
			prev = curr;
			curr = curr.next;
		}

		if(curr == null && index == -1) addAtTail(val);
		
		ListNode node = new ListNode(val);
		node.next = curr;
		prev.next = node;
		if(isHead(curr)) head = node;
    }

	private boolean isHead(ListNode node) {
		return node == head;
	}
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(head == null) return;

        ListNode curr = head;
		ListNode prev = curr;
		
		while(index-- != 0) { // find the node at index.
			if(curr == null) return;
			prev = curr;
			curr = curr.next;
		}

		if(isHead(curr)) head = head.next;
		prev.next = curr != null ? curr.next : null;
    }

	private void printLinkedList() {
        if(head == null) return;
        
		ListNode x = head;
		while(x != null) {
			System.out.printf("%d->", x.val);
			x = x.next;
		}
		System.out.print("NULL\n");
	}

	class ListNode {
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
