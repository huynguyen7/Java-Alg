import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

// leetcode 373.
public class FindKPairsWithSmallestSums {
    public static void main(String[] args) {
        int[] a1 = { 1, 7, 11 };
        int[] b1 = { 2, 4, 6 };
        int k1 = 3;
        showResults(a1, b1, k1); // expect [[1,2],[1,4],[1,6]]

        int[] a2 = { 1, 1, 2 };
        int[] b2 = { 1, 2, 3 };
        int k2 = 2;
        showResults(a2, b2, k2); // expect [[1,1],[1,1]]
    }

    private static void showResults(int[] nums1, int[] nums2, int k) {
        System.out.println("----ShowResults----");
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
        System.out.printf("k: %d\n\n", k);

        List<List<Integer>> rs = kSmallestPairs(nums1, nums2, k);
        for (List<Integer> l : rs)
            System.out.println(l.toString());
    }

    // time: O(n*m*logk), space: O(k)
	//n is nums1.length, m is nums2.length
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		List<List<Integer>> rs = new ArrayList<>();
        List<Integer> holder;
		PriorityQueue<List<Integer>> maxHeap = new PriorityQueue<>(
			k + 1,
			(l1, l2) -> Integer.compare(
				l2.get(0) + l2.get(1),
				l1.get(0) + l1.get(1)
			)
		);

		for(int i = 0; i < nums1.length; ++i) {
			for(int j = 0; j < nums2.length; ++j) {
				holder = new ArrayList<>();
				holder.add(nums1[i]);
				holder.add(nums2[j]);
				maxHeap.add(holder);
				
				if(maxHeap.size() == k + 1)
					maxHeap.poll();
			}
		}

		while(rs.size() != k)
			rs.add(maxHeap.poll());
		
		
		return rs;
    }
}
