import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// leetcode 72, element-prog 17.2
public class EditDistance {
	public static void main(String[] args) {
		String a1 = "horse";
		String b1 = "ros";
		showResults(a1, b1); // expect 3
							// replace('h', 'r')
							// remove('r')
							// remove('e')

		String a2 = "intention";
		String b2 = "execution";
		showResults(a2, b2); // expect 5
							// replace('i','e')
							// replace('n','x')
							// replace('n','c')
							// insert('u')

		String a3 = "ros";
		String b3 = "o";
		showResults(a3, b3); // expect 2, GOOD EXAMPLE TO DRAW RECURSION MAP.
							// remove('r')
							// remove('o')
	}

	private static void showResults(String s1, String s2) {
		System.out.println("----ShowResults----");
		System.out.printf("%s, %s -> %d\n\n", s1, s2, minDistanceI(s1, s2));
	}

	// Levenshtein Distance is a measure of dissimilarity between
	// two Strings. In short, the distance measures the minimum
	// number of character edits required to transform word1 to word2.

	// Constraints:
	// - Only 3 operations permitted on word1:
	// + Replace a char.
	// + Insert a char.
	// + Delete a char.

	// DP TABLE:
	// +----------------+---------------+
	// | 	 REPLACE 	| 	INSERT 		|
	// +----------------+---------------+
	// | 	 DELETE 	| 	YOUAREHERE 	|
	// +----------------+---------------+

	// DP approach
	// bottom-up DP
	// Time: O(a*b), space: O(a*b)
	// a = word1.length(), b = word2.length()
	public static int minDistanceI(String word1, String word2) {
		int[][] minNumOfEdits = new int[word2.length() + 1][word1.length() + 1]; // + 1 since we need a place for empty string as default
		
		for(int i = 0; i < minNumOfEdits.length; ++i) // init base cases
			minNumOfEdits[i][0] = i;
		for(int j = 0; j < minNumOfEdits[0].length; ++j) // init base cases
			minNumOfEdits[0][j] = j;

		for(int i = 1; i < minNumOfEdits.length; ++i) {
			for(int j = 1; j < minNumOfEdits[0].length; ++j) {
				if(word1.charAt(j - 1) != word2.charAt(i - 1)) {
					int replacement = minNumOfEdits[i - 1][j - 1];
					int insertion = minNumOfEdits[i - 1][j];
					int deletion = minNumOfEdits[i][j - 1];
					minNumOfEdits[i][j] = Math.min(replacement, Math.min(insertion, deletion)) + 1;
				} else { // if two chars are the same, use solved subproblem not using the char,
						// which means the subproblem of replament that is not using the char.
					int replacement = minNumOfEdits[i - 1][j - 1];
					minNumOfEdits[i][j] = replacement;
				}
			}
		}

		return minNumOfEdits[word2.length()][word1.length()];
	}

	// recursive approach
	// At each step, we have 3 cursive calls -> O(3^n) time complexity.
	// n = word1.length()
	// Time: O(3^n), space: O(3^n)
	public static int minDistanceII(String word1, String word2) {
		// if a word is empty, which means another word needs its <word.length()> number of
		// character edits for DELETION in order to match to empty String. Thus, return word.length() as num of char needs edits.
		if(word1.isEmpty()) return word2.length();
		if(word2.isEmpty()) return word1.length();
		
		int replacement = minDistanceII(word1.substring(1), word2.substring(1)) + costOfReplacement(word1.charAt(0), word2.charAt(0));
		int insertion = minDistanceII(word1, word2.substring(1)) + 1;
		int deletion = minDistanceII(word1.substring(1), word2) + 1;

		return Math.min(replacement, Math.min(insertion, deletion));
	}

	private static int costOfReplacement(char a, char b) {
		return a == b ? 0 : 1; // if they are the same char, no need to replace -> return 0
	}
}

// NOTES: NEEDS TO DRAW RECURSION MAP TO FULLY UNDERSTAND.
// Good explanation:
// https://www.youtube.com/watch?v=MiqoA-yF-0M
// https://www.baeldung.com/java-levenshtein-distance
