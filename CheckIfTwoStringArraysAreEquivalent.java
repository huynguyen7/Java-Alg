import java.util.Arrays;

// leetcode 1662.
public class CheckIfTwoStringArraysAreEquivalent {
	public static void main(String[] args) {
		String[] word1 = {"ab", "c"};
		String[] word2 = {"a", "bc"};
		showResults(word1, word2); // expect true

		String[] word3 = {"a", "cb"};
		String[] word4 = {"ab", "c"};
		showResults(word3, word4); // expect false

		String[] word5 = {"abc", "d", "defg"};
		String[] word6 = {"abcddefg"};
		showResults(word5, word6); // expect true
	}

	private static void showResults(String[] word1, String[] word2) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(word1));
		System.out.println(Arrays.toString(word2));
		boolean rs = arrayStringsAreEqual(word1, word2);
		System.out.printf("RESULT: %b\n", rs);
	}

	// Time: O(n + m), space: O(n + m)
	public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
		int numChars = 0;
		for(String s: word1)
			numChars += s.length();

		for(String s: word2)
			numChars -= s.length();

		if(numChars != 0) return false;
		
		StringBuilder s1 = new StringBuilder();
		StringBuilder s2 = new StringBuilder();

		for(String s: word1)
			s1.append(s);
		for(String s: word2)
			s2.append(s);
		return s1.toString().compareTo(s2.toString()) == 0;
	}
}
