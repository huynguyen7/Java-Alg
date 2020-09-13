//leetcode 657.
public class RobotReturnToOrigin {
	public static void main(String args[]) {
		String s1 = "UD";
		showResults(s1); //expect true

		String s2 = "LL";
		showResults(s2); //expect false
	}

	private static void showResults(String s) {
		System.out.println("----ShowResults----");
		System.out.printf("INPUT: %s\n", s);
		System.out.printf("OUTPUT: %b\n", judgeCircle(s));
	}

	//time: O(n), space: O(1)
	public static boolean judgeCircle(String s) {
		if(s.length() % 2 != 0) return false;
		
		int v = 0, h = 0; //v is vertical, h is horizontal
		for(char c: s.toCharArray()) { //time: O(n)
			if(c == 'U') v++;
			else if(c == 'D') v--;
			else if(c == 'L') h--;
			else h++;
		}

		return v == 0 && h == 0;
	}
}
