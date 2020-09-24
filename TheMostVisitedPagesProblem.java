import java.util.TreeMap;
import java.util.NavigableMap;
import java.util.Arrays;
import java.util.Map;

// element-prog 15.8
public class TheMostVisitedPagesProblem {
	public static void main(String[] args) {
		char[] c1 = {'g','a','t','t','a','a','a','g','t','c','t','a','t'};
		showResults(c1); 
	}

	// NOTE: This problem is only teach how to use TreeMap class
	// TreeMap is a good alternative for HashMap
	// when we want to keep the SORTEDNESS of key
	// and also changing, adding, removing elements in the Map.
	// all operations time complexity: O(logn)
	private static void showResults(char[] cArr) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(cArr));
		
		NavigableMap<Character, Integer> BST = mostFrequentElement(cArr);
		System.out.println(BST.toString());
		System.out.printf("Highest key: %c\n", BST.lastKey());
		
		char mostAppearedElement = BST.firstKey(); // just to init the variable
		int maxFreq = Integer.MIN_VALUE;
		for(Map.Entry<Character, Integer> entry: BST.entrySet()) {
			if(maxFreq < entry.getValue()) {
				maxFreq = entry.getValue();
				mostAppearedElement = entry.getKey();
			}
		}

		System.out.printf("Most appeared element: %c\n\n", mostAppearedElement);
	}

	public static NavigableMap<Character, Integer> mostFrequentElement(char[] cArr) {
		NavigableMap<Character, Integer> BST = new TreeMap<>(); // sorted based on natural order of characters
		for(char c: cArr)
			BST.put(c, BST.getOrDefault(c, 0) + 1);

		return BST;
	}
}
