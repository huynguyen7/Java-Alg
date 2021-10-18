import java.util.Arrays;

// leetcode 2037.
public class MinimumNumberOfMovesToSeatEveryone {
    public static void main(String[] args) {
        showResults(new int[] {3,1,5}, new int[] {2,7,4}); // expect 4
        showResults(new int[] {4,1,5,9}, new int[] {1,3,2,6}); // expect 7
        showResults(new int[] {2,2,6,6}, new int[] {1,3,2,6}); // expect 4
    }

    private static void showResults(int[] seats, int[] students) {
        System.out.println("\t----ShowResults----");
        System.out.printf("Seats: %s\nStuds: %s\n-> %d\n\n",
                Arrays.toString(seats),
                Arrays.toString(students),
                minMovesToSeat(seats, students));
    }

    // Time: O(nlogn), space: O(1)
    public static int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);

        final int n = seats.length;
        int numMoves = 0;

        for(int i = 0; i < n; ++i)
            numMoves += Math.abs(seats[i]-students[i]);

        return numMoves;
    }
}
