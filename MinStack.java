//leetcode 155.
public class MinStack {
	public static void main(String args[]) {
		MinStack a = new MinStack();
		a.push(1);
		System.out.println(a.getMin());
		
		a.push(3);
		a.push(2);
		a.push(4);
		a.push(3);
		System.out.println(a.top());
		System.out.println(a.getMin());
	}

	static class ListNode {
		int val;
		int min;
		ListNode next;
		
		ListNode(int val) {
			this.val = val;
			min = Integer.MAX_VALUE;
			next = null;
		}
	}

	private ListNode top;
	
	public MinStack() {}
	
	public void push(int val) {
		if(top == null) {
			top = new ListNode(val);
			top.min = val;
		} else {
			ListNode a = new ListNode(val);
			a.next = top;
			a.min = Math.min(top.min, val);
			top = a;
		}
	}

	public void pop() {
		top = top.next;
	}

	public int top() {
		return top.val;
	}

	public int getMin() {
		return top.min;
	}
}
