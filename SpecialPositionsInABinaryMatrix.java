import java.util.Arrays;

// leetcode 1582.
public class SpecialPositionsInABinaryMatrix {
	public static void main(String[] args) {
		int[][] matrix1 = {
			{1,0,0},
			{0,0,1},
			{1,0,0}
		};
		showResults(matrix1); // expect 1

		int[][] matrix2 = {
			{1,0,0},
			{0,1,0},
			{0,0,1}
		};
		showResults(matrix2); // expect 3

		int[][] matrix3 = {
			{0,0,0,1},
			{1,0,0,0},
			{0,1,1,0},
			{0,0,0,0}
		};
		showResults(matrix3); // expect 2

		int[][] matrix4 = {
			{0,0,0,0,0},
			{1,0,0,0,0},
			{0,1,0,0,0},
			{0,0,1,0,0},
			{0,0,0,1,1}
		};
		showResults(matrix4); // expect 3

		int[][] matrix5 = {
			{0,0},
			{0,0},
			{1,0}
		};
		showResults(matrix5); // expect 1

		int[][] matrix6 = {
			{0,0,0,0,0,1,0,0},
			{0,0,0,0,1,0,0,1},
			{0,0,0,0,1,0,0,0},
			{1,0,0,0,1,0,0,0},
			{0,0,1,1,0,0,0,0}
		};
		showResults(matrix6); // expect 1
	}

	private static void showResults(int[][] matrix) {
		System.out.println("----ShowResults----");
		for(int[] nums: matrix)
			System.out.println(Arrays.toString(nums));
		System.out.println();
		
		int rs = numSpecial(matrix);
		System.out.printf("Num Special = %d\n\n", rs);
	}

	// Time: O(n^2), space: O(1)
	public static int numSpecial(int[][] matrix) {
		if(matrix == null || matrix.length == 0) return 0;

		int rs = 0;
		for(int i = 0; i < matrix.length; ++i) {
			for(int j = 0; j < matrix[0].length; ++j) {
				if(matrix[i][j] == 1) {
					boolean found = true;
					for(int k = j + 1; k < matrix[0].length; ++k) {
						if(matrix[i][k] == 1) {
							found = false;
							break;
						}
					}
					for(int k = 0; k < matrix.length; ++k) {
						if(matrix[k][j] == 1 && k != i) {
							found = false;
							break;
						}
					}

					if(found) rs++;
					break;
				}
			}
		}

		return rs;
	}
}
