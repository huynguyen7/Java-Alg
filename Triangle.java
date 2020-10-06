import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

// leetcode 120, element-prog 17.8
public class Triangle {
	public static void main(String[] args) {
		int[][] triangle1 = {
			{2},
			{4,4},
			{8,5,6},
			{4,2,6,2},
			{1,5,2,3,4}
		};
		showResults(triangle1); // expect 15
								// 2 -> 4 -> 5 -> 2 -> 2

		int[][] triangle2 = {
			{2},
			{3,4},
			{6,5,7},
			{4,1,8,3}
		};
		showResults(triangle2); // expect 11
								// 2 -> 3 -> 5 -> 1
								// GOOD EXAMPLE TO DRAW RECURSION TREE.

		int[][] triangle3 = {
			{1}
		};
		showResults(triangle3); // expect 1
	}

	private static void showResults(int[][] triangle) {
		System.out.println("----ShowResults----");
		
		List<List<Integer>> input = new ArrayList<>();
		for(int[] row: triangle)
			System.out.println(Arrays.toString(row));
		for(int i = 0; i < triangle.length; ++i) {
			input.add(new ArrayList<>());
			for(int j = 0; j < triangle[i].length; ++j)
				input.get(i).add(triangle[i][j]);
		}
	
		int rs = minimumPathTotalI(input);
		System.out.printf("\nMinimum Path Total: %d\n\n", rs);
	}
	
	// Constraints:
	// The path must start at the top, then descend the trianle
	// continuosly, and end with an entry on the bottom row.
	// The weight of a path is the sum of all entries.
	
	// NOTES: triangle.get(i).get(j) -> triangle.get(i+1).get(j) or triangle.get(i+1).get(j+1)

	// DP approach.
	// bottom-up DP.
	// n = triangle.size()
	// Time: O(n^2), space: O(n)
	public static int minimumPathTotalI(List<List<Integer>> triangle) {
		if(triangle.size() == 0) return 0;
		
		// init first row
		List<Integer> prevRow = new ArrayList<>(triangle.get(0)); // save minimum path sum for previous row 
		for(int i = 1; i < triangle.size(); ++i) {
			List<Integer> currRow = new ArrayList<>(triangle.get(i));
			// compute the first element at currRow
			currRow.set(0, prevRow.get(0) + currRow.get(0));
			
			for(int j = 1; j < currRow.size() - 1; ++j)
				currRow.set(j, currRow.get(j) + Math.min(prevRow.get(j - 1), prevRow.get(j)));
			
			// compute path of the first element at currRow
			currRow.set(currRow.size() - 1, currRow.get(currRow.size() - 1) + prevRow.get(prevRow.size() - 1));

			prevRow = currRow;
		}

		return Collections.min(prevRow); // Time: O(n)
	}

	// recursive approach.
	// n is total elements in triangle.
	// Time: O(2^n), space: O(2^n)
	public static int minimumPathTotalII(List<List<Integer>> triangle) {
		if(triangle.size() == 0) return 0;
		return dfs(triangle, 0, 0);
	}

	private static int dfs(List<List<Integer>> triangle, int currRow, int currCol) {
		if(currRow == triangle.size()) return 0;
		
		int leftPathSum = dfs(triangle, currRow + 1, currCol) + triangle.get(currRow).get(currCol);
		int rightPathSum = dfs(triangle, currRow + 1, currCol + 1) + triangle.get(currRow).get(currCol); 
		
		return Math.min(leftPathSum, rightPathSum);
	}
}
