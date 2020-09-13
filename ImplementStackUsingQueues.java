import java.util.Deque;
import java.util.LinkedList;

//leetcode 225
public class ImplementStackUsingQueues {
	public static void main(String args[]) {
		ImplementStackUsingQueues stack = new ImplementStackUsingQueues();
		stack.push(1);
		stack.push(2);
		stack.push(3);

		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
	
    /** Initialize your data structure here. */
	
	private Deque<Integer> queue = new LinkedList<>();
	private int size = 0;

    public ImplementStackUsingQueues() {
		
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
		size++;
		queue.addLast(x);
		
		int tmp = size - 1;
		int val;
		while(tmp-- > 0) {
			val = queue.removeFirst();
			queue.addLast(val);
		}
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
		size--;
		return queue.removeFirst();
    }
    
    /** Get the top element. */
    public int top() {
		return queue.getFirst();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
		return queue.isEmpty();
    }
}
