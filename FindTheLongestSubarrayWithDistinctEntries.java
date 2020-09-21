import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

// element-prog 13.9
public class FindTheLongestSubarrayWithDistinctEntries {
	public static void main(String[] args) {
		char[] cArr1 = {'f','s','f','e','t','w','e','n','w','e'};
		showResults(cArr1); // expect 5 -> [s,f,e,t,w]
		
		char[] cArr2 = {'a','b','c'};
		showResults(cArr2); // expect 3

		char[] cArr3 = new char[0];
		showResults(cArr3); // expect 0

		char[] cArr4 = {'a','a','a'};
		showResults(cArr4); // expect 1
	}

	private static void showResults(char[] cArr) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(cArr));
		int rs = find(cArr);
		System.out.printf("Longest length of subarray with distince element: %d\n\n", rs);
	}

	// Time: O(), space: O()
	public static int find(char[] cArr) {
		Set<Character> marked = new HashSet<>();
		int maxLength = 0;
		int startIndex = 0;
		for(int i = 0; i < cArr.length; ++i) {
			if(!marked.contains(cArr[i])) {
				marked.add(cArr[i]);
			} else {
				maxLength = Math.max(maxLength, marked.size());
				marked.remove(cArr[startIndex]);
				startIndex = i;
				marked.add(cArr[i]);
			}
		}

		maxLength = Math.max(maxLength, marked.size());

		return maxLength;
	}
}
