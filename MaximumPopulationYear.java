import java.util.Arrays;

// leetcode 1854.
public class MaximumPopulationYear {
    public static void main(String[] args) {
        int[][] logs1 = {{1993,1999},{2000,2010}};
        showResults(logs1); // expect 1993

        int[][] logs2 = {{1950,1961},{1960,1971},{1970,1981}};
        showResults(logs2); // expect 1960
    }

    private static void showResults(int[][] logs) {
        System.out.println("\t----ShowResults----");
        for(int[] log: logs)
            System.out.println(Arrays.toString(log));
        System.out.printf("--> OUTPUT: %d\n\n", maximumPopulation(logs));
    }

    // Time: O(n), space: O(1)
    public static int maximumPopulation(int[][] logs) {
        if(logs == null || logs.length == 0) return 0;

        int[] populations = new int[101];
        for(int[] log: logs) {
            int startYearIndex = log[0]-1950;
            int endYearIndex = log[1]-1950;

            while(startYearIndex < endYearIndex)
                populations[startYearIndex++]++;
        }

        int maxIndex = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < populations.length; ++i) {
            if(populations[i] > max) {
                max = populations[i];
                maxIndex = i;
            }
        }

        return maxIndex+1950;
    }
}
