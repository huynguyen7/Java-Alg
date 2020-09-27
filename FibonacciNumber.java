import java.util.Map;
import java.util.HashMap;

// leetcode 509, element-prog 17 bootcamp.
public class FibonacciNumber {
	public static void main(String[] args) {
		showResults(0); // expect 0
		showResults(1); // expect 1
		showResults(2); // expect 1
		showResults(3); // expect 2
		showResults(4); // expect 3
		showResults(5); // expect 5
		showResults(6); // expect 8
	}

	private static void showResults(int n) {
		System.out.printf("F(%d) = %d\n", n, fibI(n));
	}

	private static Map<Integer, Integer> cache;

	// DP approach
	// Time: (n), space: O(n)
	public static int fibI(int n) {
		cache = new HashMap<>();
		return dp(n);
	}

	private static int dp(int n) {
		if(n <= 1) return n;
		else if(!cache.containsKey(n))
			cache.put(n, dp(n-1) + dp(n-2));
		return cache.get(n);
	}

	// recursive approach
	// cost space complexity for the call stack.
	// Time: O(2^n), space: O(n)
	public static int fibII(int n) {
		if(n <= 1) return n;
		
		return fibII(n-1) + fibII(n-2);
	}
}
