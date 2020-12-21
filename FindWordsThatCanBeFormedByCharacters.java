import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

// leetcode 1160.
public class FindWordsThatCanBeFormedByCharacters {
	public static void main(String[] args) {
		String[] words1 = {"cat","bt","hat","tree"};
		String chars1 = "atach";
		showResults(words1, chars1); // expect 6

		String[] words2 = {"hello","world","leetcode"};
		String chars2 = "welldonehoneyr";
		showResults(words2, chars2); // expect 10
	}

	private static void showResults(String[] words, String chars) {
		System.out.println("----ShowResults----");
		System.out.println("INPUT WORDS: " + Arrays.toString(words));
		System.out.println("INPUT CHARS: " + chars);
		int rs = countCharacters(words, chars);
		System.out.printf("-> OUTPUT: %d\n\n", rs);
	}

	// n = words.length, k = chars.length(), m = each word.length() in words
	// Time: O(k + n*mlogm), space: O(k + n*m)
	public static int countCharacters(String[] words, String chars) {
		if(words.length == 0 || chars.length() == 0) return 0;
		int count = 0;

		Map<Character, Integer> map = new HashMap<>(); // <char-freq> key value pairs.
		for(char c: chars.toCharArray()) // time: O(k), space: O(k)
			map.put(c, map.getOrDefault(c, 0) + 1);
		
		for(String word: words) { // time: O(n)
			char[] tmp = word.toCharArray();
			Arrays.sort(tmp); // time: O(mlogm)
			boolean flag = true;

			for(int i = 0; i < tmp.length;) {
				if(!map.containsKey(tmp[i])) {
					flag = false;
					break;
				} else {
					int j;
					for(j = i + 1; j < tmp.length && tmp[j] == tmp[i]; ++j);
					
					int numCharsMatch = j - i;

					if(numCharsMatch > map.get(tmp[i])) {
						flag = false;
						break;
					} else i = j;
				}
			}

			if(flag) count += tmp.length;
		}

		return count;
	}
}

// Constraints:
// - All strings contain lowercase English letters only.
