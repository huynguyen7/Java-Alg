import java.util.Arrays;

// leetcode 238.
public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        showResults(new int[] {1,2,3,4}); // expect [24,12,8,6]
        showResults(new int[] {-1,1,0,-3,3}); // expect [0,0,9,0,0]
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s\n%s\n\n", Arrays.toString(nums), Arrays.toString(productExceptSelf(nums)));
    }
    
    // Approach does not use division.
    // Time: O(n), space: O(n)
    public static int[] productExceptSelf(int[] nums) {
        final int n = nums.length;
        int[] ans = new int[n];

        int prod = 1;
        for(int i = 0; i < n; ++i) {
            ans[i] = prod;
            prod *= nums[i];
        }

        prod = 1;
        for(int i = n-1; i >= 0; --i) {
            ans[i] *= prod;
            prod *= nums[i];
        }

        return ans;
    }

    // Naive approach using division.
    // Time: O(n), space: O(1)
    public static int[] productExceptSelfII(int[] nums) {
        final int n = nums.length;
        int prod = 1;

        int numZeros = 0;
        for(int i = 0; i < n; ++i) {
            if(nums[i] != 0)
                prod *= nums[i];
            else numZeros++;
        }

        if(numZeros < 2) {
            if(numZeros == 0) {
                for(int i = 0; i < n; ++i)
                    nums[i] = prod/nums[i];
            } else {
                for(int i = 0; i < n; ++i) {
                    if(nums[i] != 0) nums[i] = 0;
                    else nums[i] = prod;
                }
            }
        } else {
            for(int i = 0; i < n; ++i)
                nums[i] = 0;
        }

        return nums;
    }
}
