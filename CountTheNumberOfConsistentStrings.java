import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.IntStream;

// leetcode 1684.
public class CountTheNumberOfConsistentStrings {
	public static void main(String[] args) {
		String allowed1 = "ab";
		String[] words1 = {"ad","bd","aaab","baa","badab"};
		showResults(allowed1, words1); // expect 2

		String allowed2 = "abc";
		String[] words2 = {"a","b","c","ab","ac","bc","abc"};
		showResults(allowed2, words2); // expect 7

		String allowed3 = "cad";
		String[] words3 = {"cc","acd","b","ba","bac","bad","ac","d"};
		showResults(allowed3, words3); // expect 4
	}

	private static void showResults(String allowed, String[] words) {
		System.out.println("----ShowResults----");
		System.out.println("ALLOWED: " + allowed);
		System.out.println("INPUT: " + Arrays.toString(words));
		int rs = countConsistentStrings(allowed, words);
		System.out.printf("Number of Consistent Strings: %d\n\n", rs);
	}

	// n is total chars in words[], m is total chars in allowed.
	// Time: O(n + m), space: O(n + m)
	public static int countConsistentStrings(String allowed, String[] words) {
		if(allowed.length() == 0 || words.length == 0) return words.length;

		char[] tokens = allowed.toCharArray(); // space: O(m)
		Set<Character> charsAllowed = new HashSet<>(); // space: O(m)
		for(char c: tokens) // time: O(m)
			charsAllowed.add(c);

		int count = 0;
		boolean flag;
		for(String s: words) {
			flag = true;
			for(char c: s.toCharArray()) {
				if(!charsAllowed.contains(c)) {
					flag = false;
					break;
				}
			}

			if(flag) count++;
		}

		return count;
	}
}
