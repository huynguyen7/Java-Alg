import java.util.Arrays;

// leetcode 2023.
public class NumberOfPairsOfStringsWithConcatenationEqualToTarget {
    public static void main(String[] args) {
        showResults(new String[] {"777","7","77","77"}, "7777"); // expect 4
        showResults(new String[] {"123","4","12","34"}, "1234"); // expect 2
        showResults(new String[] {"1","1","1"}, "11"); // expect 6
    }
    
    private static void showResults(String[] nums, String target) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        System.out.printf("%s -> %d\n\n", target, numOfPairs(nums, target));
    }

    // Time: O(n^2), space: O(n^2)
    public static int numOfPairs(String[] nums, String target) {
        int count = 0;

        for(int i = 0; i < nums.length; ++i) {
            for(int j = 0; j < nums.length; ++j) {
                if(i == j || nums[i].length() + nums[j].length() != target.length()) continue;
                String concat = nums[i]+nums[j];
                if(concat.compareTo(target) == 0) count++;
            }
        }

        return count;
    }
}
