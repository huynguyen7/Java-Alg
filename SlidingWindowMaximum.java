import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Deque;

// leetcode 239.
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] nums1 = {1,3,-1,-3,5,3,6,7};
        showResults(nums1, 3); // expect [3,3,5,5,6,7]

        int[] nums2 = {1};
        showResults(nums2, 1); // expect [1]

        int[] nums3 = {1,-1};
        showResults(nums3, 1); // expect [1,-1]

        int[] nums4 = {9,11};
        showResults(nums4, 2); // expect [11]

        int[] nums5 = {4,-2};
        showResults(nums5, 2); // expect [4]
    }

    private static void showResults(int[] nums, int k) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        System.out.printf("k = %d\n", k);
        System.out.printf("%s\n\n", Arrays.toString(maxSlidingWindow(nums, k)));
    }

    // Using deque approach.
    // Time: O(n), space: O(n)
    public static int [] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0) return new int[0];
        
        final int n = k == nums.length ? 1 : nums.length-k+1;
        int[] rs = new int[n];
        Deque<Integer> deque = new LinkedList<>();
        
        for(int i = 0; i < nums.length; ++i) {
            while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[i])
                deque.pollLast();
            deque.addLast(i);

            if(!deque.isEmpty() && deque.peekFirst() <= i-k)
                deque.pollFirst();

            if(!deque.isEmpty() && i-k+1 >= 0)
                rs[i-k+1] = nums[deque.peekFirst()];
        }

        return rs;
    }

    // Using heap approach.
    // Time: O(nlogn), space: O(k)
    public static int[] maxSlidingWindowI(int[] nums, int k) {
        if(nums == null || nums.length == 0) return new int[0];
        
        final int n = k == nums.length ? 1 : nums.length-k+1;
        int[] rs = new int[n];
        PriorityQueue<Integer> holder = new PriorityQueue<>((a, b) -> { // max heap
            return Integer.compare(b, a);
        });

        for(int i = 0; i < k; ++i)
            holder.add(nums[i]); // time: O(logn)
        rs[0] = holder.peek();

        for(int i = k ; i < nums.length; ++i) {
            holder.remove(nums[i-k]);
            holder.add(nums[i]);
            rs[i-k+1] = holder.peek();
        }

        return rs;
    }
}
