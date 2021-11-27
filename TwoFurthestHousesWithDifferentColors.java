import java.util.Arrays;

// leetcode 2078.
public class TwoFurthestHousesWithDifferentColors {
    public static void main(String[] args) {
        showResults(new int[] {1,1,1,6,1,1,1}); // expect 3
        showResults(new int[] {1,6,1,1,1}); // expect 3
        showResults(new int[] {1,1,1,6,1}); // expect 3
        showResults(new int[] {1,8,3,8,3}); // expect 4
        showResults(new int[] {1,8,3,8,1}); // expect 3
        showResults(new int[] {1,2,2,2,1}); // expect 3
        showResults(new int[] {0,1}); // expect 1
    }

    private static void showResults(int[] colors) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(colors));
        System.out.printf("%d\n\n", maxDistance(colors));
    }

    // Time: O(n), space: O(1)
    public static int maxDistance(int[] colors) {
        final int n = colors.length;
        int maxDist = 1;

        for(int i = n-1; i > 0; --i) {
            if(colors[i] != colors[0]) {
                maxDist = i;
                break;
            }
        }

        for(int i = 0; i < n-1; ++i) {
            if(colors[i] != colors[n-1]) {
                maxDist = Math.max(maxDist, n-1-i);
                break;
            }
        }

        return maxDist;
    }
}
