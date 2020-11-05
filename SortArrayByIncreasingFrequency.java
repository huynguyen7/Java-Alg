import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

// leetcode 1636.
public class SortArrayByIncreasingFrequency {
	public static void main(String[] args) {
		int[] nums1 = {1,1,2,2,2,3};
		showResults(nums1); // expect {3,1,1,2,2,2}

		int[] nums2 = {2,3,1,3,2};
		showResults(nums2); // expect {1,3,3,2,2}

		int[] nums3 = {-1,1,-6,4,5,-6,1,4,1};
		showResults(nums3); // expect {5,-1,4,4,-6,-6,1,1,1}
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		int[] rs = frequencySort(nums);
		System.out.println(Arrays.toString(rs) + "\n");
	}

	// Time: O(nlogn), space: O(n)
	public static int[] frequencySort(int[] nums) {
		Integer[] arr = new Integer[nums.length];
		for(int i = 0; i < nums.length; ++i)
			arr[i] = Integer.valueOf(nums[i]);

		Map<Integer, Integer> map = new HashMap<>();
		for(Integer num: arr)
			map.put(num, map.getOrDefault(num, 0) + 1);

		Arrays.sort(arr, 
			(a, b) -> {
				if(map.get(a) == map.get(b))
					return Integer.compare(b, a);
				else return Integer.compare(map.get(a), map.get(b));
			}
		);

		for(int i = 0; i < nums.length; ++i)
			nums[i] = arr[i].intValue();
		return nums;
	}
}
