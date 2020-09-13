import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;

//leetcode 451.
public class SortCharactersByFrequency {
	public static void main(String args[]) {
		String s1 = "tree";
		showResults(s1); //expect "eert"
		
		String s2 = "cccaaa";
		showResults(s2); //expect "cccaaa"
		
		String s3 = "Aabb";
		showResults(s3); //expect "bbaA"
	}

	private static void showResults(String s) {
		System.out.println("----ShowResults----");
		System.out.printf("%s -> %s\n\n", s, frequencySort(s));
	}

	//time: O(nlogn), space: O(n); n is s.length()
	public static String frequencySort(String s) {
		if(s.length() == 0) return "";
		
		Map<Character, Integer> map = new HashMap<>();
		for(char c: s.toCharArray())
			map.put(c, map.getOrDefault(c, 0) + 1);
		
		PriorityQueue<Character> maxHeap = new PriorityQueue<>(
			(Character a, Character b) -> Integer.compare(map.get(b), map.get(a))
		);

		for(Character key: map.keySet())
			maxHeap.add(key);
		
		StringBuilder rs = new StringBuilder();
		while(!maxHeap.isEmpty()) {
			char c = maxHeap.poll();
			int freq = map.get(c);
			while(freq-- != 0) rs.append(c);
		}

		return rs.toString();
	}
}
