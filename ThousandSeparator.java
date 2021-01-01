import java.util.Deque;
import java.util.LinkedList;

// leetcode 1556.
public class ThousandSeparator {
	public static void main(String[] args) {
		int n1 = 987;
		showResults(n1); // expect "987"

		int n2 = 1234;
		showResults(n2); // expect "1.234"

		int n3 = 123456789;
		showResults(n3); // expect "123.456.789"

		int n4 = 0;
		showResults(n4); // expect "0"
	}

	private static void showResults(int n) {
		System.out.println("----ShowResults----");
		String rs = thousandSeparator(n);
		System.out.printf("%d -> %s\n\n", n, rs);
	}

	// Time: O(n), space: O(n)
	public static String thousandSeparator(int n) {
		if(n == 0) return "0";

		StringBuilder s = new StringBuilder();
		Deque<Character> stack = new LinkedList<>();

		int numDigits = (int) Math.log10(n) + 1;
		int currDigits = 0;
		for(int i = 0; i < numDigits; ++i) {
			int digit = n % 10;
			n /= 10;
			
			if(currDigits != 0 && currDigits % 3 == 0)
				stack.addFirst('.');

			stack.addFirst((char) (digit + '0'));
			currDigits++;
		}

		while(!stack.isEmpty())
			s.append(stack.removeFirst());

		return s.toString();
	}
}
