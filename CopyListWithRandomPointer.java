import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

// leetcode 138.
public class CopyListWithRandomPointer {
	public static void main(String[] args) {
		Integer[][] nums1 = {{7,null},{13,0},{11,4},{10,2},{1,0}};
		showResults(nums1);

		Integer[][] nums2 = {{1,1},{2,1}};
		showResults(nums2);

		Integer[][] nums3 = {{3,null},{3,0},{3,null}};
		showResults(nums3);

		Integer[][] nums4 = {};
		showResults(nums4);
	}

	private static void showResults(Integer[][] nums) {
		System.out.println("----ShowResults----");
		Node head = createLinkedList(nums);
		printLinkedList(head);

		Node rs = copyRandomList(head);
		printLinkedList(rs);
		System.out.println();
	}

	// GOOD EXPLANATION:
	// https://www.youtube.com/watch?v=OvpKeraoxW0

	// NOTES:
	// - Node have extra variable 'random'.
	// - Singly linked list.

	// There is still a better approach with a constant space complexity. Check the video above.
	
	// Time: O(n), space: O(n)
	public static Node copyRandomListII(Node head) {
		if(head == null) return null;
		
		Map<Node, Node> map = new HashMap<>(); // <node-clone> pair
		Node curr = head, prev = null;

		// Add clone.
		while(curr != null) {
			Node clone = new Node(curr.val);
			if(prev != null)
				map.get(prev).next = clone;
			map.put(curr, clone);
			
			prev = curr;
			curr = curr.next;
		}

		// Add clone's random.
		for(Map.Entry<Node, Node> entry: map.entrySet()) {
			Node currClone = entry.getValue();
			currClone.random = map.get(entry.getKey().random);
		}

		return map.get(head);
	}
	
	private static Node createLinkedList(Integer[][] nums) {
		if(nums.length == 0) return null;

		List<Node> listNodes = new ArrayList<>();
		
		for(int i = 0; i < nums.length; ++i)
			listNodes.add(new Node(nums[i][0]));
		for(int i = 1; i < nums.length; ++i)
			listNodes.get(i-1).next = listNodes.get(i);
		for(int i = 0; i < nums.length; ++i) {
			Node curr = listNodes.get(i);
			Integer indexRandom = nums[i][1];
			if(indexRandom != null)
				curr.random = listNodes.get(indexRandom);
		}

		return listNodes.get(0);
	}

	private static void printLinkedList(Node head) {
		Node x = head;
		while(x != null) {
			System.out.print(x.toString() + "->");
			x = x.next;
		}
		System.out.print("NULL\n");
	}

	static class Node {
		int val;
		Node next;
		Node random;
		Node() {}
		Node(int val) {this.val = val;}
		Node(int val, Node next) {
			this.val = val;
			this.next = next;
		}
		Node(int val, Node next, Node random) {
			this.val = val;
			this.next = next;
			this.random = random;
		}

		@Override
		public String toString() {
			StringBuilder s = new StringBuilder();
			s.append("[" + val + ",");
			if(random == null)
				s.append("NULL" + "]");
			else s.append(random.val + "]");
			return s.toString();
		}
	}
}
