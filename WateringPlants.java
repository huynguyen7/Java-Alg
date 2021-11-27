import java.util.Arrays;

// leetcode 2079.
public class WateringPlants {
    public static void main(String[] args) {
        showResults(new int[] {2,2,3,3}, 5); // expect 14
        showResults(new int[] {1,1,1,4,2,3}, 4); // expect 30
        showResults(new int[] {7,7,7,7,7,7,7}, 8); // expect 49
        showResults(new int[] {3,2,4,2,1},6); // expect 17
    }

    private static void showResults(int[] plants, int capacity) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(plants));
        System.out.printf("%d -> %d\n\n", capacity, wateringPlants(plants, capacity));
    }

    // Time: O(n), space: O(1)
    public static int wateringPlants(int[] plants, int capacity) {
        final int n = plants.length;
        int numSteps = 0;
        int myCapacity = capacity;

        for(int i = 0; i < n; ++i) {
            ++numSteps;
            myCapacity -= plants[i];

            // We don't have enough water for next plan, so go back and refill.
            if(myCapacity < 0) {
                numSteps += i*2;
                myCapacity = capacity-plants[i];
            }
        }
        

        return numSteps;
    }
}
