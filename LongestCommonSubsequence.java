// leetcode 1143.
public class LongestCommonSubsequence {
	public static void main(String[] args) {
		showResults("abcde", "ace"); // expect 3
		showResults("abcde", "aec"); // expect 2
		showResults("abcdgh", "aedfhr"); // expect 3
		showResults("aggtab", "gxtxayb"); // expect 4
		showResults("abc", "abc"); // expect 3
		showResults("abc", "cba"); // expect 1
		showResults("abc", "def"); // expect 0
		showResults("abc", "aec"); // expect 2
									// GOOD EXAMPLE TO DRAW RECURSION TREE.
	}

	private static void showResults(String text1, String text2) {
		System.out.println("----ShowResults----");
		System.out.printf("Text1: %s\nText2: %s\n", text1, text2);
		int rs = longestCommonSubsequenceI(text1, text2);
		System.out.printf("Longest Common Subsequence: %d\n\n", rs);
	}

	// GOOD EXPLANATION:
	// https://www.youtube.com/watch?v=ASoaQq66foQ&t=123s

	// DP approach.
	// bottom-up DP.
	// n = text1.length(), m = text2.length().
	// Time: O(n*m), space: O(n*m)
	public static int longestCommonSubsequenceI(String text1, String text2) {
		int[][] cache = new int[text1.length() + 1][text2.length() + 1];

		for(int i = 1; i < cache.length; ++i) {
			for(int j = 1; j < cache[0].length; ++j) {
				if(text1.charAt(i - 1) == text2.charAt(j - 1))
					cache[i][j] = cache[i - 1][j - 1] + 1;
				else {
					int lengthSubsequenceIfOmitLastCharText1 = cache[i - 1][j];
					int lengthSubsequenceIfOmitLastCharText2 = cache[i][j - 1];
					cache[i][j] = Math.max(lengthSubsequenceIfOmitLastCharText1,
											lengthSubsequenceIfOmitLastCharText2);
				}
			}
		}
		
		return cache[text1.length()][text2.length()];
	}
	
	// Recursive approach.
	// n = text1.length() + text2.length().
	// Time: O(2^n), space: O(2^n)
	public static int longestCommonSubsequenceII(String text1, String text2) {
		return dfs(text1, text2);
	}

	private static int dfs(String text1, String text2) {
		if(text1.length() == 0 || text2.length() == 0) return 0; // base case.
		else if(text1.charAt(text1.length() - 1) == text2.charAt(text2.length() - 1)) // if last chars match.
			return dfs(text1.substring(0, text1.length() - 1), text2.substring(0, text2.length() - 1)) + 1;
		else {
			int lengthSubsequenceIfOmitLastCharText1 = dfs(text1.substring(0, text1.length() - 1), text2);
			int lengthSubsequenceIfOmitLastCharText2 = dfs(text1, text2.substring(0, text2.length() - 1));
			
			return Math.max(lengthSubsequenceIfOmitLastCharText1,
							lengthSubsequenceIfOmitLastCharText2);
		}
	}
}

// Constraints:
// 1 <= text.length
