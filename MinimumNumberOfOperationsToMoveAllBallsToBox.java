import java.util.Arrays;

// leetcode 1769.
public class MinimumNumberOfOperationsToMoveAllBallsToBox {
    public static void main(String[] args) {
        String boxes1 = "110";
        showResults(boxes1); // expect [1,1,3]

        String boxes2 = "001011";
        showResults(boxes2); // expect [11,8,5,4,3,4]
    }

    private static void showResults(String boxes) {
        System.out.println("\t----ShowResults----");
        System.out.println("BOXES: " + boxes);
        final int[] rs = minOperations(boxes);
        System.out.printf("Min Operations: %s\n\n", Arrays.toString(rs));
    }

    // Time: O(n), space: O(n)
    public static int[] minOperations(String boxes) {
        int n = boxes.length(), sum = 0;
        char[] M = boxes.toCharArray();
        int[] rs = new int[n];
        int[] left = new int[n], right = new int[n];

        if (M[0] == '1') sum++;
        for(int i = 1; i < n; ++i) {
            left[i] = left[i-1] + sum;
            rs[i] += left[i];
            if(M[i] == '1') sum++;
        }

        sum = 0;
        if (M[n-1] == '1') sum++;
        for(int i = n-2; i >= 0; --i) {
            right[i] = right[i+1] + sum;
            rs[i] += right[i];
            if(M[i] == '1') sum++;
        }

        return rs;
    }
}
