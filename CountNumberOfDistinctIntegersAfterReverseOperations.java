import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

// leetcode 2442.
public class CountNumberOfDistinctIntegersAfterReverseOperations {
    public static void main(String[] args) {
        assert(showResults(new int[] {1,13,10,12,31}) == 6); // expect 6
        assert(showResults(new int[] {2,2,2}) == 1); // expect 1
    }

    private static int showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        int rs = countDistinctIntegers(nums);
        System.out.printf("%s\nRS: %d\n\n", Arrays.toString(nums), rs);
        return rs;
    }

    // Time: O(n), space: O(n)
    public static int countDistinctIntegers(int[] nums) {
        final int n = nums.length;

        Set<Integer> seen = new HashSet<>();
        Set<Integer> created = new HashSet<>();

        for(int num: nums)
            seen.add(num);

        for(int num: seen) {
            int reversed = reverseDigits(num);
            if(!seen.contains(reversed))
                created.add(reversed);
        }

        return seen.size() + created.size();
    }

    private static int reverseDigits(int num) {
        int rs = 0;
        int numDigits = (int) Math.log10(num)+1;

        for(int i = numDigits; i >= 1; --i) {
            rs = rs*10 + num % 10;
            num /= 10;
        }
        
        return rs;
    }
}
