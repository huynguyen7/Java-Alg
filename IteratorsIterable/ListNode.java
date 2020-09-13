public class ListNode<T> {
	public static void main(String[] args) {
		System.out.println("---Test---");
		ListNode<Integer> node1 = new ListNode(8);
		System.out.println(node1);
		System.out.println(node1.getNext());
	}
	
	private T val;
	private ListNode next;

	public ListNode() {}

	public ListNode(T val) {
		this.val = val;
	}

	public ListNode(ListNode next) {
		this.next = next;
	}

	public ListNode(T val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	public T getVal() {
		return this.val;
	}

	public void setVal(T val) {
		this.val = val;
	}

	public ListNode getNext() {
		return this.next;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return String.valueOf(this.val);
	}
}
