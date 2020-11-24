public interface ListSet<T extends Comparable> {
	public boolean contains(T val);
	public void add(T val);
	public void remove(T val);
	public boolean isEmpty();
}
