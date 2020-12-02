import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class FizzBuzz {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		showResults(n);
	}

	private static void showResults(int n) {
		System.out.println("----ShowResults----");
		System.out.printf("INPUT N = %d\n\n", n);
		List<String> rs = fizzBuzz(n);
		System.out.println(rs.toString());
	}

	// Time: O(n), space: O(n)
	public static List<String> fizzBuzz(int n) {
		if(n < 1) return Collections.emptyList();
		
		final String FIZZ = "Fizz";
		final String BUZZ = "Buzz";
		final String FIZZ_BUZZ = "FizzBuzz";
		
		List<String> rs = new ArrayList<>();
		for(int i = 1; i <= n; ++i) {
			if(i % 3 == 0 && i % 5 != 0)
				rs.add(FIZZ);
			else if(i % 3 != 0 && i % 5 == 0)
				rs.add(BUZZ);
			else if(i % 3 == 0 && i % 5 == 0)
				rs.add(FIZZ_BUZZ);
			else rs.add(Integer.toString(i));
		}

		return rs;
	}
}
