// leetcode 1523.
public class CountOddNumbersInAnIntervalRange {
	public static void main(String[] args) {
		int low = Integer.parseInt(args[0]);
		int high = Integer.parseInt(args[1]);

		System.out.printf("[%d,%d] has %d odd numbers.\n\n", low, high, countOdds(low, high));
	}

	// [1,2] -> {1}
	// [1,3] -> {1,3}
	// [2,9] -> {3,5,7,9}
	// [3,9] -> {3,5,7,9}

	public static int countOdds(int low, int high) {
		if(low == high) return low % 2 != 0 ? 1 : 0;
		if(low % 2 == 0 && high % 2 == 0)
			return (high - low) / 2;
		else return (high - low) / 2 + 1;
	}
}

// Constraints:
// low <= oddValue <= high
