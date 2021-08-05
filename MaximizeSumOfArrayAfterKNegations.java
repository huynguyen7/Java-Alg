import java.util.Arrays;
import java.util.Queue;
import java.util.PriorityQueue;

// leetcode 1005.
public class MaximizeSumOfArrayAfterKNegations {
    public static void main(String[] args) {
        showResults(new int[] {4,2,3}, 1); // expect 5
        showResults(new int[] {3,-1,0,2}, 3); // expect 6
        showResults(new int[] {2,-3,-1,5,-4}, 2); // expect 13
        showResults(new int[] {5,6,9,-3,3}, 2); // expect 20
    }

    private static void showResults(int[] nums, int k) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        System.out.printf("%d -> %d\n\n", k, largestSumAfterKNegations(nums, k));
    }

    // Time: O(nlogn), space: O(1)
    public static int largestSumAfterKNegations(int[] nums, int k) {
        int sum = 0;
        Queue<Integer> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

        for(int num: nums)
            minHeap.add(num);

        while(k-- != 0) {
            int num = minHeap.poll();
            num *= -1;
            minHeap.add(num);
        }

        int size = nums.length;
        while(size-- != 0)
            sum += minHeap.poll();

        return sum;
    }
}
