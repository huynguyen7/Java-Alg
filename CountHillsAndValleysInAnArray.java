import java.util.Arrays;

// leetcode 2210.
public class CountHillsAndValleysInAnArray {
    public static void main(String[] args) {
        assert(showResults(new int[] {2,4,1,1,6,5}) == 3); // expect 3
        assert(showResults(new int[] {6,6,5,5,4,1}) == 0); // expect 0
        assert(showResults(new int[] {1,2,2,1}) == 1); // expect 1
    }

    private static int showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        int rs = countHillValley(nums);
        System.out.printf("-> %d\n\n", rs);
        return rs;
    }

    // Time: O(n), space: O(1)
    public static int countHillValley(int[] nums) {
        if(nums.length <= 2) return 0;

        final int n = nums.length;
        int rs = 0;

        int left = nums[0];
        
        for(int i = 1; i < n-1; ++i) {
            int curr = nums[i];
            int right = nums[i+1];

            if((curr > left && curr > right) || (curr < left && curr < right)) {
                rs += 1;
                left = curr;
            }
        }

        return rs;
    }
}
