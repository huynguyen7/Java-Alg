import java.util.Arrays;
import java.util.PriorityQueue;

// leetcode 2164.
public class SortEvenAndOddIndicesIndependently {
    public static void main(String[] args) {
        showResults(new int[] {4,1,2,3}); // expect [2,3,4,1]
        showResults(new int[] {2,1}); // expect [2,1]
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        int[] rs = sortEvenOdd(nums);
        System.out.printf("%s\n\n", Arrays.toString(rs));
    }

    // Time: O(nlogn), space: O(n)
    public static int[] sortEvenOdd(int[] nums) {
        final int n = nums.length;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
                (a,b) -> Integer.compare(a,b));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
                (a,b) -> Integer.compare(b,a));

        int i;
        for(i = 0; i < n; ++i) {
            if(i % 2 == 0) minHeap.add(nums[i]);
            else           maxHeap.add(nums[i]);
        }

        for(i = 0; i < n; ++i) {
            if(i % 2 == 0) nums[i] = minHeap.poll();
            else nums[i] = maxHeap.poll();
        }

        return nums;
    }
}
