// leetcode 887
public class SuperEggDrop {
	public static void main(String[] args) {
		int k1 = 1, n1 = 2;
		showResults(k1, n1); // expect 2

		int k2 = 2, n2 = 6;
		showResults(k2, n2); // expect 3

		int k3 = 3, n3 = 14;
		showResults(k3, n3); // expect 4

		int k4 = 2, n4 = 3;
		showResults(k4, n4); // expect 2
							// 	GOOD EXAMPLE TO DRAW RECURSION TREE.
	}

	private static void showResults(int k, int n) {
		System.out.println("----ShowResults----");
		System.out.printf("Num Eggs: %d, Num Floors: %d\n", k, n);
		System.out.printf("Total Min Drops: %d\n\n", superEggDropI(k, n));
	}

	// GOOD EXPLANATION:
	// https://www.youtube.com/watch?v=iOaRjDT0vjc&t=10s
	// https://algorithms.tutorialhorizon.com/dynamic-programming-egg-dropping-problem/

	// RULES:
	// Find the least amount of drops to ensure the pivotal floor is found.
	// Pivotal floor is the highest floor that the egg dropped wont break.

	// Trick: If current floor's drop does not break the egg, then go upwardone floor to check.
	// If it breaks at current floor, then go downward one floor to check.

	// k is the number of eggs.
	// n is the number of floors.

	// DP approach.
	// bottom-up DP.
	// Time: O(n*k^2), space: O(n*k^2)
	public static int superEggDropI(int numEggs, int numFloors) {
		if(numFloors <= 1 || numEggs == 1) return numFloors; // base cases.
		
		int[][] minDrops = new int[numEggs+1][numFloors+1];
		for(int i = 0; i < minDrops[1].length; ++i) // init base cases.
			minDrops[1][i] = i;
		for(int i = 0; i < minDrops.length; ++i) // init base cases.
			minDrops[i][1] = 1;
		
		for(int i = 2; i < minDrops.length; ++i) {
			for(int j = 2; j < minDrops[i].length; ++j) {
				minDrops[i][j] = Integer.MAX_VALUE;
				for(int k = 1; k <= j; ++k) { // go check each floor level.
					int numDropsAtCurrFloor = 1 + Math.max(minDrops[i-1][k-1], minDrops[i][j-k]);
					minDrops[i][j] = Math.min(minDrops[i][j], numDropsAtCurrFloor);
				}
			}
		}
		
		return minDrops[numEggs][numFloors];
	}

	// Recursive approach.
	// Time: O(n*2^k), space: O(n*2^k)
	public static int superEggDropII(int numEggs, int numFloors) {
		if(numFloors <= 1 || numEggs == 1) return numFloors; // base cases.

		int minDrops = Integer.MAX_VALUE;
		for(int i = 1; i <= numFloors; ++i) { // Time: O(n)
			int numDropsAtCurrFloor = Math.max(superEggDropII(numEggs, numFloors - i), superEggDropII(numEggs - 1, i - 1)); // Time: O(2^k)
			minDrops = Math.min(minDrops, numDropsAtCurrFloor);
		}

		return minDrops + 1;
	}
}
