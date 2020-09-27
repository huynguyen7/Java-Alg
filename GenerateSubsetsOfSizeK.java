import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

// element-prog 16.5
public class GenerateSubsetsOfSizeK {
	public static void main(String[] args) {
		int n1 = 5;
		int k1 = 2;
		showResults(n1, k1); // expect {(1,2),(1,3),(1,4),(1,5),(2,3), (2,4),(2,5),(3,4),(3,5),(4,5)}

		int n2 = 2;
		int k2 = 2;
		showResults(n2, k2); // expect {{1,2}}

		int n3 = 5;
		int k3 = 3;
		showResults(n3, k3);
	}

	// Constraints:
	// k <= nums.length
	// nums[i] must be UNIQUE.
	// 1 <= nums[i] <= n
	private static void showResults(int n, int k) {
		System.out.println("----ShowResults----");
		System.out.printf("From 1 -> %d, with size: %d\n\n", n, k);
		
		List<List<Integer>> rs = generateSubsetsOfSizeK(n, k);
		System.out.printf("Number of subsets size %d: %d\n", k, rs.size());
		for(List<Integer> l: rs)
			System.out.println(l.toString());
		System.out.println();
	}

	private static List<List<Integer>> rs;

	// time for recursive calls: O()
	// C(n,k) is combination
	// Time: O(C(n,k)), space: O(C(n,k))
	public static List<List<Integer>> generateSubsetsOfSizeK(int n, int k) {
		rs = new ArrayList<>();
		List<Integer> holder = new ArrayList<>();
		backtrack(n, k, holder, 1);
		return rs;
	}

	private static void backtrack(int n, int k, List<Integer> holder, int startIndex) {
		if(holder.size() == k) { // goal
			rs.add(new ArrayList<>(holder));
			return;
		}

		for(int j = startIndex ; j <= n; ++j) { // Time: O(n)
			int val = j;
			holder.add(val); // make choice
			backtrack(n, k, holder, j + 1); // explore
			holder.remove(holder.size() - 1); // undo choice
		}
	}
}
