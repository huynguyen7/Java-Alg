import java.util.Arrays;

// leetcode 2176.
public class CountEqualAndDivisiblePairsInAnArray {
    public static void main(String[] args) {
        assert(showResults(new int[] {3,1,2,2,2,1,3}, 2) == 4); // expect 4
        assert(showResults(new int[] {1,2,3,4}, 1) == 0); // expect 0
    }

    private static int showResults(int[] nums, int k) {
        System.out.println("\t----ShowResults----");
        int rs = countPairs(nums, k);
        System.out.printf("%s\n%d -> %d\n\n", Arrays.toString(nums), k, rs);
        return rs;
    }

    // Time: O(n^2), space: O(1)
    public static int countPairs(int[] nums, int k) {
        final int n = nums.length;
        int count = 0;
        for(int i = 0; i < n; ++i) {
            for(int j = i+1; j < n; ++j) {
                if(nums[i] != nums[j] || (i*j)%k != 0) continue;
                count++;
            }
        }

        return count;
    }
}
