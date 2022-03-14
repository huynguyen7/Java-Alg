import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

// leetcode 2200.
public class FindAllKDistantIndicesInAnArray {
    public static void main(String[] args) {
        showResults(new int[] {3,4,9,1,3,9,5}, 9, 1); // expect [1,2,3,4,5,6]
        showResults(new int[] {2,2,2,2,2}, 2, 2); // expect [0,1,2,3,4]
    }

    private static void showResults(int[] nums, int key, int k) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        List<Integer> rs = findKDistantIndices(nums, key, k);
        System.out.printf("%d %d\n%s\n\n", key, k, rs.toString());
    }

    // Time: O(n), space: O(n)
    public static List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> rs = new ArrayList<>();
        List<Integer> matchingIndices = new ArrayList<>();

        final int n = nums.length;
        int i, j;

        for(i = 0; i < n; ++i) {
            if(nums[i] == key)
                matchingIndices.add(i);
        }

        int numMatches = matchingIndices.size();
        if(numMatches == 0) return rs;

        for(i = 0; i < n; ++i) {
            int leftBound = i-k < n ? i-k : n-1;
            int rightBound = i+k >= 0 ? i+k : 0;
            
            boolean isFound = false;
            for(int index = 0; index < numMatches; ++index) {
                if(matchingIndices.get(index) >= leftBound && matchingIndices.get(index) <= rightBound) {
                    isFound = true;
                    break;
                }
            }
            if(isFound) rs.add(i);
        }

        return rs;
    }
}
