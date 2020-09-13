import java.util.Arrays;

//leetcode 1502.
public class CanMakeArithmeticProgressionFromSequence {
	public static void main(String args[]) {
		int[] nums1 = {3,5,1}; //expect true
		int[] nums2 = {1,2,4}; //expect false
		int[] nums3 = {1,3,7,5,9}; //expect true
		int[] nums4 = {-509,-19,-439,-264,-404,-369,-299,-89,-229,-54,-194,16,-544,-159,-124,-474,-334}; //expect true

		showResults(nums1);
		showResults(nums2);
		showResults(nums3);
		showResults(nums4);
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println("INPUT:\t" + Arrays.toString(nums));
		System.out.printf("OUTPUT: %b\n\n", canMakeArithmeticProgression(nums));
	}

	//time: O(nlogn), space: O(1); n is startTime.length
	public static boolean canMakeArithmeticProgression(int[] nums) {
		Arrays.sort(nums); //time: O(nlogn)
		int diff1 = Math.abs(nums[1] - nums[0]);
        
        for(int i = 0; i < nums.length - 1; ++i)
            if(diff1 != Math.abs(nums[i+1] - nums[i]))
                return false;
        
		return true;
	}
}
