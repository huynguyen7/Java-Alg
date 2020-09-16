//leetcode 392.
public class IsSubsequence {
	public static void main(String args[]) {
		String s1 = "abc";
		String t1 = "ahbgdc";
		showResults(s1, t1); // expect true

		String s2 = "axc";
		String t2 = "aahbgdc";
		showResults(s2, t2); // expect false

		String s3 = "aaaaaa";
		String t3 = "bbaaaa";
		showResults(s3, t3); // expect false
	}

	private static void showResults(String s, String t) {
		System.out.println("----ShowResults----");
		System.out.printf("s : %s, t: %s\n", s, t);
		System.out.printf("Result: %b\n\n", isSubsequence(s, t));
	}

	//time: O(n), space: O(1)
	public static boolean isSubsequence(String s, String t) {
		int countMatch = 0;
		int i = 0;
		for (char c : s.toCharArray()) {
			if (i == t.length())
				break;
			for (; i < t.length(); ++i) {
				if (t.charAt(i) == c) {
					countMatch++;
					i++;
					break;
				}
			}
		}

		System.out.println(countMatch);
		return countMatch == s.length();
	}
}
