public class GenerateAStringWithCharactersThatHaveOddCounts {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		showResults(n);
	}

	private static void showResults(int n) {
		System.out.println("\t----ShowResults----");
		String rs = generateTheString(n);
		System.out.printf("n = %d -> %s\n\n", n, rs);
	}

	// Time: O(n), space: O(n)
	public static String generateTheString(int n) {
		if(n <= 0) return "";

		StringBuilder s = new StringBuilder();
		if(n % 2 == 0) {
			for(int i = 1; i < n; ++i)
				s.append('a');
			s.append('b');
		} else {
			for(int i = 1; i <= n; ++i)
				s.append('b');
		}

		return s.toString();
	}
}
