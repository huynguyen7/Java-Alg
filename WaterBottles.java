// leetcode 1518.
public class WaterBottles {
    public static void main(String[] args) {
        showResults(9,3); // expect 13
        showResults(15,4); // expect 19
        showResults(5,5); // expect 6
        showResults(2,3); // expect 2
        showResults(17,3); // expect 25
    }

    private static void showResults(int numBottles, int numExchange) {
        System.out.println("\t----ShowResults----");
        System.out.printf("NumBottles: %d\tNumExchange: %d\n", numBottles, numExchange);
        int rs = numWaterBottles(numBottles, numExchange);
        System.out.printf("NumWaterBottles: %d\n\n", rs);
    }

    // Time: O(n), space: O(1)
    public static int numWaterBottles(int numBottles, int numExchange) {
        int rs = numBottles;
        int emptyBottles = numBottles;
        while(emptyBottles >= numExchange) {
            rs += emptyBottles/numExchange;
            emptyBottles = (emptyBottles%numExchange)+(emptyBottles/numExchange);
        }

        return rs;
    }
}
