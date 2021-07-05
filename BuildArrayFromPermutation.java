import java.util.Arrays;

// leetcode 1920.
public class BuildArrayFromPermutation {
    public static void main(String[] args) {
        int[] nums1 = {0,2,1,5,3,4};
        showResults(nums1); // expect [0,1,2,4,5,3]

        int[] nums2 = {5,0,1,2,3,4};
        showResults(nums2); // expect [4,5,0,1,2,3]
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        int[] rs = buildArray(nums);
        System.out.println(Arrays.toString(rs) + "\n");
    }

    // Better approach..
    // Time: O(n), space: O(1)
    public static int[] buildArray(int[] nums) {
        if(nums == null || nums.length == 0) return new int[0];

        final int n = nums.length;

        int tmp = 0, tmp1 = 0, idk = (int) Math.pow(2,10) - 1;
        for(int i = 0; i < nums.length; i++) {
            tmp = nums[nums[i]] & idk;
            nums[i] = nums[i] | (tmp << 10);
        }

        for(int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] >> 10;
        }

        return nums;
    }

    // Time: O(n), space: O(n)
    public static int[] buildArrayI(int[] nums) {
        if(nums == null || nums.length == 0) return new int[0];

        final int n = nums.length;
        int[] rs = new int[n];

        for(int i = 0; i < n; ++i)
            rs[i] = nums[nums[i]];

        return rs;
    }
}
