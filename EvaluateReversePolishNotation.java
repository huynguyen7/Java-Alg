import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Elements of Programming Interview 9.2,
 * leetcode 150.
 */
public class EvaluateReversePolishNotation {
	public static void main(String args[]) {
		String[] tokens1 = {"2","1","+","3","*"};
		showResults(tokens1); //expect 9

		String[] tokens2 = {"4","13","5","/","+"};
		showResults(tokens2); //expect 6
		
		String[] tokens3 = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
		showResults(tokens3); //expect 22
		
		String[] tokens4 = {"18"};
		showResults(tokens4);
	}

	private static void showResults(String[] tokens) {
		if(tokens == null) return;
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(tokens));
		System.out.printf("OUTPUT: %d\n\n", evalRPN(tokens));
	}

	// Time: O(n), space: O(n)
	public static int evalRPN(String[] tokens) {
		if(tokens.length == 0 || tokens == null) return 0;

		Deque<Integer> stack = new LinkedList<>(); //space: O(n)
		int rs = 0;
		
		for(int i = 0; i < tokens.length; ++i) { //time: O(n)
			if(tokens[i].equals("+")) {
				Integer val1 = stack.removeFirst();
				Integer val2 = stack.removeFirst();
				rs = val2 + val1;
				stack.addFirst(new Integer(rs));
			} else if(tokens[i].equals("-")) {
				Integer val1 = stack.removeFirst();
				Integer val2 = stack.removeFirst();
				rs = val2 - val1;
				stack.addFirst(new Integer(rs));
			} else if(tokens[i].equals("*")) {
				Integer val1 = stack.removeFirst();
				Integer val2 = stack.removeFirst();
				rs = val2 * val1;
				stack.addFirst(new Integer(rs));
			} else if(tokens[i].equals("/")) {
				Integer val1 = stack.removeFirst();
				Integer val2 = stack.removeFirst();
				rs = val2 / val1;
				stack.addFirst(new Integer(rs));
			} else { //is digit
				stack.addFirst(Integer.parseInt(tokens[i]));
			}
		}
		
		if(!stack.isEmpty()) return stack.removeFirst();
		
		return rs;
	}
}

//constraints
//valid operators are: +, -, *, /
//division between two integers should truncate -> 0.
//the input expression should not be any divide by zero operation.
