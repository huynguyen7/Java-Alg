import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class GroupThePeopleGivenTheGroupSizeTheyBelongTo {
	public static void main(String[] args) {
		int[] groupSizes1 = {3,3,3,3,3,1,3};
		showResults(groupSizes1); // expect [[5],[0,1,2],[3,4,6]]

		int[] groupSizes2 = {2,1,3,3,3,2};
		showResults(groupSizes2); // expect [[1],[0,5],[2,3,4]]

		int[] groupSizes3 = {2,2,1,1,1,1,1,1};
		showResults(groupSizes3); // expect [[2],[3],[4],[5],[6],[7],[0,1]]
	}

	private static void showResults(int[] groupSizes) {
		System.out.println("----ShowResults----");
		System.out.println("GROUP SIZES: " + Arrays.toString(groupSizes));

		List<List<Integer>> rs = groupThePeople(groupSizes);
		System.out.println();
		for(List<Integer> l: rs)
			System.out.println(l.toString());
		System.out.println();
	}

	/* NOTES:
	- Each person is labeled with a UNIQUE ID from 0 -> n - 1.
	**/

	// n = groupSizes.length
	// Time: O(n), space:O (n)
	public static List<List<Integer>> groupThePeople(int[] groupSizes) {
		if(groupSizes == null || groupSizes.length == 0)
			return Collections.emptyList();

		Map<Integer, List<Integer>> map = new HashMap<>(); // <size-group list> key value pairs.
		List<List<Integer>> rs = new ArrayList<>();

		for(int i = 0; i < groupSizes.length; ++i) {
			List<Integer> tmp;
			if(map.containsKey(groupSizes[i])) {
				tmp = map.get(groupSizes[i]);
				tmp.add(i);
			} else {
				tmp = new ArrayList<>();
				tmp.add(i);
				map.put(groupSizes[i], tmp);
			}
			
			if(tmp.size() >= groupSizes[i]) {
				rs.add(new ArrayList<>(tmp));
				tmp.clear();
			}
		}

		for(Map.Entry<Integer, List<Integer>> entry: map.entrySet()) {
			List<Integer> tmp = entry.getValue();
			if(tmp.size() != 0)
				rs.add(new ArrayList(tmp));
		}
		
		return rs;
	}
}
