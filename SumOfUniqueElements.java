import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

// leetcode 1748.
public class SumOfUniqueElements {
    public static void main(String[] args) {
        int[] nums1 = {1,2,3,2};
        showResults(nums1); // expect 4

        int[] nums2 = {1,1,1,1,1};
        showResults(nums2); // expect 0

        int[] nums3 = {1,2,3,4,5};
        showResults(nums3); // expect 15
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        int rs = sumOfUnique(nums);
        System.out.printf("Results: %d\n\n", rs);
    }

    // Time: O(n), space: O(n)
    public static int sumOfUnique(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int sum = 0;
        
        Map<Integer, Boolean> map = new HashMap<>();
        for(int num: nums) {
            if(map.containsKey(num)) {
                if(map.get(num)) continue;
                else {
                    sum -= num;
                    map.put(num, true);
                }
            } else {
                sum += num;
                map.put(num, false);
            }
        }

        return sum;
    }
}
