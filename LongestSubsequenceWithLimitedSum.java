import java.util.Arrays;

// leetcode 2389.
public class LongestSubsequenceWithLimitedSum {
    public  static void main(String[] args) {
        assert(Arrays.equals(showResults(new int[] {4,5,2,1}, new int[] {3,10,21}), new int[] {2,3,4})); // expect [2,3,4]
        assert(Arrays.equals(showResults(new int[] {2,3,4,5}, new int[] {1}), new int[] {0})); // expect [0]
    }

    private static int[] showResults(int[] nums, int[] queries) {
        System.out.println("\t----ShowResults----");
        int[] rs = answerQueries(nums, queries); 
        System.out.printf("%s\n%s\n%s\n\n",
                Arrays.toString(nums),
                Arrays.toString(queries),
                Arrays.toString(rs));
        return rs;
    }

    // Time: O(n + m + log(n)), space: O(m)
    public static int[] answerQueries(int[] nums, int[] queries) {
        final int n = nums.length;
        final int m = queries.length;
        int[] rs = new int[m];
        
        Arrays.sort(nums); // time: O(log(n))

        for(int i = 0 ; i < m; ++i) {
            int query = queries[i];
            int sum = 0, size = 0;
            int j = 0;
        
            while(j < n && sum + nums[j] <= query) {
                sum += nums[j];
                size += 1;
                j += 1;
            }

            rs[i] = size;
        }

        return rs;
    }
}
