import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

//leetcode 1054.
public class DistantBarcodes {
    public static void main(String args[]) {
        int[] nums1 = { 1, 1, 1, 2, 2, 2 };
        showResults(nums1); // expect {2,1,2,1,2,1}

        int[] nums2 = { 1, 1, 1, 1, 2, 2, 3, 3 };
        showResults(nums2); // expect {1,3,1,3,2,1,2,1}

        int[] nums3 = { 1, 1, 1, 1, 2, 2, 2, 3, 3 };
        showResults(nums3);
    }

    private static void showResults(int[] barcodes) {
        System.out.println("----ShowResults----");
        System.out.println(Arrays.toString(barcodes));

        int[] rs = rearrangeBarcodes(barcodes);
        System.out.println(Arrays.toString(rs) + "\n");
    }

    // time: O(mlogn), space: O(n)
    // m is total elements in barcodes, n is the number of UNIQUE value
    public static int[] rearrangeBarcodes(int[] barcodes) {
        PriorityQueue<NumCount> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.count, a.count));
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < barcodes.length; ++i)
            map.put(barcodes[i], map.getOrDefault(barcodes[i], 0) + 1);
        for (Map.Entry<Integer, Integer> entry : map.entrySet())
            maxHeap.add(new NumCount(entry.getKey(), entry.getValue()));

        int index = 0;
        while (!maxHeap.isEmpty()) {
            NumCount first = maxHeap.poll();
            NumCount second = maxHeap.poll();

            barcodes[index++] = first.val;
            if (--first.count != 0)
                maxHeap.add(first);

            if (second != null) {
                barcodes[index++] = second.val;
                if (--second.count != 0)
                    maxHeap.add(second);
            }
        }

        return barcodes;
    }

    static class NumCount {
        int val;
        int count;

        public NumCount(int val, int count) {
            this.val = val;
            this.count = count;
        }
    }
}

// constraints:
// intput array is sorted.
