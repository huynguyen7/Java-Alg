import java.util.List;
import java.util.ArrayList;

// leetcode 22, element-prog 16.6
public class GenerateParentheses {
	public static void main(String[] args) {
		int n1 = 3;
		showResults(n1); // expect [
								//	  "((()))",
								//	  "(()())",
								//	  "(())()",
								//	  "()(())",
								//	  "()()()" ]
								
	}

	private static void showResults(int n) {
		System.out.println("----ShowResults----");
		System.out.printf("n: %d\n\n", n);
		
		List<String> rs = generateParenthesis(n);
		System.out.printf("Total of generated parentheses: %d\n", rs.size());
		for(String s: rs)
			System.out.println(s);
		System.out.println();
	}

	private static List<String> rs;
	
	// Time: O(4^n / (sqrt(n))), space: O(4^n / (sqrt(n)))
	public static List<String> generateParenthesis(int n) {
		rs = new ArrayList<>();
		if(n == 0) return rs;

		backtrack(n, 0, 0, "");
		return rs;
	}

	private static void backtrack(int n, int open, int close, String holder) {
		if(holder.length() == n * 2) { // goal
			rs.add(holder);
			return;
		}

		if(open < n) backtrack(n, open + 1, close, holder + "(");
		if(close < open) backtrack(n, open, close + 1, holder + ")");
	}
}
