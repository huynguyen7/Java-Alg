import java.util.Iterator;

// leetcode 706.
public class DesignHashMap {
	public static void main(String[] args) {
		DesignHashMap map = new DesignHashMap();
		map.put(6,1);
		map.put(7,2);
		map.put(8,0);
		map.put(1,0);
		map.put(8,1);
		map.put(1,2);

		map.remove(6);
		Iterator<Integer> iter = map.keySet();
		while(iter.hasNext()) {
			int key = iter.next();
			System.out.printf("[%d - %d]\n", key, map.get(key));
		}
	}
	
	private boolean[] keys;
	private int[] values;

	public DesignHashMap() {
		keys = new boolean[1000000];
		values = new int[1000000];
	}

	public void put(int key, int value) {
		keys[key] =  true;
		values[key] = value;
	}

	public int get(int key) {
		return keys[key] ? values[key] : -1;
	}

	public void remove(int key) {
		keys[key] = false;
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
				while(index < keys.length && !keys[index]) index++;
				int tmp = index++;
				return tmp;
			}
		};
	}
}
