import java.util.Deque;
import java.util.LinkedList;

//leetcode 20, element-prog 9.3
public class ValidParentheses {
	public static void main(String args[]) {
		String s1 = "()"; //expect true
		String s2 = "()[]{}"; //expect true
		String s3 = "{]"; //expect false
		String s4 = "([)]"; //expect false
		String s5 = "{[]}"; //expect true
		String s6 = "{[][]{()}}"; //expect true
		
		showResults(s1);
		showResults(s2);
		showResults(s3);
		showResults(s4);
		showResults(s5);
		showResults(s6);
	}

	private static void showResults(String s) {
		System.out.printf("%s -> %b\n\n", s, isValid(s));
	}

	//time: O(n), space: O(n); n is s.length()
	public static boolean isValid(String s) {
		if(s.length() == 0 || s.length() % 2 != 0) return true;
		
		Deque<Character> stack = new LinkedList<>();	
		
		char tmp;
		for(int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if(c == '(' || c == '[' || c == '{') stack.addFirst(c);
			else {
				switch(c) {
					case ')':
						if(stack.removeFirst() != '(') return false;
						break;
					case ']':
						if(stack.removeFirst() != '[') return false;
						break;
					case '}':
						if(stack.removeFirst() != '{') return false;
						break;
				}
			}
		}

		return true;
	}
}
