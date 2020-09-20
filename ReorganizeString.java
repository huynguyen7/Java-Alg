import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;

// leetcode 767
public class ReorganizeString {
	public static void main(String[] args) {
		String s1 = "aab";
		showResults(s1); // expect "aba"
		
		String s2 = "aaab";
		showResults(s2); // expect ""
		
		String s3 = "vvvlo";
		showResults(s3); // expect "vlvov"
	}

	private static void showResults(String s) {
		System.out.println("----ShowResults----");
		System.out.printf("%s -> %s\n\n", s,
						reorganizeString(s));
	}

	// Time: O(nlogm), space: O(logm)
	// n is s.length(), m is total UNIQUE character
	public static String reorganizeString(String s) {
		if(s.length() <= 1) return s;
		
		Map<Character, Integer> map = new HashMap<>();
		for(char c: s.toCharArray())
			map.put(c, map.getOrDefault(c, 0) + 1);

		PriorityQueue<CharCounts> maxHeap = new PriorityQueue<>(
			(a, b) -> Integer.compare(b.count, a.count)
		);
		for(Map.Entry<Character, Integer> entry: map.entrySet())
			maxHeap.add(new CharCounts(entry.getKey(), entry.getValue()));
		
		StringBuilder rs = new StringBuilder();
		
		while(!maxHeap.isEmpty()) {
			CharCounts charCounts1 = maxHeap.poll();
			CharCounts charCounts2 = maxHeap.poll();
			
			if(rs.length() > 0 && rs.charAt(rs.length() - 1) == charCounts1.c) return "";
			else {
				rs.append((char) charCounts1.c);
				
				if(--charCounts1.count != 0)
					maxHeap.add(charCounts1);
			}

			if(charCounts2 != null) {
				if(rs.length() > 0 && rs.charAt(rs.length() - 1) == charCounts2.c) return "";
				else {
					rs.append((char) charCounts2.c);
					
					if(--charCounts2.count != 0)
						maxHeap.add(charCounts2);
				}
			}
		}

		return rs.toString();
	}

	static class CharCounts {
		char c;
		int count;
		
		CharCounts(char c, int count) {
			this.c = c;
			this.count = count;
		}

		@Override
		public String toString() {
			return String.format("%c -> %d", c, count);
		}
	}
}
