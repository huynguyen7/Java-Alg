import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// leetcode 490, element-prog 19.1
public class SearchAMaze {
	public static void main(String[] args) {
		int[][] maze1 = {
			{0,1,0,0},
			{0,0,0,0},
			{0,1,1,0},
			{0,0,1,0},
			{1,0,0,1},
		};
		showResults(maze1, new int[] {0,0}, new int[] {3,3});

		int[][] maze2 = {
			{0,1,1,0,0},
			{0,0,1,0,0},
			{1,0,1,0,1},
			{0,1,1,1,1}
		};
		showResults(maze2, new int[] {0,0}, new int[] {3,0});
	}

	// NOTES: start[x,y] and end[x,y] path.

	private static void showResults(int[][] maze, int[] start, int[] end) {
		System.out.println("----ShowResults----");
		System.out.println("Start: " + Arrays.toString(start));
		System.out.println("End: " + Arrays.toString(end) + "\n");
		for(int[] arr: maze)
			System.out.println(Arrays.toString(arr));
		System.out.println();
		List<int[]> path = findPathDFS(maze, start, end);
		
		for(int i = 0; i < path.size() - 1; ++i)
			System.out.print(Arrays.toString(path.get(i)) + "->");
		if(path.size() != 0) System.out.println(Arrays.toString(path.get(path.size() - 1)) + "\n");
		else System.out.println("There is no such path.\n");
	}

	private static boolean foundPath;

	// V is the number of elements.
	// E is the number of edges (path from element to another element).
	// Time: O(V + E), space: O(V)
	public static List<int[]> findPathDFS(int[][] maze, int[] start, int[] end) {
		List<int[]> path = new ArrayList<>();
		if(maze.length == 0) return path;

		boolean[][] visited = new boolean[maze.length][maze[0].length];
		foundPath = false;
		
		visited[start[0]][start[1]] = true;
		path.add(new int[] {start[0], start[1]});
		dfs(maze, start[0], start[1], end, visited, path);

		if(path.size() == 1) path.remove(path.size() - 1);
		return path;
	}

	private static void dfs(int[][] maze, int currX, int currY, int[] end, boolean[][] visited, List<int[]> path) {

		if(currX == end[0] && currY == end[1]) {
			foundPath = true;
			return;
		}
		if(currX > 0 && maze[currX-1][currY] != 1  && !visited[currX-1][currY]) { // go left
			visited[currX-1][currY] = true;
			path.add(new int[] {currX-1, currY});
			dfs(maze, currX-1, currY, end, visited, path);
			if(!foundPath) path.remove(path.size() - 1);
		}
		if(currX < maze.length - 1 && maze[currX+1][currY] != 1 && !visited[currX+1][currY]) { // go right
			visited[currX+1][currY] = true;
			path.add(new int[] {currX+1, currY});
			dfs(maze, currX+1, currY, end, visited, path);
			if(!foundPath) path.remove(path.size() - 1);
		}
		if(currY > 0 && maze[currX][currY-1] != 1 && !visited[currX][currY-1]) { // go up
			visited[currX][currY-1] = true;
			path.add(new int[] {currX, currY-1});
			dfs(maze, currX, currY-1, end, visited, path);
			if(!foundPath) path.remove(path.size() - 1);
		}
		if(currY < maze[0].length - 1 && maze[currX][currY+1] != 1 && !visited[currX][currY+1]){ // go down
			visited[currX][currY+1] = true;
			path.add(new int[] {currX, currY+1});
			dfs(maze, currX, currY+1, end, visited, path);
			if(!foundPath) path.remove(path.size() - 1);
		}
	}
}
