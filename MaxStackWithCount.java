import java.util.LinkedList;
import java.util.Deque;

//element-prog 9.1
public class MaxStackWithCount {
	public static void main(String args[]) {

	}
	
	static class MaxWithCount {
		public Integer max;
		public Integer count;
		
		public MaxWithCount(Integer max, Integer count) {
			this.max = max;
			this.count = count;
		}
	}

	private Deque<Integer> elements = new LinkedList<>();
	private Deque<MaxWithCount> cachedMaxWithCount = new LinkedList<>();

	public boolean isEmpty() {
		return elements.isEmpty();
	}

	public Integer max() {
		if(isEmpty()) throw new IllegalStateException("max(): empty stack");
		return cachedMaxWithCount.peekFirst().max;
	}

	public Integer pop() {
		if(isEmpty()) throw new IllegalStateException("pop(): empty stack");
		Integer popElement = elements.removeFirst();
		if(popElement.equals(cachedMaxWithCount.peekFirst().max)) {
			cachedMaxWithCount.peekFirst().count = cachedMaxWithCount.peekFirst().count - 1;
			if(cachedMaxWithCount.peekFirst().count.equals(0))
				cachedMaxWithCount.removeFirst();
		}

		return popElement;
	}

	public void push(Integer x) {
		elements.addFirst(x);
		if(!isEmpty()) {
			if(Integer.compare(x, cachedMaxWithCount.peek().max) == 0)
				cachedMaxWithCount.peekFirst().count = cachedMaxWithCount.peekFirst().count + 1; //update count value
			else if(Integer.compare(x, cachedMaxWithCount.peek().max) > 0)
				cachedMaxWithCount.addFirst(new MaxWithCount(x, 1));
		} else {
			cachedMaxWithCount.addFirst(new MaxWithCount(x, 1));
		}
	}
}
