import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// leetcode 2529.
public class MaximumCountOfPositiveIntegerAndNegativeInteger {
    public static void main(String[] args) {
        assert(showResults(new int[] {-2,-1,-1,1,2,3}) == 3); // expect 3
        assert(showResults(new int[] {-3,-2,-1,0,0,1,2}) == 3); // expect 3
        assert(showResults(new int[] {5,20,66,1314}) == 4); // expect 4
        assert(showResults(new int[] {0,0,0,0,0,0}) == 0); // expect 0
        assert(showResults(new int[] {-2, -2, -1}) == 3); // expect 3
    }
    
    private static int showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        int rs = maximumCount(nums);
        System.out.printf("-> %d\n\n", rs);
        return rs;
    }
    
    // Find the smallest number's index that larger than 0.
    // Time: O(logn), space: O(1)
    public static int maximumCount(int[] nums) {
        final int n = nums.length;
        if(nums[0] == 0 && nums[n-1] == 0) return 0;
        if(nums[0] > 0 || nums[n-1] < 0) return n;
        int indexStartPos = binarySearch(0, nums, 0, n-1); // Inclusive bound
        int indexEndNeg = indexStartPos; // Exclusive bound
        while(indexEndNeg > 0 && nums[indexEndNeg-1] == 0) indexEndNeg -= 1;
        return Math.max(indexEndNeg, n-indexStartPos);
    }

    private static int binarySearch(int val, int[] nums, int low, int high) {
        if(low < 0 || high >= nums.length) return -1;
        if(low == high) return low;

        int mid = low + (high-low)/2;

        if(mid == 0 && nums[mid] > 0) return mid;
        else if(mid == 0 && nums[mid] <= 0) return -1;

        if(nums[mid] > 0 && nums[mid-1] <= 0) return mid;
        else if(nums[mid] <= 0) low = mid+1;
        else high = mid-1;
        return binarySearch(val, nums, low, high);
    }

    // Naive approach
    // Time: O(n), space: O(1)
    public static int maximumCountII(int[] nums) {
        int pos = 0;
        int neg = 0;

        for(int num: nums) {
            if(num == 0) continue;
            else if(num < 0) neg += 1;
            else pos += 1;
        }

        return Math.max(pos, neg);
    }
}
