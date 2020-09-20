import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// leetcode 49.
public class GroupAnagrams {
	public static void main(String args[]) {
		String[] strs1 = {"eat","tea","tan","ate","nat","bat"};
		showResults(strs1); // expect [["bat"],["nat","tan"],["ate","eat","tea"]]
		
		String[] strs2 = {""};
		showResults(strs2); // expect [[""]]
		
		String[] strs3 = {"a"};
		showResults(strs3); // expect [["a"]]
		
		String[] strs4 = {"logarithmmic","algorithmic","acghiilmort"};
		showResults(strs4);
	}

	private static void showResults(String[] strs) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(strs));
		System.out.println("OUTPUT");
		
		List<List<String>> rs = groupAnagrams(strs);
		for(List<String> l : rs)
			System.out.println(l.toString());
		System.out.println();
	}

	// better approach
	// Time: O(nmlogm), space: O(n)
	public static List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> sortedStringToAnagrams = new HashMap<>();
		for(String s: strs) {
			// Sort the string, uses it as key, then append
			// the original string as another value in hash table
			char[] sortedCharArray = s.toCharArray();
			Arrays.sort(sortedCharArray);
			String sortedString = new String(sortedCharArray);
			
			if(!sortedStringToAnagrams.containsKey(sortedString))
				sortedStringToAnagrams.put(sortedString, new ArrayList<String>());
			
			sortedStringToAnagrams.get(sortedString).add(s);
		}
		
		List<List<String>> rs = new ArrayList<>();
		for(Map.Entry<String, List<String>> entry: sortedStringToAnagrams.entrySet())
			rs.add(entry.getValue());
		

		return rs;
	}

	// naive approach
	// Time: O(n), space: O(m), m is total characters in the arrays
	// since it is calling validateAnagram() function
	// thus this create O(m) space for hashMap
	public static List<List<String>> groupAnagramsII(String[] strs) {
		List<List<String>> rs = new ArrayList<>();
		List<String> holder;

		for(int i = 0; i < strs.length; ++i) {
			String s = strs[i];
			
			if(!rs.isEmpty()) {
				boolean check = false;
				for(int j = 0; j < rs.size(); ++j) {
					check = validateAnagram(s, rs.get(j).get(0));
					if(check) {
						rs.get(j).add(s);
						break;
					}
				}
				
				if(!check) {
					holder = new ArrayList<>();
					holder.add(s);
					rs.add(holder);
				}
			} else {
				holder = new ArrayList<>();
				holder.add(s);
				rs.add(holder);
			}
		}
		
		return rs;
	}

	// Time: O(n), space: O(n)
	private static boolean validateAnagram(String s, String t) {
		if(s.length() != t.length()) return false;
		Map<Character, Integer> map = new HashMap<>();
		
		for(char c: s.toCharArray())
			map.put(c, map.getOrDefault(c, 0) + 1);
		for(char c: t.toCharArray()) {
			if(!map.containsKey(c)) return false;
			else map.put(c, map.get(c) - 1);
		}
		
		for(Integer i: map.values())
			if(i != 0) return false;

		return true;
	}
}
