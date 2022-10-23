import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

// leetcode 2441.
public class LargestPositiveIntegerThatExistsWithItsNegative {
    public static void main(String[] args) {
        assert(showResults(new int[] {-1,2,-3,3}) == 3); // expect 3
        assert(showResults(new int[] {-1,10,6,7,-7,1}) == 7); // expect 7
        assert(showResults(new int[] {-10,8,6,7,-2,-3}) == -1); // expect -1
        assert(showResults(new int[] {-30,34,1,32,26,-9,-30,22,-49,29,48,47,38,4,43,12,-1,-8,11,-37,32,40,9,15,-34,-34,-16,-5,26,-44,-36,-13,-16,10,39,-17,-22,17,-16}) == 34); // expect 34
    }

    private static int showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        int rs = findMaxK(nums);
        System.out.printf("%s\nRS: %d\n\n", Arrays.toString(nums), rs);
        return rs;
    }

    // Time: O(n), space: O(n)
    public static int findMaxK(int[] nums) {
        Set<Integer> val = new HashSet<>();
        int maxK = -1;
        for(int i = 0; i < nums.length; ++i) {
            if(val.contains(nums[i] * -1)) maxK = Math.max(maxK, Math.abs(nums[i]));
            else val.add(nums[i]);
        }
        
        return maxK;
    }
}
