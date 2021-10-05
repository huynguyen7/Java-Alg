import java.util.Arrays;

// leetcode 540.
public class SingleElementInASortedArray {
    public static void main(String[] args) {
        showResults(new int[] {1,1,2,3,3,4,4,8,8}); // expect 2
        showResults(new int[] {3,3,7,7,10,11,11}); // expect 10
        showResults(new int[] {1,1,2,2,3}); // expect 3
        showResults(new int[] {0,1,1,2,2}); // expect 0
        showResults(new int[] {1}); // expect 1
        showResults(new int[] {0,0,1}); // expect 1
        showResults(new int[] {1,2,2}); // expect 1
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        int ans = singleNonDuplicate(nums);
        System.out.printf("Single element: %d\n\n", ans);
    }

    // Time: O(logn), space: O(logn)
    public static int singleNonDuplicate(int[] nums) {
        return binSearch(nums, 0, nums.length-1);
    }

    // Helper method for singleNonDuplicate().
    private static int binSearch(int[] nums, int low, int high) {
        if(low > high) return -1;

        int mid = (low+high) >> 1;
        boolean flag = mid%2 == 0;

        boolean hasDup = false;
        if(mid > low && nums[mid-1] == nums[mid]) hasDup = true;
        else if(mid < high && nums[mid+1] == nums[mid]) hasDup = true;
        
        if(!hasDup) return nums[mid];
        else if(flag) {
            if (nums[mid] != nums[mid+1]) return binSearch(nums, low, mid);
            else return binSearch(nums, mid+2, high);
        } else {
            if(nums[mid] != nums[mid-1]) return binSearch(nums, low, mid-1);
            else return binSearch(nums, mid+1, high);
        }
    }

    // Time: O(n), space: O(1)
    public static int singleNonDuplicateI(int[] nums) {
        int ans = nums[0];
        for(int i = 1; i < nums.length; ++i)
            ans ^= nums[i]; // Using XOR op to eliminate evenly appeared elements.
        return ans;
    }
}
