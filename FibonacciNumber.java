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

    // best approach, only replacing elements in cache without adding new ones.
    // DP approach with iterations
    // bottom-up DP
    // Time: O(n), space: O(1)
    public static int fibI(int n) {
        if(n <= 1) return n;
        
        cache = new HashMap<>();
        cache.put(0,0); // init base case
        cache.put(1,1); // init base case
        
        int counter = 2;
        while(counter <= n) {
            cache.put(counter % 2, cache.get((counter - 1) % 2) + cache.get((counter - 2) % 2));
            counter++;
        }
        
        return cache.get(n % 2);
    }
    
    
    // better approach
    // DP approach with iterations
    // bottom-up DP
    // Time: O(n), space: O(n)
    public static int fibII(int n) {
        if(n <= 1) return n;
        
        cache = new HashMap<>();
        cache.put(0,0); // init base case
        cache.put(1,1); // init base case
        
        int counter = 2;
        while(counter <= n) {
            cache.put(counter, cache.get(counter - 1) + cache.get(counter - 2));
            counter++;
        }
        
        return cache.get(n);
    }
    
    // DP approach with recursion => This cost more space for recursive calls
    // top-down DP
    // Time: O(n), space: O(n)
    public static int fibIII(int n) {
        cache = new HashMap<>();
        return dp(n);
    }
    
    private static int dp(int n) {
        if(n <= 1) return n;
        else if(!cache.containsKey(n))
            cache.put(n, dp(n - 1) + dp(n - 2));
        return cache.get(n);
    }
    
    //  recursive approach
    // Time: O(2^n), space: O(n)
    public static int fibIV(int n) {
        if(n <= 1) return n;
        else return fibIV(n - 1) + fibIV(n - 2);
    }
}
