import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

// leetcode 2154.
public class KeepMultiplyingFoundValuesByTwo {
    public static void main(String[] args) {
        assert(showResults(new int[] {5,3,6,1,12}, 3) == 24); // expect 24
        assert(showResults(new int[] {2,7,9}, 4) == 4); // expect 4
    }

    private static int showResults(int[] nums, int original) {
        System.out.println("\t----ShowResults----");
        int rs = findFinalValue(nums, original);
        System.out.printf("%s\n%d -> %d\n\n", Arrays.toString(nums), original, rs);
        return rs;
    }

    // Time: O(n), space: O(n)
    public static int findFinalValue(int[] nums, int original) {
        Set<Integer> visited = new HashSet<>();
        for(int num: nums)
            visited.add(num);

        while(true) {
            // If not found, stop the process.
            if(!visited.contains(original)) break;
            original = original*2;
        }

        return original;
    }
}
