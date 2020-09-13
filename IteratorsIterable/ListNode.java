public class ListNode<T>{
	private T val;
	private ListNode<T> next;

	public ListNode() {}
	public ListNode(T val) {this.val = val;}
	public ListNode(T val, ListNode<T> next) {this.val = val; this.next = next;}

	public void setVal(T val) {
		this.val = val;
	}

	public void setNext(ListNode<T> next) {
		this.next = next;
	}

	public T getVal() {
		return this.val;
	}

	public ListNode<T> getNext() {
		return this.next;
	}
}
