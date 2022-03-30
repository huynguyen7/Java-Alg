import java.util.Arrays;

// leetcode 198.
public class HouseRobber {
	public static void main(String[] args) {
		int[] nums1 = {1,2,3,1};
		assert(showResults(nums1) == 4); // expect 4

		int[] nums2 = {2,7,9,3,1};
		assert(showResults(nums2) == 12); // expect 12

		int[] nums3 = {1,2,3,4};
		assert(showResults(nums3) == 6); // expect 6

        int[] nums4 = {3,2,2,10,2};
        assert(showResults(nums4) == 13); // expect 13
	}
	
	private static int showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		int rs = robI(nums);
		System.out.printf("Result: %d\n\n", rs);
        
        return rs;
	}

	// DP approach, bottom-up, no space consumption.
	// Time: O(n), space: O(1)
	public static int rob(int[] nums) {
		// rob1 is to hold the previous house's max sum value.
		// rob2 is to hold the curr house's max sum value.
		int rob1 = 0, rob2 = 0; // init base case
		
		for(int i = 0; i < nums.length; ++i) {
			int tmp = Math.max(nums[i] + rob1, rob2);
			rob1 = rob2;
			rob2 = tmp;
		}

		return rob2;
	}

    // DP approach, top-down.
    // Time: O(n), space: O(n)
    public static int robI(int[] nums) {
        int[] mem = new int[nums.length] ;
        Arrays.fill(mem, -1);

        return dfsI(nums, mem, 0);
    }

    private static int dfsI(int[] nums, int[] mem, int curr) {
        if(curr >= nums.length) return 0;
        else if(mem[curr] != -1) return mem[curr];
        else {
            int take = dfsI(nums, mem, curr+2) + nums[curr];
            int notTake = dfsI(nums, mem, curr+1);

            mem[curr] = Math.max(take, notTake);
            return mem[curr];
        }
    }

    // Recursive approach.
    // Time: O(2^n), space: O(2^n)
    public static int robII(int[] nums) {
        return dfsII(nums, 0);
    }
    
    private static int dfsII(int[] nums, int curr) {
        if(curr >= nums.length) return 0;
        
        int take = dfsII(nums, curr+2) + nums[curr];
        int notTake = dfsII(nums, curr+1);
        
        return Math.max(take, notTake);
    }
}
