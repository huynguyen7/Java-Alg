import java.util.Deque;
import java.util.LinkedList;

//leetcoe 622, element-prog 9.8
public class DesignCircularQueue {
	public static void main(String args[]) {
		/**
		DesignCircularQueue queue = new DesignCircularQueue(3);
		System.out.println(queue.enQueue(1)); //true
		System.out.println(queue.enQueue(2)); //true
		System.out.println(queue.enQueue(3)); //true
		System.out.println(queue.enQueue(4)); //false
		System.out.println(queue.Rear()); //3
		System.out.println(queue.isFull()); //true
		System.out.println(queue.deQueue()); //true
		System.out.println(queue.enQueue(4)); //true
		System.out.println(queue.Rear()); //4
		*/
		
		/**
		DesignCircularQueue queue = new DesignCircularQueue(8);
		System.out.println(queue.deQueue()); // false
		System.out.println(queue.enQueue(1)); // true
		System.out.println(queue.enQueue(2)); // true
		System.out.println(queue.Rear()); // 2
		System.out.println(queue.enQueue(3)); // true
		System.out.println(queue.enQueue(4)); // true
		System.out.println(queue.enQueue(5)); // true
		System.out.println(queue.deQueue()); // true
		System.out.println(queue.Rear()); // 5
		System.out.println(queue.Front()); // 2
		
		System.out.println(queue.enQueue(7));
		System.out.println(queue.enQueue(11));
		System.out.println(queue.enQueue(12));
		System.out.println(queue.enQueue(13));
		System.out.println(queue.enQueue(18));
		System.out.println(queue.enQueue(20));
		*/
	}

	private int count;
	private int[] nums;
	private int head;
	private int tail;
	
	//Set the size of the queue to be k.
	public DesignCircularQueue(int k) {
		nums = new int[k];
		this.count = 0;
		this.head = 0;
		this.tail = 0;
	}

	/** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
		if(isFull()) return false;
		count++;
		nums[tail] = value;
		tail = (tail + 1) % nums.length;
		return true;
    }

	/** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
		if(isEmpty()) return false;
		count--;
		head = (head + 1) % nums.length;
		return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
		if(isEmpty()) return -1;
		return nums[head];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
		if(isEmpty()) return -1;
		int index = (tail - 1) % nums.length;
		if(tail - 1 < 0) index = nums.length + index;
		return nums[index];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
		return count == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
		return count == nums.length;
    }
}
