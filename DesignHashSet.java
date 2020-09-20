import java.util.Iterator;

// leetcode 705
public class DesignHashSet {
	public static void main(String[] args) {
		DesignHashSet set = new DesignHashSet();
		set.add(1);
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(2);
		set.add(10);
		set.add(5);		
		set.add(1000000);
		set.add(0);

		Iterator<Integer> iter = set.keySet();
		while(iter.hasNext())
			System.out.printf("%d ", iter.next());
		System.out.println();
	}

	private boolean[] keys;
	
	public DesignHashSet() {
		keys = new boolean[1000001];
	}

	private int getHashCode(int key) {
		return key % 1000001;
	}

	public void add(int key) {
		keys[getHashCode(key)] = true;
	}

	public void remove(int key) {
		keys[getHashCode(key)] = false;
	}

	public boolean contains(int key) {
		return keys[getHashCode(key)];
	}

	public Iterator<Integer> keySet() {
		return new Iterator<Integer>() {
			int index = 0;
			@Override
			public boolean hasNext() {
				int tmp = index;
				while(tmp < keys.length && !keys[tmp]) tmp++;
				if(tmp == keys.length) return false;
				else return true;
			}

			@Override
			public Integer next() {
				while(!keys[index])
					index++;
				int tmp = index++;
				return Integer.valueOf(tmp);
			}
		};
	}
}
