import java.util.Map;
import java.util.HashMap;

// leetcode 266.
// Given a string, determine if a permutation of the string could
// form a palindrome.

public class PalindromePermutation {
	public static void main(String[] args) {
		String s1 = "tact cat";
		showResults(s1); // expect false

		String s2 = "code";
		showResults(s2); // expect false

		String s3 = "carerac";
		showResults(s3); // expect true

		String s4 = "aab";
		showResults(s4); // expect true
	}

	private static void showResults(String s) {
		System.out.println("----ShowResults----");
		System.out.printf("%s -> %b\n\n", s,
						isPalindromePermutation(s));
	}

	// Time: O(), space: O()
	public static boolean isPalindromePermutation(String s) {
		if(s.length() <= 1) return true;
		
		s = s.toLowerCase();
		Map<Character, Integer> map = new HashMap<>();
		
		for(char c: s.toCharArray())
			map.put(c, map.getOrDefault(c, 0) + 1);

		int count = 0;
		for(int freqs: map.values())
			count+= (freqs % 2);

		return count <= 1;
	}
}
