//leetcode 1025.
public class DivisorGame {
	public static void main(String[] args) {
		int a1 = 2;
		showResults(a1); //expect true

		int a2 = 3;
		showResults(a2); //expect false
	}

	private static void showResults(int n) {
		System.out.println("----ShowResults----");
		System.out.printf("%d -> %b\n\n", n, divisorGame(n));
	}

	public static boolean divisorGame(int n) {
		return N % 2 == 0;
	}
}
