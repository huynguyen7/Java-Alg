import java.util.Set;
import java.util.HashSet;

//leetcode 771.
public class JewelsAndStones {
	public static void main(String args[]) {
		String j1 = "aA";
		String s1 = "aAAbbbb";
		showResults(j1, s1); //expect 3

		String j2 = "z";
		String s2 = "ZZ";
		showResults(j2, s2); //expect 0
	}
	
	private static void showResults(String j, String s) {
		System.out.println("----ShowResults----");
		System.out.println("Jewels: " + j);
		System.out.println("Stone: " + s);
		System.out.printf("Results: %d\n\n", numJewelsInStones(j, s));
	}
	
	//time: O(n), space: O(n)
	public static int numJewelsInStones(String j, String s) {
		Set<Character> set = new HashSet<>();
		for(char c: j.toCharArray())
			set.add(c);
		
		int count = 0;
		for(char c: s.toCharArray())
			if(set.contains(c))
				count++;

		return count;
	}
}

//constraints:
//String s and j only contains letters.
//The characters of String j are distinct.
