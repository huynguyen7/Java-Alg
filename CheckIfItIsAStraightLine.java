import java.util.Arrays;

// leetcode 1232.
public class CheckIfItIsAStraightLine {
	public static void main(String[] args) {
		int[][] coordinates1 = {{1,2},{2,3},{3,4},{4,5},{5,6},{6,7}};
		showResults(coordinates1); // expect true

		int[][] coordinates2 = {{1,1},{2,2},{3,4},{4,5},{5,6},{7,7}};
		showResults(coordinates2); // expect false

		int[][] coordinates3 = {{0,0},{0,1},{0,-1}};
		showResults(coordinates3); // expect true
	}

	private static void showResults(int[][] coordinates) {
		System.out.println("----ShowResults----");
		for(int[] nums: coordinates)
			System.out.println(Arrays.toString(nums));
		boolean rs = checkStraightLine(coordinates);
		System.out.printf("\nIs Straight Line: %b\n\n", rs);
	}

	// Time: O(n), space: O(1)
	public static boolean checkStraightLine(int[][] coordinates) {
		for(int i = 2; i < coordinates.length; ++i) {
			int x0 = coordinates[i - 2][0];
			int y0 = coordinates[i - 2][1];
			int x1 = coordinates[i - 1][0];
			int y1 = coordinates[i - 1][1];
			int x2 = coordinates[i][0];
			int y2 = coordinates[i][1];

			if(((y1 - y0) * (x2 - x1)) != ((y2 - y1) * (x1 - x0)))
				return false;
		}

		return true;
	}
}
