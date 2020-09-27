import java.util.List;
import java.util.ArrayList;

// leetcode 131, element-prog 16.7
public class PalindromicPartitioning {
	public static void main(String[] args) {
		String s1 = "0204451881";
		showResults(s1); // expect ["020", "2", "0",
						//			"44", "4", "5",
						//			"88", "8", "1"
						//			"1881"]
	}

	private static void showResults(String s) {
		System.out.println("----ShowResults----");
		System.out.printf("INPUT: %s\n\n", s);
		
		List<List<String>> rs = generatePalidromicDecompositions(s);
		System.out.printf("Size: %d\n", rs.size());
		System.out.println("OUTPUT:");
		for(List<String> a: rs)
			System.out.println(a.toString());
		System.out.println();
	}

	private static List<List<String>> rs;
	
	// Time: O(n * 2^n), space: O(n * 2^n)
	public static List<List<String>> generatePalidromicDecompositions(String s) {
		rs = new ArrayList<>();
		if(s.length() == 0) return rs;

		backtrack(s, new ArrayList<>(), 0); // using StringBuilder class type for holder
		return rs;
	}

	private static void backtrack(String s, List<String> holder, int startIndex) {
		if(startIndex == s.length()) { // goal
			rs.add(new ArrayList<>(holder));
			return;
		}

		for(int j = startIndex + 1; j <= s.length(); ++j) {
			String tmp = s.substring(startIndex, j);
			if(isPalindrome(tmp)) { // shorten the palindrome as possible then adding to holder with dfs
				holder.add(tmp); // make choice
				backtrack(s, holder, j); // explore
				holder.remove(holder.size() - 1); // undo choice
			}
		}
	}

	// Time: O(n), space: O(1)
	private static boolean isPalindrome(String s) {
		if(s.length() == 0) return true;
		
		int i = 0, j = s.length() - 1;
		while(i < j) {
			if(s.charAt(i) == s.charAt(j)) {
				i++;
				j--;
			} else return false;
		}
		
		return true;
	} 
}
