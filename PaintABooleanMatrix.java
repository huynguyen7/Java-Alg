import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Deque;
import java.util.LinkedList;

// element-prog 19.2
public class PaintABooleanMatrix {
	public static void main(String[] args) {
		int[][] matrix = {
			{1,0,1,0,0,0,1,1,1,1},
			{0,0,1,0,0,1,0,0,1,1},
			{1,1,1,0,0,1,1,0,1,1},
			{0,1,0,1,1,1,1,0,1,0},
			{1,0,1,0,0,0,0,1,0,0},
			{1,0,1,0,0,1,0,1,1,1},
			{0,0,0,0,1,0,1,0,0,1},
			{1,0,1,0,1,0,1,0,0,0},
			{1,0,1,1,0,0,0,1,1,1},
			{0,0,0,0,0,0,0,1,1,0}
		};
		showResults(matrix, 5, 4);
	}

	private static void showResults(int[][] matrix, int x, int y) {
		System.out.println("----ShowResults----");
		System.out.println();
		printMatrix(matrix);
		
		System.out.printf("\nChunk Contains: [%d,%d]\n\n", x, y);
		paintChunkContainsXY(matrix, x, y);
		printMatrix(matrix);
		System.out.println();
	}

	// Using Breadth First Search.
	// V is the number of elements in matrix
	// E is the number of path from an element to another element.
	// Time: O(V+E), space: O(V)
	public static void paintChunkContainsXY(int[][] matrix, int x, int y) {
		Deque<int[]> queue = new LinkedList<>();

		queue.addLast(new int[] {x, y});
		matrix[x][y] = 1;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-- > 0) {
				int[] coordinate = queue.removeFirst();
				x = coordinate[0];
				y = coordinate[1];
				
				if(x > 0 && matrix[x-1][y] == 0) {
					matrix[x-1][y] = 1;
					queue.addLast(new int[] {x-1, y});
				}
				if(x < matrix.length - 1 && matrix[x+1][y] == 0) {
					matrix[x+1][y] = 1;
					queue.addLast(new int[] {x+1, y});
				}
				if(y > 0 && matrix[x][y-1] == 0) {
					matrix[x][y-1] = 1;
					queue.addLast(new int[] {x, y-1});
				}
				if(y < matrix[0].length - 1 && matrix[x][y+1] == 0) {
					matrix[x][y+1] = 1;
					queue.addLast(new int[] {x, y+1});
				}
			}
		}
	}

	private static void printMatrix(int[][] matrix) {
		for(int i = 0; i < matrix.length; ++i) {
			System.out.print("|");
			for(int j = 0; j < matrix[0].length; ++j) {
				if(matrix[i][j] == 0) System.out.print("_|");
				else System.out.print("x|");
			}
			System.out.println();
		}
	}
}

// Constraints:
// matrix[i][j] = 1 => painted, 0 = empty.
// Using int[] instead of boolean[] for easier input
