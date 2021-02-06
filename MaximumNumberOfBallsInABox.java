import java.util.Map;
import java.util.HashMap;

// leetcode 1742.
public class MaximumNumberOfBallsInABox {
    public static void main(String[] args) {
        showResults(1, 10); // expect 2
        showResults(5, 15); // expect 2
        showResults(19, 28); // expect 2
    }

    private static void showResults(int lowLimit, int highLimit) {
        System.out.println("\t----ShowResults----");
        int rs = countBalls(lowLimit, highLimit);
        System.out.printf("LOW: %d, HIGH: %d\nRESULTS: %d\n\n", lowLimit, highLimit, rs);
    }

    // Time: O(n), space: O(n)
    public static int countBalls(int lowLimit, int highLimit) {
        if(lowLimit > highLimit) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        for(int num = lowLimit; num <= highLimit; ++num) {
            int boxNum = sumDigit(num);
            map.put(boxNum, map.getOrDefault(boxNum, 0) + 1);
        }
        
        int max = Integer.MIN_VALUE;
        for(int val: map.values())
            max = Math.max(max, val);

        return max;
    }

    private static int sumDigit(int num) {
        int sum = 0;
        
        while(num != 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }
}
