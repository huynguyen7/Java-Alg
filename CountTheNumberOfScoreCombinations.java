import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// element-prog 17.1
public class CountTheNumberOfScoreCombinations {
	public static void main(String[] args) {
		int finalScore1 = 12;
		int[] scores1 = {2,3,7};
		showResults(finalScore1, scores1); // expect 4

		int finalScore2 = 5;
		int[] scores2 = {1,2,5};
		showResults(finalScore2, scores2); // ex[ect 4

		int finalScore3 = 3;
		int[] scores3 = {2};
		showResults(finalScore3, scores3); // expect 0

		int finalScore4 = 5;
		int[] scores4 = {1,2,3};
		showResults(finalScore4, scores4); // expect 5
	}

	private static void showResults(int finalScore, int[] scores) {
		System.out.println("----ShowResults----");
		List<Integer> input = new ArrayList<>();
		for(int i: scores)
			input.add(i);
		
		System.out.println(input.toString());
		int rs = getNumberOfScoreCombinationsI(finalScore, input);
		System.out.printf("Final Score: %d -> Total combinations: %d\n\n", finalScore, rs);
	}

	// Constraints:
	// 0 <= finalScore
	
	// Good explanation for DP: https://www.youtube.com/watch?v=DJ4a7cmjZY0
	// Also: https://www.youtube.com/watch?v=xCbYmUPvc2Q

	// DP approach
	// bottom-up DP : Calculate all of possible combinations from 0 -> finalScores
	// n is the number of values from 0 -> finalScore
	// c is scores.size()
	// Time: O(c * n), space: O(c * n)
	public static int getNumberOfScoreCombinationsI(int finalScore, List<Integer> scores) {
		if(finalScore == 0 && scores.size() == 0) return 1;
		if(finalScore != 0 && scores.size() == 0) return 0;

		int[][] numCombinationsForScores = new int[scores.size()][finalScore + 1];
		
		for(int i = 0; i < numCombinationsForScores.length; ++i)
			numCombinationsForScores[i][0] = 1; // init all 0 values to 1.
												// because 0 score can be created from any score.

		for(int i = 0; i < numCombinationsForScores.length; ++i) { // Time: O(c)
			for(int j = 1; j < numCombinationsForScores[0].length; ++j) { // Time: O(n)
				int numCombinationsWhenNotChoosing = i - 1 >= 0 ? numCombinationsForScores[i - 1][j] : 0;
				int numCombinationsWhenChoosing = j >= scores.get(i)
												? numCombinationsForScores[i][j - scores.get(i)]
												: 0;
				numCombinationsForScores[i][j] = numCombinationsWhenNotChoosing + numCombinationsWhenChoosing;
			}
		}

		return numCombinationsForScores[scores.size() - 1][finalScore];
	}

	// recursive approach
	// Time: O(2^n), space: O(2^n)
	public static int getNumberOfScoreCombinationsII(int finalScore, List<Integer> scores) {
		return dfs(scores, scores.size(), finalScore);
	}

	private static int dfs(List<Integer> scores, int size, int finalScore) {
		if(finalScore == 0) return 1; // 0 score can be created from any score
		if(finalScore < 0) return 0; // no solution exist when finalScore is negative
		if(size <= 0 && finalScore >= 1) return 0; // no solution exist when there is no score left
													// and finalScore is still available

		return dfs(scores, size - 1, finalScore) 
				+ dfs(scores, size, finalScore - scores.get(size - 1));
	}
}
