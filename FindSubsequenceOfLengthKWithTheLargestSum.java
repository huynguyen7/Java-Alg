import java.util.*;

// leetcode 2099.
public class FindSubsequenceOfLengthKWithTheLargestSum {
    public static void main(String[] args) {
        assert(Arrays.equals(showResults(new int[] {2,1,3,3}, 2), new int[] {3,3})); // expect [3,3]
        assert(Arrays.equals(showResults(new int[] {-1,-2,3,4}, 3), new int[] {-1,3,4})); // expect [-1,3,4]
        assert(Arrays.equals(showResults(new int[] {3,4,3,3}, 2), new int[] {4,3})); // expect [4,3]
    }

    private static int[] showResults(int[] nums, int k) {
        System.out.println("\t----ShowResults----");
        int[] rs = maxSubsequence(nums, k);
        System.out.printf("%s\n%d -> %s\n\n", Arrays.toString(nums), k,
                Arrays.toString(rs));

        return rs;
    }

    // Time: O(n*logk), space: O(k)
    public static int[] maxSubsequence(int[] nums, int k) {
        final int n = nums.length;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            /*
             * Sort by value, however, store their indices to the heap.
             */
            (a,b) -> Integer.compare(nums[a], nums[b])
        );

        for(int i = 0; i < n; ++i) {
            minHeap.add(i);
            if(minHeap.size() > k)
                minHeap.poll();
        }

        return minHeap.stream()
                .sorted()
                .mapToInt(i -> nums[i])
                .toArray();
    }
}
