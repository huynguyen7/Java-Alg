import java.util.Set;
import java.util.HashSet;

// leetcode 1704.
public class DetermineIfStringHalvesAreAlike {
	public static void main(String[] args) {
		String s1 = "book";
		showResults(s1); // expect true

		String s2 = "textbook";
		showResults(s2); // expect false

		String s3 = "MerryChristmas";
		showResults(s3); // expect false

		String s4 = "AbCdEfGh";
		showResults(s4); // expect true
	}

	private static void showResults(String s) {
		System.out.println("\t----ShowResults----");
		boolean rs = halvesAreAlike(s);
		System.out.printf("%s -> %b\n\n", s, rs);
	}

	// Time: O(n), space: O(n)
	public static boolean halvesAreAlike(String s) {
		if(s == null || s.length() == 0) return true;

		Set<Character> vowels = new HashSet<>();
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');
		vowels.add('A');
		vowels.add('E');
		vowels.add('I');
		vowels.add('O');
		vowels.add('U');
		
		int countFirstHalf = 0;
		int countSecondHalf = 0;
		
		char[] tokens = s.toCharArray();
		for(int i = 0; i < tokens.length / 2; ++i) {
			if(vowels.contains(tokens[i]))
				countFirstHalf++;
		}

		for(int i = tokens.length / 2; i < tokens.length; ++i) {
			if(vowels.contains(tokens[i]))
				countSecondHalf++;
		}

		return countFirstHalf == countSecondHalf;
	}
}
