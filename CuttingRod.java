import java.util.Arrays;

/**
 * SOURCE:
 * - https://www.geeksforgeeks.org/cutting-a-rod-dp-13/
 * - https://www.radford.edu/~nokie/classes/360/dp-rod-cutting.html
 */

public class CuttingRod {
    public static void main(String[] args) {
        int rodLength1 = 8;
        int[] prices1 = {1,5,8,9,10,17,17,20};
        showResults(prices1, rodLength1); // expect 22

        int rodLength2 = 8;
        int[] prices2 = {3,5,8,9,10,17,17,20};
        showResults(prices2, rodLength2); // expect 24

        int rodLength3 = 3;
        int[] prices3 = {3,5,9};
        showResults(prices3, rodLength3); // expect 9
    }

    private static void showResults(int[] prices, int rodLength) {
        System.out.println("\t----ShowResults----");
        System.out.println("ROD LENGTH: " + rodLength);
        System.out.println("PRICES:     " + Arrays.toString(prices));
        System.out.printf("MAX VALUE  : %d\n\n", maxValue(prices, rodLength));
    }

    /**
     * DP approach.
     * Bottom-Up DP.
     * Time: O(n^2), space: O(n)
     */
    public static int maxValue(int[] prices, int rodLength) {
        if(prices == null || rodLength != prices.length) return 0;

        int[] grid = new int[rodLength+1];
        for(int currentRodLength = 1; currentRodLength <= rodLength; ++currentRodLength) {
            int max = Integer.MIN_VALUE;
            // Finding max for current rod length with each cut.
            for(int cut = 1; cut <= currentRodLength; ++cut)
                max = Math.max(max, prices[cut-1] + grid[currentRodLength-cut]);
            grid[currentRodLength] = max;
        }
        
        return grid[rodLength];
    }

    /**
     * DP approach.
     * Top-Down DP.
     * Time: O(n^2), space: O(n)
    */
    public static int maxValueI(int[] prices, int rodLength) {
        if(prices == null || rodLength != prices.length) return 0;
        int[] grid = new int[rodLength];
        return maxValueHelperI(prices, rodLength, grid);
    }

    private static int maxValueHelperI(int[] prices, int currentRodLength, int[] grid) {
        if(currentRodLength <= 0) return 0;
        
        // DEFAULT VALUE IS 0 -> NOT VISITED NODE.
        if(grid[currentRodLength-1] == 0) {
            int max = Integer.MIN_VALUE;
            for(int cut = 1; cut <= currentRodLength; ++cut)
                max = Math.max(max, prices[cut-1] + maxValueHelperI(prices, currentRodLength-cut, grid));
            grid[currentRodLength-1] = max;
        }

        return grid[currentRodLength-1];
    }

    /**
     * Recursive approach.
     * Time: O(2^n), space: O(2^n)
    */
    public static int maxValueII(int[] prices, int rodLength) {
        if(prices == null || rodLength != prices.length) return 0;
        return maxValueHelperII(prices, rodLength);
    }

    private static int maxValueHelperII(int[] prices, int currentRodLength) {
        if(currentRodLength <= 0) return 0;

        int max = Integer.MIN_VALUE;
        for(int cut = 1; cut <= currentRodLength; ++cut)
            max = Math.max(max, prices[cut-1] + maxValueHelperII(prices, currentRodLength-cut));

        return max;
    }
}
