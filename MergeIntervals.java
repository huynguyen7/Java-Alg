import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

//leetcode 56, element-prog 14.6
public class MergeIntervals {
	public static void main(String[] args) {
		int[][] intervals1 = {
			{1,3},{2,6},{8,10},{15,18}
		};
		showResults(intervals1); //expect {{1,6},{8,10},{15,18}}

		int[][] intervals2 = {
			{1,4},{4,5}
		};
		showResults(intervals2); //expect {{1,5}}

		int[][] intervals3 = {
			{2,8},{0,2},{1,3},{-1,3}
		};
		showResults(intervals3); //expect {{-1,8}}

		int[][] intervals4 = {
			{-1,3},{9,11},{1,2},{4,5}
		};
		showResults(intervals4); //expect {{-1,3},{4,5},{9,11}}
	}

	private static void showResults(int[][] intervals) {
		System.out.println("----ShowResults----");
		for(int[] interval: intervals)
			System.out.print(Arrays.toString(interval) + " ");
		System.out.println();
		
		int[][] rs = merge(intervals);
		for(int[] interval: rs)
			System.out.print(Arrays.toString(interval) + " ");
		System.out.println("\n");
	}

	//time: O(nlogn), space: O(n), n is total intervals
	public static int[][] merge(int[][] intervals) {
		Arrays.sort(intervals,
			((int[] a1, int[] a2) -> Integer.compare(a1[0], a2[0])) //lambda expression implements Comparator interface
		); //time: O(nlogn)
		
		int min = intervals[0][0];
		int max = intervals[0][1];
		
		List<int[]> list = new ArrayList<>();

		for(int i = 1; i < intervals.length; ++i) {
			if(max < intervals[i][0]) {
				list.add(new int[] {min, max});
				min = intervals[i][0];
				max = intervals[i][1];
			} else if(max >= intervals[i][0] && max < intervals[i][1]) {
				max = intervals[i][1];
			} else if(max >= intervals[i][0] && max >= intervals[i][1]) {
				continue;
			}
		}

		list.add(new int[] {min, max});
		
		return listToArray(list);
	}

	private static int[][] listToArray(List<int[]> list) {
		if(list.size() == 0) return new int[0][0];
		int[][] rs = new int[list.size()][2];
		
		for(int i = 0; i < list.size(); ++i)
			rs[i] = list.get(i);
		return rs;
	}
}


//constraints:
//intervals[i][0] <= intervals[i][1]
