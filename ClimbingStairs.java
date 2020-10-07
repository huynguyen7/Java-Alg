import java.util.*;

// leetcode 70
public class ClimbingStairs {
	public static void main(String[] args) {
		showResults(2); // expect 2
						// 1 step + 1 step
						// 2 steps

		showResults(3); // expect 3
						// 1 step + 1 step + 1 step
						// 1 step + 2 steps
						// 2 steps + 1 step
						// GOOD EXAMPLE TO DRAW RECURSION TREE.

		showResults(4); // expect 5
		showResults(5); // expect 8
	}

	private static void showResults(int n) {
		System.out.println("----ShowResults----");
		System.out.printf("n = %d -> Result: %d\n\n", n, climbStairs(n));
	}

	// RULES:
	// Each time you can either climb 1 or 2 steps.
	// It takes n steps to reach to the top.

	// You are climbing a stair case.
	// How many distinct ways can you climb to the top ?
	// Trick: totalWays(n) = totalWays(n-1) + totalWays(n-2)

	// best approach
	// Time: O(n), space: O(1)
	public static int climbStairs(int n) {
		if(n <= 1) return 1;
		
		int rs = 1, prev = 1, prevPrev = 1;
		int counter = 2;

		while(counter <= n) {
			prevPrev = prev;
			prev = rs;
			rs = prev + prevPrev;
			counter++;
		}

		return rs;
	}

	// DP approach.
	// bottom-up DP.
	// Time: O(n), space: O(n)
	public static int climbStairsI(int n) {
		if(n <= 1) return 1;
		
		int[] numWaysOfSteps = new int[n + 1];
		numWaysOfSteps[0] = 1; // init base case
		numWaysOfSteps[1] = 1; // init base case

		for(int i = 2; i < numWaysOfSteps.length; ++i) {
			numWaysOfSteps[i] = numWaysOfSteps[i - 1] + numWaysOfSteps[i - 2];
		}

		return numWaysOfSteps[n];
	}


	// DP approach.
	// top-down DP.
	// Time: O(n), space: O(n)
	public static int climbStairsII(int n) {
		if(n <= 1) return 1;

		int[] numWaysOfSteps = new int[n + 1];
		dfsII(n, n, numWaysOfSteps);

		return numWaysOfSteps[n];
	}

	private static int dfsII(int n, int currNumSteps, int[] numWaysOfSteps) {
		if(currNumSteps <= 1) return 1;
		else {
			if(numWaysOfSteps[currNumSteps] == 0) { // curr index has not processed.
				int numWaysWhenOneStep = dfsII(n, currNumSteps - 1, numWaysOfSteps);
				int numWaysWhenTwoSteps = dfsII(n, currNumSteps - 2, numWaysOfSteps);

				numWaysOfSteps[currNumSteps] = numWaysWhenOneStep + numWaysWhenTwoSteps;
			}

			return numWaysOfSteps[currNumSteps];
		}
	}

	private static int totalWays;

	// recursive approach
	// Time: O(2^n), space: O(2^n)
	public static int climbStairsIII(int n) {
		if(n <= 1) return 1;

		totalWays = 0;
		dfsIII(n, 0);

		return totalWays;
	}

	private static void dfsIII(int n, int currNumSteps) {
		if(currNumSteps > n) return;
		else if(currNumSteps == n) {
			totalWays++;
			return;
		} else {
			dfsIII(n, currNumSteps + 1);
			dfsIII(n, currNumSteps + 2);
		}
	}
}
