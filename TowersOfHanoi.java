// element-prog 16.1
public class TowersOfHanoi {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		showResults(n,1,2,3);
	}

	// DEFAULT is 3 towers
	private static void showResults(int n, int start, int using, int end) {
		System.out.println("----ShowResults----");
		System.out.printf("Moving %d rings from tower %d to tower %d\n\n", n, start, end);

		hanoi(n, start, using, end);
		System.out.println();
	}

	// n is the number of total rings
	// Time: O(2^n), space: O()
	public static void hanoi(int n, int start, int using, int end) {
		if(n > 0) {
			hanoi(n - 1, start, end, using);
			if(n != 1) System.out.printf("%d -> %d using %d\n", start, end, using);
			else System.out.printf("%d -> %d\n", start, end);
			hanoi(n - 1, using, start, end);
		}
	}
}

// Good explanation:
// https://www.youtube.com/watch?v=q6RicK1FCUs
