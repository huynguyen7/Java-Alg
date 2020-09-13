import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Iterator;

//element-prog 11.1
public class MergeSortedFiles {
	public static void main(String args[]) {
		int[][] nums1 = {
			{3,5,7},
			{0,6},
			{0,6,28}
		};
		showResults(nums1); //expect {0,0,3,5,6,6,7,28}
	}

	private static void showResults(int[][] nums) {
		System.out.println("----ShowResults----");
		List<List<Integer>> input = new ArrayList<>();
		for(int[] a: nums) {
			System.out.println(Arrays.toString(a));
			input.add(new ArrayList<>());
		}

		System.out.println("OUTPUT:\n");
		for(int i = 0; i < nums.length; ++i) {
			for(int j = 0; j < nums[i].length; ++j)
				input.get(i).add(nums[i][j]);
		}	
		
		List<Integer> rs = mergeSortedArrays(input);
		System.out.println(rs.toString());
	}

	private static class ArrayEntry {
		public Integer value;
		public Integer arrayId;
		
		public ArrayEntry(Integer value, Integer arrayId) {
			this.value = value;
			this.arrayId = arrayId;
		}
	}

	//better approach is in the book.
	public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
		List<Integer> rs = new ArrayList<>();
		if(sortedArrays == null || sortedArrays.size() == 0) return rs;

		List<Iterator<Integer>> iters = new ArrayList<>(sortedArrays.size());
		for(List<Integer> array: sortedArrays)
			iters.add(array.iterator());

		PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(
			((int) sortedArrays.size()), //heap size
			((ArrayEntry o1, ArrayEntry o2) -> Integer.compare(o1.value, o2.value)) //lambda expression, implement Comparator interface
		);

		for(int i = 0; i < iters.size(); ++i) {
			if(iters.get(i).hasNext())
				minHeap.add(new ArrayEntry(iters.get(i).next(), i));
		}

		while(!minHeap.isEmpty()) {
			ArrayEntry headEntry = minHeap.poll();
			rs.add(headEntry.value);

			if(iters.get(headEntry.arrayId).hasNext()) 
				minHeap.add(new ArrayEntry(iters.get(headEntry.arrayId).next(),
											headEntry.arrayId));
		}

		return rs;
	}

	//naive approach
	//time: O(nlogn), space: O(n)
	public static List<Integer> mergeSortedArrays2(List<List<Integer>> sortedFiles) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(); //space: O(n)
		
		List<Integer> rs = new ArrayList<>();
		
		//time: O(nlogn)
		for(List<Integer> a: sortedFiles) {
			for(Integer element: a)
				minHeap.add(element);
		}
		
		while(!minHeap.isEmpty())
			rs.add(minHeap.poll());
		
		return rs;
	}
}
