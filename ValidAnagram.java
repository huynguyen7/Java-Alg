import java.util.Map;
import java.util.HashMap;

// leetcode 242.
public class ValidAnagram {
	public static void main(String[] args) {
		String s1 = "anagram";
		String t1 = "nagaram";
		showResults(s1, t1); // expect true

		String s2 = "rat";
		String t2 = "car";
		showResults(s2, t2); // expect false

		
	}

	private static void showResults(String s, String t) {
		System.out.println("----ShowResults----");
		System.out.printf("s: %s\tt:%s\n", s, t);
		System.out.printf("Valid anagram: %b\n\n", validAnagram(s, t));
	}

	// Time: O(n), space: O(n)
	public static boolean validAnagram(String s, String t) {
		if(s.length() != t.length()) return false;
		Map<Character, Integer> map = new HashMap<>();
		for(char c: s.toCharArray())
			map.put(c, map.getOrDefault(c, 0) + 1);
		
		for(char c: t.toCharArray()) {
			if(map.containsKey(c)) map.put(c, map.get(c) - 1);
			else return false;
		}
		
		for(char c: map.keySet())
			if(map.get(c) != 0) return false;

		return true;
	}
}
