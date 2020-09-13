import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

//leetcode 347.
public class TopKFrequentELements {
	public static void main(String args[]) {
		int[] nums1 = {1,1,1,2,2,3};
		int k1 = 2;
		showResults(nums1, k1); //ex[ect {1,2}

		int[] nums2 = {1};
		int k2 = 1;
		showResults(nums2, k2); //expect {1} 
	}

	private static void showResults(int[] nums, int k) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		System.out.printf("k = %d\n", k);
		int[] rs = topKFrequent(nums, k);
		System.out.println(Arrays.toString(rs) + "\n");
	}

	//time: O(nlogk), space: O(k)
	public static int[] topKFrequent(int[] nums, int k) {
		if(nums.length == 0) return new int[0];
		
		Map<Integer, Integer> map = new HashMap<>(); //<Value, Frequency>
		for(int i = 0; i < nums.length; ++i) //time: O(n)
			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(
			k + 1, //init heap's max size
			//compare based on frequency getting from map.get(key)
			(key1, key2) -> Integer.compare(map.get(key1), map.get(key2))
		);

		for(int key: map.keySet()) {
			minHeap.add(key);
			if(minHeap.size() == k + 1) minHeap.remove(); //filter
		}
		
		int[] rs = new int[k];
		for(int i = 0; i < k; ++i)
			rs[i] = minHeap.poll();
		
		return rs;
	}
}
