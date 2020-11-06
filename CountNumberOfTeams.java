import java.util.Arrays;

// leetcode 1395.
public class CountNumberOfTeams {
	public static void main(String[] args) {
		int[] rating1 = {2,5,3,4,1};
		showResults(rating1); // expect 3

		int[] rating2 = {2,1,3};
		showResults(rating2); // expect 0

		int[] rating3 = {1,2,3,4};
		showResults(rating3); // expect 4

		int[] rating4 = {1,2,3};
		showResults(rating4); // expect 1
	}

	private static void showResults(int[] rating) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(rating));
		int rs = numTeamsII(rating);
		System.out.printf("Num teams = %d\n\n", rs);
	}

	// Better approach
	// Time: O(n^2), space: O(n)
	public static int numTeamsI(int[] rating) {
		int[] up = new int[rating.length];
		int[] down = new int[rating.length];
		int rs = 0;

		for(int i = rating.length - 1; i >= 0; --i) {
			for(int j = i + 1; j < rating.length; ++j) {
				if(rating[i] < rating[j]) {
					up[i] += 1;
					rs += up[j];
				} else {
					down[i] += 1;
					rs += down[j];
				}
			}
		}

		return rs;
	}

	// Naive approach.
	// Time: O(n^3), space: O(1)
	public static int numTeamsII(int[] rating) {
		if(rating == null || rating.length == 0) return 0;
		
		int rs = 0;
		for(int i = 0; i < rating.length; ++i) {
			for(int j = i + 1; j < rating.length; ++j) {
				for(int k = j + 1; k < rating.length; ++k) {
					if((rating[i] < rating[j] && rating[j] < rating[k]) ||
						(rating[i] > rating[j] && rating[j] > rating[k])) {
						rs++;
					}
				}
			}
		}

		return rs;
	}
}
