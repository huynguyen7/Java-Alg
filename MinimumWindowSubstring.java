import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

// leetcode 76, element-prog 13.7
public class MinimumWindowSubstring {
	public static void main(String[] args) {
		String s1 = "ADOBECODEBANC";
		String t1 = "ABC";
		showResults(s1, t1); // expect "BANC"
		
		String s2 = "ab";
		String t2 = "a";
		showResults(s2, t2); // expect "a"

		String s3 = "whoopiepiesmakemyscalegroan";
		String t3 = "roam";
		showResults(s3, t3); // expect "myscalegro"

		String s4 = "a";
		String t4 = "b";
		showResults(s4, t4); // expect ""
		
		String s5 = "a";
		String t5 = "ab";
		showResults(s5, t5); // expect "" 

		String s6 = "coffeeteabiscuits";
		String t6 = "cake";
		showResults(s6, t6); // expect ""
		
		String s7 = "azjskfzts";
		String t7 = "sz";

		showResults(s7, t7); // expect "zjs"
		
		String s8 = "aaa";
		String t8 = "aaa";
		showResults(s8, t8); // expect "aaa"
	}	
	
	private static void showResults(String s, String t) {
		System.out.println("----ShowResults----");
		System.out.printf("S = %s, T = %s\n", s, t);
		System.out.printf("Results: %s\n\n", minWindowI(s, t));
	}
	
	// best approach
	// Time: O(n + m), space: O(n + m)
	public static String minWindowI(String s, String t) {
		// char-freq pair
		Map<Character, Integer> requiredCharsFrequency = new HashMap<>();
		for(char c: t.toCharArray())
			requiredCharsFrequency.put(c, requiredCharsFrequency.getOrDefault(c, 0) + 1);

		// char-freq pair
		Map<Character, Integer> windowCharsFrequency = new HashMap<>();
		
		int left = 0, right = 0;
		int totalCharsRequired = requiredCharsFrequency.size();
		int totalCharsInWindowThatMatch = 0;

		int minWindowLength = Integer.MAX_VALUE;
		String minWindow = "";

		while(right < s.length()) { // running right pointer
			char rightChar = s.charAt(right);
			windowCharsFrequency.put(rightChar, windowCharsFrequency.getOrDefault(rightChar, 0) + 1);
			
			// Check if right char is in t string
			if(requiredCharsFrequency.containsKey(rightChar)) {
				if(Integer.compare(requiredCharsFrequency.get(rightChar),
					windowCharsFrequency.get(rightChar)) == 0) // have the same frequency for rightChar
					totalCharsInWindowThatMatch++;
			}
			
			while(totalCharsInWindowThatMatch == totalCharsRequired) { // running left pointer
				char leftChar = s.charAt(left);
				int windowSize = right - left + 1;
				if(windowSize < minWindowLength) {
					minWindow = s.substring(left, right + 1);
					minWindowLength = windowSize;
				}
				
				// shorten the string
				windowCharsFrequency.put(leftChar, windowCharsFrequency.get(leftChar) - 1);
				if(requiredCharsFrequency.containsKey(leftChar)) {
					if(Integer.compare(windowCharsFrequency.get(leftChar), requiredCharsFrequency.get(leftChar)) < 0) // comparing freq, if smaller, then the char fail the requirement
						totalCharsInWindowThatMatch--;
				}

				left++;
			}

			right++;
		}

		return minWindow;
	}

	// Time: O((n*m)^3), space: O(n + m)
	// n is total substring
	// m is number of UNIQUE chars
	public static String minWindowII(String s, String t) {
		int n = s.length();
		
		int minWindowLengthSeenSoFar = Integer.MAX_VALUE;
		String minWindow = "";
		
		for(int left = 0; left < n; ++left) {
			for(int right = left; right < n; ++right) {
				String subString = s.substring(left, right + 1);

				// Check if substring is valid (contains all the characters of t string)
				// Time: O((n*m)^3), since it calling its helper method 
				boolean windowContainsAllChars = stringContainsAllCharacters(subString, t);
				if(windowContainsAllChars && subString.length() < minWindowLengthSeenSoFar) {
					minWindowLengthSeenSoFar = subString.length();
					minWindow = subString;
				}
			}
		}

		return minWindow;
	}

	// helper method for minWindowII
	private static boolean stringContainsAllCharacters(String s, String t) {
		Map<Character, Integer> requiredCharsFrequency = new HashMap<>(); // space: O(m^3)
		for(char c: t.toCharArray())
			requiredCharsFrequency.put(c, requiredCharsFrequency.getOrDefault(c, 0) + 1);
		
		for(int i = 0; i < s.length(); ++i) {
			char currChar = s.charAt(i);
			if(requiredCharsFrequency.containsKey(currChar)) {
				int count = requiredCharsFrequency.get(currChar) - 1;
				if(count == 0) requiredCharsFrequency.remove(currChar);
				else requiredCharsFrequency.put(currChar, count);
			}
		}
	
		return requiredCharsFrequency.isEmpty();	
	}
}
