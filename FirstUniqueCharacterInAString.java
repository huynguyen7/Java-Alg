import java.util.Map;
import java.util.HashMap;

// leetcode 387.
public class FirstUniqueCharacterInAString {
	public static void main(String[] args) {
		String s1 = "leetcode";
		showResults(s1); // expect 0
		
		String s2 = "loveleetcode";
		showResults(s2); // expect 2

		String s3 = "";
		showResults(s3); // expect -1

		String s4 = "cc";
		showResults(s4); // expect -1

		String s5 = "aabb";
		showResults(s5); // expect -1
	}

	private static void showResults(String s) {
		System.out.println("----ShowResults----");
		System.out.printf("%s -> %d\n\n", s,
						firstUniqChar(s));
	}

	// Time: O(n), space: O(m)
	// n is s.length(), m is total UNIQUE chars in s.
	public static int firstUniqChar(String s) {
		Map<Character, Integer> map = new HashMap<>(); // char-index pair
		for(int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if(map.containsKey(c)) map.put(c, s.length()); // s.length() means this string has duplicates
			else map.put(c, i);
		}
		
		System.out.println(map.toString());
		
		int minIndex = Integer.MAX_VALUE;
		for(int index: map.values())
			minIndex = Math.min(minIndex, index);
		
		return minIndex >= s.length() ? -1 : minIndex;
	}
}
