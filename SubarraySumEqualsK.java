import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

// leetcode 560.
public class SubarraySumEqualsK {
    public static void main(String[] args) {
        showResults(new int[] {1,1,1}, 2); // expect 2
        showResults(new int[] {1,2,3}, 3); // expect 2
    }

    private static void showResults(int[] nums, int k) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%d -> %s\n%d\n\n", k, Arrays.toString(nums), 
                subarraySum(nums, k));
    }

    // Better approach.
    // Time: O(n), space: O(n)
    public static int subarraySum(int[] nums, int k) {
        final int n = nums.length;
        int count = 0;
        int currSum = 0;
        
        Map<Integer, Integer> prevSum = new HashMap<>(); // <sum, freq> pair.
        prevSum.put(0, 1); // There is one case that has sum to 0 as default.
        for(int i = 0; i < n; ++i) {
            currSum += nums[i];
            if(prevSum.containsKey(currSum-k))
                count += prevSum.get(currSum-k);
            prevSum.put(currSum, prevSum.getOrDefault(currSum, 0) + 1);
        }


        return count;
    }

    // Time: O(n^2), space: O(1)
    public static int subarraySumII(int[] nums, int k) {
        final int n = nums.length;
        int count = 0;

        for(int i = 0; i < n; ++i) {
            int currSum = 0;
            for(int j = i; j < n; ++j) {
                currSum += nums[j];
                if(currSum == k) count ++;
            }
        }

        return count;
    }
}
