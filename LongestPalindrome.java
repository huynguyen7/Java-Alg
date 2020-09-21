import java.util.Set;
import java.util.HashSet;

// leetcode 409.
public class LongestPalindrome {
	public static void main(String[] args) {
		String s1 = "abccccdd";
		showResults(s1); // expect 7
		
		String s2 = "a";
		showResults(s2); // expect 1

		String s3 = "bb";
		showResults(s3); // expect 2

		String s4 = "bbc";
		showResults(s4); // expect 3

		String s5 = "abbbaa";
		showResults(s5);
	}

	private static void showResults(String s) {
		System.out.println("----ShowResults----");
		System.out.printf("%s -> %d\n\n", s, longestPalindrome(s));
	}

	// Time: O(n), space: O(m)
	public static int longestPalindrome(String s) {
		int longestPalindromeLength = 0;
			
		// This set contains chars that appear ODD times
		Set<Character> set = new HashSet<>();
		for(char c: s.toCharArray()) {
			if(!set.contains(c)) set.add(c);
			else set.remove(c);
		}

		System.out.println(set.toString());
		
		if(set.size() <= 1) return s.length(); // all chars in s appear EVEN times (one char might be ODD times),
												// which means it can easily form a palindrome
												// For example: "aabb" -> abba; "aabbc" -> "abcba"
		return s.length() - set.size() + 1;
	}
}
