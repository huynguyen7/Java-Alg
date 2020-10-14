import java.util.Deque;
import java.util.Arrays;
import java.util.LinkedList;

// leetcode 84, element-prog 18.8
public class LargestRectangleInHistorgram {
	public static void main(String[] args) {
		int[] h1 = {1,2,5};
		showResults(h1); // expect 5
	
		int[] h2 = {2,1,5,6,2,3};
		showResults(h2); // expect 10

		int[] h3 = {1,4,2,5,6,3,2,6,6,5,2,1,3};
		showResults(h3); // expect 20

		int[] h4 = {4,2,0,3,2,4,3,4};
		showResults(h4); // expect 10

		int[] h5 = {1,1,2,3};
		showResults(h5); // expect 4
	}

	// GOOD EXPLANATION:
	// https://www.youtube.com/watch?v=ZmnqCZp9bBs

	private static void showResults(int[] heights) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(heights));

		int rs = largestRectangleArea(heights);
		System.out.printf("Largest Rect Area: %d\n\n", rs);
	}

	// Trick: the stack should be sorted in descending order(from top of the stack).

	// Greedy approach.
	// Time: O(n), space: O(n)
	public static int largestRectangleArea(int[] heights) {
		Deque<Integer> stack = new LinkedList<>(); // stack contains indices.
		int maxArea = 0, i = 0;
		
		while(i < heights.length) {
			if(stack.isEmpty() || heights[i] >= heights[stack.getFirst()]) {
				stack.addFirst(i++);
			} else {
				int topIndex = stack.removeFirst();
				int area = Integer.MIN_VALUE;
				if(stack.isEmpty()) area = heights[topIndex] * i;
				else area = heights[topIndex] * (i - stack.getFirst() - 1);

				maxArea = Math.max(maxArea, area);
			}
		}
		
		// After the previous for-loop, if there is any value left in the stack. Process these values.
		while(!stack.isEmpty()) {
			int topIndex = stack.removeFirst();
			int area = Integer.MIN_VALUE;
			if(stack.isEmpty()) area = heights[topIndex] * i;
			else area = heights[topIndex] * (i - stack.getFirst() - 1);

			maxArea = Math.max(maxArea, area);
		}
		
		return maxArea;
	}
}
