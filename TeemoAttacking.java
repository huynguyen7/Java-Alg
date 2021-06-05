import java.util.Arrays;

// leetcode 495.
public class TeemoAttacking {
    public static void main(String[] args) {
        int[] timeSeries1 = {1,4};
        int duration1 = 2;
        showResults(timeSeries1, duration1); // expect 4

        int[] timeSeries2 = {1,2};
        int duration2 = 2;
        showResults(timeSeries2, duration2); // expect 3
    }

    private static void showResults(int[] timeSeries, int duration) {
        System.out.println("\t----ShowResults----");
        System.out.printf("Time Series: %s\nDuration: %d\nOutput: %d\n\n", Arrays.toString(timeSeries), duration, findPoisonedDuration(timeSeries, duration));
    }

    // Time: O(n), space: O(1)
    public static int findPoisonedDuration(int[] timeSeries, int duration) {
        if(timeSeries == null || timeSeries.length == 0) return 0;

        int poisonedTime = 0;
        int endPoisonedTime = timeSeries[0]+duration-1;
        for(int i = 1; i < timeSeries.length; ++i) {
            if(endPoisonedTime >= timeSeries[i]) poisonedTime += timeSeries[i]-timeSeries[i-1];
            else poisonedTime += endPoisonedTime-timeSeries[i-1]+1;
            endPoisonedTime = timeSeries[i]+duration-1;
        }
        poisonedTime += endPoisonedTime-timeSeries[timeSeries.length-1]+1;

        return poisonedTime;
    }
}
