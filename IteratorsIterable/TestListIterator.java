import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class TestListIterator {
	public static void main(String args[]) {
		int[][] nums1 = {
			{3,5,7},
			{0,6},
			{0,6,28},
			{1,2,3}
		};

		List<List<Integer>> sortedArrays = convertToList(nums1);
		for(List<Integer> l: sortedArrays) {
			Iterator<Integer> iter = l.iterator();
			while(iter.hasNext())
				System.out.printf("%d ", iter.next());
			System.out.println();
		}
	}

	private static List<List<Integer>> convertToList(int[][] nums) {
		List<List<Integer>> rs = new ArrayList<>();
		for(int[] arr: nums) {
			List<Integer> tmp = new ArrayList<>();
			for(int i = 0; i < arr.length; ++i)
				tmp.add(Integer.valueOf(arr[i]));
			rs.add(tmp);
		}

		return rs;
	}
}
