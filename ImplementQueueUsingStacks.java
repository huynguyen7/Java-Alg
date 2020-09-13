import java.util.LinkedList;
import java.util.Deque;

//leetcode 232, element-prog 9.9.
public class ImplementQueueUsingStacks {
	public static void main(String[] args) {
		ImplementQueueUsingStacks queue = new ImplementQueueUsingStacks();
		queue.push(1);
		queue.push(2);
		queue.push(3);

		System.out.println(queue.pop());
		System.out.println(queue.pop());
		System.out.println(queue.pop());
	}

	/** Initialize your data structure here. */
	
	private Deque<Integer> stack;
	private Deque<Integer> holder;
	
    public ImplementQueueUsingStacks() {
		stack = new LinkedList<Integer>();
		holder = new LinkedList<Integer>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
		if(stack.isEmpty()) stack.addFirst(x);
		else {
			while(!stack.isEmpty()) holder.addFirst(stack.removeFirst());
			stack.addFirst(x);
			while(!holder.isEmpty()) stack.addFirst(holder.removeFirst());
		}
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
		return stack.removeFirst();
    }
    
    /** Get the front element. */
    public int peek() {
		return stack.peekFirst();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
		return stack.isEmpty();
    }
}
