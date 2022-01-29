import java.util.Arrays;

// leetcode 2149.
public class RearrangeArrayElementsBySign {
    public static void main(String[] args) {
        showResults(new int[] {3,1,-2,-5,2,-4}); // expect [3,-2,1,-5,2,-4]
        showResults(new int[] {-1,1}); // expect [1,-1]
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        int[] rs = rearrangeArray(nums);
        System.out.printf("%s\n\n", Arrays.toString(rs));
    }

    // Time: O(n), space: O(n)
    public static int[] rearrangeArray(int[] nums) {
        final int n = nums.length;
        int[] ps = new int[n/2]; // Storing positive nums.
        int[] ns = new int[n/2]; // Storing negative nums.
        
        int x = 0, y = 0;
        for(int i = 0; i < n; ++i) {
            if(nums[i] > 0) // is positive
                ps[x++] = nums[i];
            else // is negative
                ns[y++] = nums[i];
        }
        
        // Start at 0 again!
        x = 0;
        y = 0;

        for(int i = 0; i < n; ++i) {
            if(i % 2 == 0) // Apply rule 3.
                nums[i] = ps[x++];
            else
                nums[i] = ns[y++];
        }

        return nums;
    }
}
