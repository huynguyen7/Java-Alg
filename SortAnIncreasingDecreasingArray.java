import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Collections;

//element-prog 11.2
public class SortAnIncreasingDecreasingArray {
	public static void main(String args[]) {
		int[] nums1 = {57,131,493,294,221,339,418,452,442,190}; //mountain array (multiple peak nodes)
		showResults(nums1);

		int[] nums2 = {1,5,6,4,3,9,10,0,-1};
		showResults(nums2);

		int[] nums3 = {1,4,5,3,6,7,2,3,4};
		showResults(nums3);
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println("INPUT:\t" + Arrays.toString(nums));
		List<Integer> input = convertToList(nums);
		
		//int[] rs = sortKIncreasingDecreasingArray2(nums); -> naive approach
		//System.out.println("OUTPUT: " + Arrays.toString(rs));
		List<Integer> rs = sortKIncreasingDecreasingArray(input);
		System.out.println("OUTPUT:\t" + rs.toString());
		System.out.println();
	}

	//sort the mountain array
	//better approach
	//time: O(nlogk), space: O(n); n is total elements, k is the number of subarray
	public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> nums) {
		if(nums == null || nums.size() == 0) return new ArrayList<>();

		List<List<Integer>> sortedArrays = new ArrayList<>();
		boolean isIncreasing = true;
		int i, j = 0;
		
		for(i = 0; i < nums.size() - 1; i += (j - i)) {
			List<Integer> subarray = new ArrayList<>();
			j = i;
			if(isIncreasing) {
				while(j + 1 < nums.size() && (nums.get(j + 1) >= nums.get(j))) {
					subarray.add(nums.get(j++));
				}
			} else {
				while(j + 1 < nums.size() && (nums.get(j + 1) < nums.get(j)))
					subarray.add(nums.get(j++));
			}

			subarray.add(nums.get(j++));
			if(!isIncreasing) Collections.reverse(subarray);

			isIncreasing = !isIncreasing;
			sortedArrays.add(subarray);
		}

		return mergeSortedArrays(sortedArrays);
	}

	private static class ArrayEntry {
		Integer value;
		Integer arrayId;
	
		public ArrayEntry() {}
	
		public ArrayEntry(Integer value, Integer arrayId) {
			this.value = value;
			this.arrayId = arrayId;
		}
	} 

	//from element-prog 11.1
	//time: O(nlogk), space: O(n)
	private static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
		List<Integer> rs = new ArrayList<>(); //space: O(n)
		if(sortedArrays == null || sortedArrays.size() == 0) return rs;
		
		List<Iterator<Integer>> iteratorsList = new ArrayList<>();
		for(List<Integer> list: sortedArrays)
			iteratorsList.add(list.iterator());
		
		PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(
			((int) iteratorsList.size()), //heap's max size
			(ArrayEntry a1, ArrayEntry a2) -> a1.value.compareTo(a2.value) //lambda expression implement comparator interface
		);

		for(int i = 0; i < iteratorsList.size(); ++i) //add all first entry values to minHeap
			minHeap.add(new ArrayEntry(iteratorsList.get(i).next(), i));

		while(!minHeap.isEmpty()) {//time: O(n)
			ArrayEntry min = minHeap.poll(); //time: O(logk)
			rs.add(min.value);
			
			if(iteratorsList.get(min.arrayId).hasNext())
				minHeap.add(new ArrayEntry(iteratorsList.get(min.arrayId).next(),
											min.arrayId));
		}

		return rs;
	}

	//naive approach
	//time: O(nlogn), space: O(1), n is nums.length
	public static int[] sortKIncreasingDecreasingArray2(int[] nums) {
		Arrays.sort(nums); //time: O(nlogn)
		return nums;
	}

	private static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	//time: O(n), space: O(n)
	private static List<Integer> convertToList(int[] nums) {
		List<Integer> rs = new ArrayList<>();
		for(int i = 0; i < nums.length; ++i)
			rs.add(Integer.valueOf(nums[i]));
		return rs;
	}
}
