import java.util.Arrays;

// leetcode 455.
public class AssignCookies {
	public static void main(String[] args) {
		int[] g1 = {1,2,3};
		int[] s1 = {1,1};
		showResults(g1, s1); // expect 1
		
		int[] g2 = {1,2};
		int[] s2 = {1,2,3};
		showResults(g2, s2); // expect 2

		int[] g3 = {1,3,3,4};
		int[] s3 = {2,2,4};
		showResults(g3, s3); // expect 2

		int[] g4 = {3,3};
		int[] s4 = {1,2};
		showResults(g4, s4); // expect 0

		int[] g5 = {10,9,8,7};
		int[] s5 = {5,6,7,8};
		showResults(g5, s5); // expect 2
	}

	private static void showResults(int[] g, int[] s) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(g));
		System.out.println(Arrays.toString(s));

		int rs = findContentChildren(g, s);
		System.out.printf("Result: %d\n\n", rs);
	}

	// Goal is to maximize the number of your content children.
	// Output the maximum number.

	// Greedy approach.
	// n = g.length, m = s.length
	// Time: O(nlogn + mlogm), space: O(1)
	public static int findContentChildren(int[] g, int[] s) {
		int numChildren = 0;

		Arrays.sort(g);
		Arrays.sort(s);
		
		int i = 0, j = 0;
		while(i < g.length && j < s.length) {
			if(g[i] <= s[j]) {
				numChildren++;
				i++;
				j++;
			} else {
				j++;
			}
		}

		return numChildren;
	}
}
