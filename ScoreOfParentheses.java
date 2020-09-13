import java.util.LinkedList;
import java.util.Deque;

//leetcode 856.
public class ScoreOfParentheses {
	public static void main(String args[]) {
		String s1 = "()"; //expect 1
		String s2 = "(())"; //expect 2
		String s3 = "(()(()))"; //expect 6
		String s4 = "(()())"; //expect 4
		String s5 = "(((())))";
		
		showResults(s1);
		showResults(s2);
		showResults(s3);
		showResults(s4);
		showResults(s5);
	}
	
	private static void showResults(String s) {
		System.out.println("----ShowResults----");
		System.out.printf("%s --> %d\n\n", s, scoreOfParentheses(s));
	}

	//time: O(n), space: O(n)
	public static int scoreOfParentheses(String s) {
		if(s.length() == 0 || s == null) return 0;
		
		int score = 0;
		Deque<Integer> stack = new LinkedList<>(); //space: O(n)
		
		for(char c: s.toCharArray()) { //time: O(n), space: O(n)
			if(c == '(') stack.addFirst(-1);
			else {
				if(stack.peekFirst() == -1) { //-1 means '('
					stack.removeFirst();
					stack.addFirst(1);
				} else {
					int tmpScore = 0;
					while(stack.peekFirst() != -1)
						tmpScore += stack.removeFirst();
					stack.removeFirst();
					stack.addFirst(tmpScore * 2);
				}
			}
		}
		
		while(!stack.isEmpty()) score += stack.removeFirst(); //time: O(n)
		
		return score;
	}

	
}

//constraints
//String s only contains '(' and ')'
