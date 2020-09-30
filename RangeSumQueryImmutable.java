import java.util.Arrays;

// leetcode 303.
public class RangeSumQueryImmutable {
	public static void main(String[] args) {
		int[] test1 = {-2,0,3,-5,2,-1};
		System.out.println(Arrays.toString(test1));
		RangeSumQueryImmutable nums = new RangeSumQueryImmutable(test1);
		
		System.out.println();
		System.out.println(nums.sumRange(0, 2)); // expect 1
		System.out.println(nums.sumRange(2, 5)); // expect -1
		System.out.println(nums.sumRange(0, 5)); // expect -3
	}

	private int[] nums; // this is used with first approach
	private int[][] cache; // this is used with second approach

	public RangeSumQueryImmutable(int[] nums) {
		initCacheSumQueryI(nums);
		System.out.println(Arrays.toString(nums));
	}

	// used for first approach
	public int sumRange(int i, int j) {
		if(i - 1 < 0) return nums[j];
		return nums[j] - nums[i - 1];
	}

	// used for second approach
	public int sumRangeII(int i, int j) {
		return cache[i][j];
	}

	// Time: O(n), space: O(1)
	private void initCacheSumQueryI(int[] nums) {
		this.nums = nums;
		
		for(int i = 0; i < nums.length; ++i)
			nums[i] += i == 0 ? 0 : nums[i - 1];
	}

	// Time limit exceeded on Leetcode
	// DP approach.
	// bottom-up DP.
	// n = nums.length
	// Time: O(n^2), space: O(n^2)
	private void initCacheSumQueryII(int[] nums) {
		cache = new int[nums.length][nums.length];

		for(int i = 0; i < cache.length; ++i) {
			for(int j = i; j < cache[0].length; ++j) {
				if(j == i) {
					cache[i][j] = nums[i];
				} else {
					cache[i][j] = cache[i][j - 1] + nums[j];
				}
			}
		}
	}
}

// Constraints:
// 0 <= i <= j <= nums.length
