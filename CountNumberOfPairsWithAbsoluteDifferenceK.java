import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

// leetcode 2006.
public class CountNumberOfPairsWithAbsoluteDifferenceK {
    public static void main(String[] args) {
        showResults(new int[] {1,2,2,1}, 1); // expect 4
        showResults(new int[] {1,3}, 3); // expect 0
        showResults(new int[] {3,2,1,5,4}, 2); // expect 3
        showResults(new int[] {10,2,10,9,1,6,8,9,2,8}, 5); // expect 1
        showResults(new int[] {7,7,8,3,1,2,7,2,9,5}, 6); // expect 6
        showResults(new int[] {9,1,6,5,2,10,1,4,3,7}, 8); // expect 3
    }

    private static void showResults(int[] nums, int k) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        System.out.printf("k = %d => %d\n\n", k, countKDifference(nums, k));
    }

    /**
     * 1 <= nums.length <= 200
     * 1 <= nums[i] <= 100
     * 1 <= k <= 99
     */

    // Time: O(n), space: O(n)
    public static int countKDifference(int[] nums, int k) {
        int count = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums)
            map.put(num, map.getOrDefault(num,0)+1);

        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if(map.containsKey(entry.getKey()+k))
                count += entry.getValue()*map.get(entry.getKey()+k);
        }

        return count;
    }

    // Time: O(n^2), space: O(1)
    public static int countKDifferenceII(int[] nums, int k) {
        int count = 0;
        for(int i = 0; i < nums.length; ++i) {
            for(int j = i; j < nums.length; ++j) {
                if(Math.abs(nums[i]-nums[j]) == k)
                    count++;
            }
        }

        return count;
    }
}
