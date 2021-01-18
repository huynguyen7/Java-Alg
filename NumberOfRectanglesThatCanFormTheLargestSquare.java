import java.util.Arrays;

// leetcode 1725.
public class NumberOfRectanglesThatCanFormTheLargestSquare {
	public static void main(String[] args) {
		int[][] rectangles1 = {{5,8},{3,9},{5,12},{16,5}};
		showResults(rectangles1); // expect 3

		int[][] rectangles2 = {{2,3},{3,7},{4,3},{3,7}};
		showResults(rectangles2); // expect 3
	}

	private static void showResults(int[][] rectangles) {
		System.out.println("\t----ShowResults----");
		for(int[] row: rectangles)
			System.out.println(Arrays.toString(row));
		int rs = countGoodRectangles(rectangles);
		System.out.printf("Number of good rectangles: %d\n\n", rs);
	}

	// Time: O(n*m), space: O(1)
	public static int countGoodRectangles(int[][] rectangles) {
		if(rectangles.length == 0) return 0;

		int count = 0;
		int maxLen = Integer.MIN_VALUE;

		for(int i = 0; i < rectangles.length; ++i)
			maxLen = Math.max(maxLen,
					Math.min(rectangles[i][0], rectangles[i][1]));

		for(int i = 0; i < rectangles.length; ++i) {
			if(Math.min(rectangles[i][0], rectangles[i][1]) == maxLen)
				count++;
		}

		return count;
	}
}
