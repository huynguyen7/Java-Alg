import java.util.Arrays;

// leetcode 1779.
public class FindNearestPointThatHasTheSameXOrYCoordinate {
    public static void main(String[] args) {
        int x1 = 3, y1 = 4;
        int[][] points1 = {
            {1,2},
            {3,1},
            {2,4},
            {2,3},
            {4,4}
        };
        showResults(x1, y1, points1); // expect 2

        int x2 = 3, y2 = 4;
        int[][] points2 = {
            {3,4}
        };
        showResults(x2, y2, points2); // expect 0

        int x3 = 3, y3 = 4;
        int[][] points3 = {
            {2,3}
        };
        showResults(x3, y3, points3); // expect -1

    }

    private static void showResults(int x, int y, int[][] points) {
        System.out.println("\t----ShowResults----");
        for(int[] p: points)
            System.out.printf("[%d, %d] ", p[0], p[1]);
        System.out.printf("\n[x, y] = [%d, %d]\n", x, y);
        int rs = nearestValidPoint(x, y, points);
        System.out.printf("Results: %d\n\n", rs);
    }

    // Time: O(n), space: O(1)
    public static int nearestValidPoint(int x, int y, int[][] points) {
        int rs = 0;
        int minDist = Integer.MAX_VALUE;
        int[] currLocation = {x,y};

        for(int i = 0; i < points.length; ++i) { // time: O(n)
            if(points[i][0] != currLocation[0] && points[i][1] != currLocation[1]) continue;

            int dist = manhattanDistance(currLocation, points[i]);
            if(dist < minDist) {
                minDist = dist;
                rs = i;
            }
        }

        return minDist == Integer.MAX_VALUE ? -1 : rs;
    }

    private static int manhattanDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0]-p2[0]) + Math.abs(p1[1]-p2[1]);
    }
}
