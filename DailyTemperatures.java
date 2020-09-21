import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

// leetcode 739.
public class DailyTemperatures {
	public static void main(String[] args) {
		int[] T1 = {73,74,75,71,69,72,76,73};
		showResults(T1); // expect [1, 1, 4, 2, 1, 1, 0, 0]
	}

	private static void showResults(int[] T) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(T));
		int[] rs = dailyTemperatures(T);
		System.out.println(Arrays.toString(rs));
	}

	// Time: O(n), space: O(n)
	// n is T.length
	public static int[] dailyTemperatures(int[] T) {
		int[] rs = new int[T.length]; // space: O(n)
		Deque<Integer> stack = new LinkedList<>(); // store indexes
		
		for(int i = T.length - 1; i >= 0; --i) { // time: O(n)
			while(!stack.isEmpty() && T[i] >= T[stack.peekFirst()])
				stack.pop();
			rs[i] = stack.isEmpty() ? 0 : stack.peekFirst() - i;
			stack.addFirst(i);
		}
		
		return rs;
	}
}
