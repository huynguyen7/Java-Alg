import java.util.Deque;
import java.util.LinkedList;

//leetcode 921.
public class MinimumAddToMakeParenthesesValid {
	public static void main(String args[]) {
		String s1 = "())"; //expect 1
		String s2 = "((("; //expect 3
		String s3 = "()"; //expect 0
		String s4 = "()))(("; //expect 4

		showResults(s1);
		showResults(s2);
		showResults(s3);
		showResults(s4);
	}
	
	private static void showResults(String s) {
		System.out.println("----ShowResults----");
		System.out.printf("%s --> %d\n\n", s, minAddToMakeValid(s));
	}

	//time: O(n), space: O(n)
	public static int minAddToMakeValid(String s) {
		if(s.length() == 0 || s == null) return 0;
		
		int count = 0;
		Deque<Character> stack = new LinkedList<>();
		
		boolean check;
		char c;
		for(int i = 0; i < s.length(); ++i) {
			c = s.charAt(i);
			check = false;
			if(c == '(') {
				stack.addFirst(c);
				count++;
			} else {
				while(!stack.isEmpty()) {
					if(stack.removeFirst() == '(') {
						check = true;
						count--;
						break;
					}
				}
				if(!check) count++;
			}
		}

		

		return count;
	}
}
