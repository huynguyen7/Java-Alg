import java.util.Map;
import java.util.HashMap;

// leetcode 1189.
public class MaximumNumberOfBalloons {
	public static void main(String[] args) {
		String s1 = "nlaebolko";
		showResults(s1); // expect 1
		
		String s2 = "loonbalxballpoon";
		showResults(s2); // expect 2
		
		String s3 = "leetcode";
		showResults(s3); // expect 0

		String s4 = "ballonn";
		showResults(s4); // expect 0
	}

	private static void showResults(String text) {
		System.out.println("----ShowResults----");
		System.out.printf("%s -> %d\n\n", text,
						maxNumberOfBalloons(text));
	}

	// Time: O(), space: O()
	public static int maxNumberOfBalloons(String text) {
		String sample = "balloon";
		if(text.length() < sample.length()) return 0;
		
		Map<Character, Integer> requiredCharsFrequency = new HashMap<>();
		for(char c: sample.toCharArray())
			requiredCharsFrequency.put(c, requiredCharsFrequency.getOrDefault(c, 0) + 1);

		Map<Character, Integer> textCharsFrequency = new HashMap<>();
		int rs = 0;
		for(char c: text.toCharArray()) {
			if(requiredCharsFrequency.containsKey(c))
				textCharsFrequency.put(c, textCharsFrequency.getOrDefault(c, 0) + 1);
		}
		
		System.out.println(requiredCharsFrequency.toString());
		System.out.println(textCharsFrequency.toString());
		
		int minOccurrence = Integer.MAX_VALUE;
		
		for(Character key: textCharsFrequency.keySet()) {
			int possibleOccurrences = textCharsFrequency.get(key) / requiredCharsFrequency.get(key);
			minOccurrence = Math.min(minOccurrence, possibleOccurrences);
		}

		return minOccurrence;
	}
}
