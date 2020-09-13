import java.util.Arrays;

public class CountSort {
	public static void main(String args[]) {
		int[] nums1 = {2,3,1,2,3,5,2,3,8,1};
		showResults(nums1);

		int[] nums2 = {5,2,4,2,4,6,3,2,3,6,7,4,8,8};
		showResults(nums2);
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		countSort(nums);
		System.out.println(Arrays.toString(nums));
	}

	//count sort only works with positive numbers.
	//k is the numeber of UNIQUE value in the array.
	//time: O(n), space: O(n)
	public static void countSort(int[] nums) {
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < nums.length; ++i) //time: O(n)
			max = Math.max(max, nums[i]);
		
		int[] freq = new int[max + 1]; //count value's frequency from 0->max
		for(int i = 0; i < nums.length; ++i) //time: O(n)
			freq[nums[i]]++;
		
		int currIndex = 0;
		for(int i = 0; i < freq.length; ++i) {
			int j = i;
			if(freq[j] != 0) {
				while(i < nums.length && freq[j] != 0) {
					nums[currIndex++] = j;
					freq[j]--;
				}
			}
		}
	}
}
