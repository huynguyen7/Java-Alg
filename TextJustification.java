import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// GOOD EXPLANATION:
// https://www.youtube.com/watch?v=GqXlEbFVTXY
	

// leetcode 68.
public class TextJustification {
	public static void main(String[] args) {
		String[] words1 = {"This", "is", "an", "example", "of", "text", "justification."};
		int maxWidth1 = 16;
		showResults(words1, maxWidth1); // expect
										// {
										//   "This    is    an",
										//   "example  of text",
										//   "justification.  "
										// }

		String[] words2 = {"What","must","be","acknowledgment","shall","be"};
		int maxWidth2 = 16;
		showResults(words2, maxWidth2);
		showResults(words2, maxWidth2); // expect
										// {
										//  "What   must   be",
										//  "acknowledgment  ",
										//  "shall be        "
										// }

		String[] words3 = {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
		int maxWidth3 = 20;
		showResults(words3, maxWidth3); // expect 
										// {
										//  "Science  is  what we",
										//  "understand      well",
										//  "enough to explain to",
										//  "a  computer.  Art is",
										//  "everything  else  we",
										//  "do                  "
										// }

		String[] words4 = {"What","need","an","acknowledgement","shall","be"};
		int maxWidth4 = 15;
		showResults(words4, maxWidth4); // expect
										// {
										// "What   need  an",
										// "acknowledgement",
										// "shall be       "
										// }
	}

	private static void showResults(String[] words, int maxWidth) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(words));
		System.out.printf("Max width: %d\n\n", maxWidth);

		System.out.println("OUTPUT:");
		List<String> rs = fullJustify(words, maxWidth);
		for(String s: rs)
			System.out.printf("'%s'\n", s);
		System.out.println();
	}
	
	// Trick: last line should always be left justify.
	// Using GREEDY to get most words for each line.
	// If lines have only one words => left justify,
	// else => middle justify

	// n is number of lines
	// m is maxWidth
	// Time: O(n*m), space: O(n*m)
	public static List<String> fullJustify(String[] words, int maxWidth) {
		List<String> rs = new ArrayList<>();
		
		int i = 0;
		while(i < words.length) {
			int j = i + 1;
			int lineLength = words[i].length();

			while(j < words.length && 
				(lineLength + words[j].length() + (j - i - 1)) < maxWidth) { // <j-i-1> is the number of spaces we need at least for <j-i> words.
				lineLength += words[j++].length();
			}

			int diff = maxWidth - lineLength; // number of empty chars left.
			int numWordsCurrLine = j - i; 
			
			if(numWordsCurrLine == 1 || j >= words.length) // if number of words is 1 or we are at the last words of the array.
				rs.add(leftJustify(words, diff, i, j));
			else rs.add(middleJustify(words, diff, i, j));

			i = j;
		}
		
		return rs;
	}

	private static String leftJustify(String[] words, int diff, int i, int j) {
		StringBuilder rs = new StringBuilder(words[i]); // init first word
		for(int k = i + 1; k < j; ++k)
			rs.append(" " + words[k]);
		
		int spacesOnRight = diff - (j - i - 1);
		rs.append(" ".repeat(spacesOnRight)); // add spaces to the right side of the string.

		return rs.toString();
	}

	private static String middleJustify(String[] words, int diff, int i, int j) {
		int spacesNeeded = j - i - 1; // number of spaces between each word
		int spaces = diff / spacesNeeded;
		int extraSpaces = diff % spacesNeeded;
		
		StringBuilder rs = new StringBuilder(words[i]);
		for(int k = i + 1; k < j; ++k) {
			int spacesToApply = spaces + (extraSpaces-- > 0 ? 1 : 0);
			rs.append(" ".repeat(spacesToApply) + words[k]);
		}

		return rs.toString();
	}
}
