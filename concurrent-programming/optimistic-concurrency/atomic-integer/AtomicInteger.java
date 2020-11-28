/*
	NOTES: THIS IMPLEMENTATION IS ONLY TO SHOW HOW THE LOGIC OF COMPAREANDSET()
	IN OPTIMISTIC CONCURRENCY, BUT THE FACT IS THAT THE OUTPUT IS NOT CORRECT!!!
	PLEASE USE THE AtomicInteger CLASS IMPLEMENTED BY JAVA LIBRARY CORRECTNESS.
	
	This is an implementation of Atomic Integer by using Optimistic Concurrency.
	Optimistic Concurrency makes sure that there is no DEADLOCKS, LIVELOCKS.
*/

public class AtomicInteger {

	private volatile int num; // using volatile to prevent cache wrong value.

	public AtomicInteger() {
		num = 0;
	}

	public AtomicInteger(int num) {
		this.num = num;
	}

	public AtomicInteger(Integer num) {
		this.num = num.intValue();
	}

	public int get() {
		return num;
	}

	public void set(int value) {
		this.num = value;
	}

	private final boolean compareAndSet(int curr, int next) {
		if(curr != num) return false; // the value has been changed by another thread.
		else {
			this.set(next);
			return true;
		}
	}

	public final int getAndAdd(int delta) {
		while(true) { // APPLYING OPTIMISTIC CONCURRENCY BY USING INFINITE LOOP.
			int curr = this.get();
			int next = curr + delta;
			if(compareAndSet(curr, next))
				return curr;
		}
	}
}
