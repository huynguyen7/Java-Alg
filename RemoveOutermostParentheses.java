import java.util.LinkedList;
import java.util.Deque;

//leetcode  1021.
public class RemoveOutermostParentheses {
	public static void main(String args[]) {
		String s1 = "(()())(())"; //expect "()()()"
		String s2 = "(()())(())(()(()))"; //expect "()()()()(())"
		String s3 = "()()"; //expect ""

		showResults(s1);
		showResults(s2);
		showResults(s3);
	}

	private static void showResults(String s) {
		System.out.println("----ShowResults----");
		System.out.printf("%s --> %s\n\n", s, removeOuterParentheses(s));
	}
	
    //time: O(n), space: O(n)
    public static String removeOuterParentheses(String s) {
        if(s.length() == 0 || s == null) return s;
		
		StringBuilder rs = new StringBuilder(); //space: O(n)
		Deque<Character> charStack = new LinkedList<>(); //space: O(n)
		Deque<Integer> levelStack = new LinkedList<>(); //space: O(n)
		int level = 0;
		
		char c;
		for(int i = 0; i < s.length(); ++i) { //time: O(n)
			c = s.charAt(i);
			charStack.addFirst(c);
			if(c == ')') levelStack.addFirst(--level);
			else if(c == '(') levelStack.addFirst(level++);
		}

		while(!charStack.isEmpty()) { //time: O(n)
			level = levelStack.removeFirst();
			c = charStack.removeFirst();
			if(level != 0) rs.insert(0, c);
		}

		return rs.toString();
    }
}

//constraints:
//s is a valid parenthes string
//s only contains '(' or ')'
