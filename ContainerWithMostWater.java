import java.util.Arrays;

// leetcode 11, element-prog 18.7
public class ContainerWithMostWater {
	public static void main(String[] args) {
		int[] h1 = {1,8,6,2,5,4,8,3,7};
		showResults(h1); // expect 49

		int[] h2 = {1,1};
		showResults(h2); // expect 1

		int[] h3 = {4,3,2,1,4};
		showResults(h3); // expect 16

		int[] h4 = {1,2,1};
		showResults(h4); // expect 2

		int[] h5 = {1,2,1,3,4,4,5,6,2,1,3,1,3,2,1,2,4,1};
		showResults(h5); // expect 48
		
		int[] h6 = {7,2};
		showResults(h6); // expect 2

        int[] h7 = {4,3,7,4};
        showResults(h7); // expect 12
	}

	private static void showResults(int[] height) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(height));

		int rs = maxAreaI(height);
		System.out.printf("Max Area: %d\n\n", rs);
	}

	// Trick: Consider the widest pair.

	// Greedy approach.
	// Time: O(n), space: O(1)
	public static int maxAreaI(int[] height) {
		int maxWater = 0, i = 0, j = height.length - 1;
		while(i < j) {
			int width = j - i;
			maxWater = Math.max(maxWater,
								width * Math.min(height[i], height[j]));
			
			if(height[i] < height[j]) i++;
			else if(height[i] > height[j]) j--;
			else {
				i++;
				j--;
			}
		}

		return maxWater;
	}

	// Brute-force approach.
	// Time: O(n^2), space: O(1)
	public static int maxAreaII(int[] height) {
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < height.length; ++i) {
			for(int j = i + 1; j < height.length; ++j) {
				max = Math.max(max, (j - i) * Math.min(height[j], height[i]));
			}
		}

		return max;
	}
}

// Constraints:
// height[i] >= 0 
// height.length >= 2
// height[] is not sorted.
