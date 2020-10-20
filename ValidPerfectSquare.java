// leetcode 367.
public class ValidPerfectSquare {
	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		System.out.printf("%d -> %b\n\n", num, isPerfectSquare(num));
	}

	// n = num
	// Time: O(logn), space: O(1)
	public static boolean isPerfectSquare(int num) {
		long lo = 1, hi = num - 1;
		while(lo < hi) {
			long mid = lo + (hi - lo) / 2;

			if(mid * mid == num) return true;
			else if(mid * mid < num) lo = mid;
			else hi = mid;

			if(mid * mid > num && (mid - 1) * (mid - 1) < num)
				return false;
		}

		return false;
	}
}
// Constraints:
// - num >= 1
