import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

// leetcode 2465.
public class NumberOfDistinctAverages {
    public static void main(String[] args) {
        assert(showResults(new int[] {4,1,4,0,3,5}) == 2); // expect 2
        assert(showResults(new int[] {1,100}) == 1); // expect 1
    }

    private static int showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        int rs = distinctAverages(nums);
        System.out.printf("%s\n-> %d\n\n", Arrays.toString(nums), rs);
        return rs;
    }

    // Time: O(nlogn), space: O(n)
    public static int distinctAverages(int[] nums) {
        final int n = nums.length;
        Arrays.sort(nums);
        Set<Double> seen = new HashSet<>();

        int low = 0, high = n-1;
        while(low < high)
            seen.add((nums[low++] + nums[high--])/2.0);

        return seen.size();
    }
}
