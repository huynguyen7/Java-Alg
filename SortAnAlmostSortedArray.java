import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

//element-prog 11.3
public class SortAnAlmostSortedArray {
	public static void main(String args[]) {
		int[] nums1 = {3,-1,2,6,4,5,8};
		showResults(nums1, 2); //expect {-1,2,3,4,5,8}

		int[] nums2 = {6,5,3,2,8,10,9};
		showResults(nums2, 3); //expect {2,3,5,6,8,9,10}
	} 

	private static void showResults(int[] nums, int k) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		ArrayList<Integer> convertedList = new ArrayList<>();
		for(int i = 0; i < nums.length; ++i)
			convertedList.add(nums[i]);
		
		
		sort(convertedList, k);
		System.out.println(convertedList.toString());
	}

	//time: O(nlogk), space: O(k)
	public static void sort(ArrayList<Integer> arr, int k) {
		if(arr.size() == 0 || arr == null) return;

		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(
			k + 1,
			(a, b) -> a - b
		);

		Iterator<Integer> iter = arr.iterator();
		for(int i = 0; i <= k; ++i)
			if(iter.hasNext()) minHeap.add(iter.next()); //space: O(k)
		
		int currIndex = 0; //use this variable as a pointer to set array value
		while(!minHeap.isEmpty()) {
			arr.set(currIndex++, minHeap.poll());
			if(iter.hasNext()) minHeap.add(iter.next());
		}
	}

	//naive approach
	//time: O(nlogn), space: O(1)
	public static void sort(List<Integer> arr, int k) {
		Collections.sort(arr); //time: O(nlogn)
	}
}

//explanation:
//https://www.youtube.com/watch?v=yQ84lk-EXTQ
