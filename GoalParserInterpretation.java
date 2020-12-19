// leetcode 1678.
public class GoalParserInterpretation {
	public static void main(String[] args) {
		String s1 = "G()(al)";
		showResults(s1); // expect "Goal"

		String s2 = "G()()()()(al)";
		showResults(s2); // expect "Gooooal"

		String s3 = "(al)G(al)()()G";
		showResults(s3); // expect "alGalooG"
	}

	private static void showResults(String command) {
		System.out.println("----ShowResults----");
		System.out.println("INPUT: " + command);
		String rs = interpret(command);
		System.out.printf("OUTPUT: %s\n\n", rs);
	}

	// Time: O(n), space: O(n)
	public static String interpret(String command) {
		if(command == null || command.length() == 0) return "";
		
		StringBuilder s = new StringBuilder();
		
		char[] tokens = command.toCharArray();
		for(int i = 0; i < tokens.length; ) {
			if(tokens[i] == '(') {
				for(int j = i + 1; j < tokens.length; ++j) {
					if(j == i + 1 && tokens[j] == ')') {
						s.append('o');
						i = j + 1;
						break;
					} else {
						if(tokens[j] == ')') {
							i = j + 1;
							break;
						} else s.append(tokens[j]);
					}
				}
			} else s.append(tokens[i++]);
		}

		return s.toString();
	}
}
