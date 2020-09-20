import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

// element-prog 13.6
public class FindTheNearestRepeatedEntriesInAnArray {
	public static void main(String[] args) {
		String[] strs1 = {"All", "work", "and", "no", "play", "makes", "for", "no", "work", "no", "fun", "and", "no", "results"};
		showResults(strs1); // expect 2
	}

	private static void showResults(String[] strs) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(strs));
		int rs = findSmallestDistanceOfRepeatedEntries(strs);
		System.out.printf("RESULT: %d\n\n", rs);
	}

	// Time: O(n), space: O(m)
	// n is strs.length
	// m is total of UNIQUE element in strs[]
	public static int findSmallestDistanceOfRepeatedEntries(String[] strs) {
		Map<String, Integer> map = new HashMap<>(); // String - index pair
		int minDistance = Integer.MAX_VALUE;
		
		for(int i = 0; i < strs.length; ++i) {
			String s = strs[i];
			if(!map.containsKey(s)) map.put(s, i);
			else {
				minDistance = Math.min(minDistance, i - map.get(s));
				map.put(s, i);
			}
		}

		return minDistance;
	}
}
