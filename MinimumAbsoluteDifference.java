import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

//leetcode 1200.
public class MinimumAbsoluteDifference {
	public static void main(String args[]) {
		int[] nums1 = {4,2,1,3}; //expect {{1,2},{2,3},{3,4}}
		int[] nums2 = {1,3,6,10,15}; //expect {{1,3}}
		int[] nums3 = {3,8,-10,23,19,-4,-14,27}; //expect {{-14,-10},{19,23},{23,27}}
		int[] nums4 = {34,-5,-24,60,2,-46}; //expect {{-5,2}}

		showResults(nums1);
		showResults(nums2);
		showResults(nums3);
		showResults(nums4);
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println("INPUT:\t" + Arrays.toString(nums));
		System.out.println();
		List<List<Integer>> rs = minimumAbsDifference(nums);
		for(List<Integer> l: rs)
			System.out.println(l.toString());
		System.out.println();
	}

	//time: O(nlogn), space: O(1); n is 
	public static List<List<Integer>> minimumAbsDifference(int[] nums) {
		Arrays.sort(nums); //time: O(nlogn)
		List<List<Integer>> rs = new ArrayList<>();
		
		int minDif = Math.abs(nums[1] - nums[0]);
		for(int i = 0; i < nums.length - 1; ++i)
			minDif = Math.min(Math.abs(nums[i+1] - nums[i]), minDif);
		System.out.println("MinDif: " + minDif);

		for(int i = 0; i < nums.length - 1; ++i) { 
			if(Math.abs(nums[i+1] - nums[i]) == minDif)
				rs.add(Arrays.asList(nums[i], nums[i+1]));
			System.out.println(nums[i+1] + "\t" + nums[i] + "\t" + (nums[i+1] - nums[i]));
		}
		return rs;
	}
}
