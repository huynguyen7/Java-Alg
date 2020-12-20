// leetcode 1688.
public class CountOfMatchesInTournament {
	public static void main(String[] args) {
		showResults(7); // expect 6.
		showResults(14); // expect 13.
	}

	private static void showResults(int n) {
		System.out.println("----ShowResults----");
		System.out.printf("n = %d => OUTPUT = %d\n\n", n, numberOfMatches(n));
	}

	// Time: O(n), space: O(n)
	public static int numberOfMatches(int n) {
		if(n == 1) return 0;
		else {
			boolean numTeamsIsEven = n % 2 == 0 ? true : false;
			
			if(numTeamsIsEven) {
				int numMatches = n / 2;
				return numberOfMatches(n / 2) + numMatches;
			} else {
				int numMatches = (n - 1) / 2;
				return numberOfMatches(((n - 1) / 2) + 1) + numMatches;
			}
		}
	}
}
