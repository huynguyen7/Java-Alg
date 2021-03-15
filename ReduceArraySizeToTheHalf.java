import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.HashMap;
import java.util.Map;

public class ReduceArraySizeToTheHalf {
    public static void main(String[] args) {
        int[] nums1 = {3,3,3,3,5,5,5,2,2,7};
        showResults(nums1); // expect 2
        
        int[] nums2 = {7,7,7,7,7,7};
        showResults(nums2); // expect 1

        int[] nums3 = {1,9};
        showResults(nums3); // expect 1

        int[] nums4 = {1000,1000,3,7};
        showResults(nums4); // expect 1

        int[] nums5 = {1,2,3,4,5,6,7,8,9,10};
        showResults(nums5); // expect 5
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        int rs = minSetSize(nums);
        System.out.printf("Results: %d\n\n", rs);
    }

    // Time: O(nlogn), space: O(n)
    public static int minSetSize(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums) // time: O(n), space: O(n)
            map.put(num, map.getOrDefault(num, 0) + 1);

        final int n = nums.length;
        Queue<NumCount> maxHeap = new PriorityQueue<>((a, b) -> {
            return Integer.compare(b.count, a.count);
        });

        for(Map.Entry<Integer, Integer> entry: map.entrySet()) // time: O(nlogn), space: O(n)
            maxHeap.add(new NumCount(entry.getKey(), entry.getValue()));
        
        int setSize = 0, numRemoved = 0;
        while(!maxHeap.isEmpty()) {
            NumCount tmp = maxHeap.poll();
            numRemoved += tmp.count;
            setSize++;
            if(numRemoved >= (n/2)) return setSize;
        }

        return setSize;
    }

    static class NumCount {
        public int num;
        public int count;

        private NumCount() {}
        public NumCount(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public String toString() {
            return String.format("[%d, %d]", num, count);
        }
    }
}
