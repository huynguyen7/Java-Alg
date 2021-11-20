import java.util.Arrays;

// leetcode 2073.
public class TimeNeededToBuyTickets {
    public static void main(String[] args) {
        showResults(new int[] {2,3,2}, 2); // expect 6
        showResults(new int[] {2,3,2}, 1); // expect 7
        showResults(new int[] {5,1,1,1}, 0); // expect 8
    }

    private static void showResults(int[] tickets, int k) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(tickets));
        System.out.printf("%d -> %d\n\n", k, timeRequiredToBuy(tickets, k));
    }

    // Greedy approach.
    // Time: O(n), space: O(1)
    public static int timeRequiredToBuy(int[] tickets, int k) {
        final int n = tickets.length;

        int timeTaken = 0;
        for(int i = 0; i < n; ++i) {
            if(i <= k) timeTaken += Math.min(tickets[i], tickets[k]);
            else timeTaken += Math.min(tickets[i], tickets[k]-1);
        }

        return timeTaken;
    }

    // Naive approach.
    // Time: O(n^2), space: O(1)
    public static int timeRequiredToBuyII(int[] tickets, int k) {
        final int n = tickets.length;

        int timeTaken = 0;
        while(tickets[k] != 0) {
            for(int i = 0; i < n; ++i) {
                if(tickets[i] != 0){ 
                    timeTaken++;
                    tickets[i]--;
                }
                if(tickets[k] == 0) break;
            }
        }

        return timeTaken;
    }
}
