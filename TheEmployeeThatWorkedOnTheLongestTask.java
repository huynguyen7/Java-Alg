import java.util.Arrays;

// leetcode 2432.
public class TheEmployeeThatWorkedOnTheLongestTask {
    public static void main(String[] args) {
        assert(showResults(10, new int[][] {{0,3},{2,5},{0,9},{1,15}}) == 1); // expect 1
        assert(showResults(26, new int[][] {{1,1},{3,7},{2,12},{7,17}}) == 3); // expect 3
        assert(showResults(2, new int[][] {{0,10},{1,20}}) == 0); // expect 0
    }

    private static int showResults(int n, int[][] logs) {
        System.out.println("\t----ShowResults----");
        for(int[] nums: logs)
            System.out.println(Arrays.toString(nums));
        int rs = hardestWorker(n, logs); 
        System.out.printf("%d -> %d\n\n", n, rs);
        return rs;
    }

    // Time: O(n), space: O(1)
    public static int hardestWorker(int n, int[][] logs) {
        int id = logs[0][0];
        int maxTime = logs[0][1];

        for(int i = 1; i < logs.length; ++i) {
            int timeToFinish = logs[i][1]-logs[i-1][1];
            if(maxTime <= timeToFinish) {
                if(maxTime == timeToFinish) id = Math.min(id, logs[i][0]);
                else id = logs[i][0];
                maxTime = timeToFinish;
            }
        }

        return id;
    }
}
