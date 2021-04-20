import java.util.Arrays;

// leetcode 1822.
public class SignOfProductOfAnArray {
    public static void main(String[] args) {
        int[] nums1 = {-1,-2,-3,-4,3,2,1};
        showResults(nums1); // expect 1

        int[] nums2 = {1,5,0,2,-3};
        showResults(nums2); // expect 0

        int[] nums3 = {-1,1,-1,1,-1};
        showResults(nums3); // expect -1
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        System.out.printf("sgn = %d\n\n", arraySign(nums));
    }

    private static int sign(int num) {
         return num < 0 ? -1 : 1;
    }

    // Time: O(n), space: O(1)
    public static int arraySign(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int rs = 1;
        for(int num: nums) {
            if(num == 0) return 0;
            else rs *= sign(num);
        }

        return rs;
    }
}
