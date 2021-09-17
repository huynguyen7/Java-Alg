import java.util.*;

// leetcode 1287.
public class ElementAppearingMoreThanTwentyFivePercentInSortedArray {
    public static void main(String[] args) {
        showResults(new int[] {1,2,2,6,6,6,6,7,10}); // expect 6
        showResults(new int[] {1,1}); // expect 1
        showResults(new int[] {1,2,3,3}); // expect 3
        showResults(new int[] {1,2,3,4,5,6,7,8,9,10,11,12,12,12,12}); // expect 12
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));

        System.out.printf("Element appear more than 25 perc: %d\n\n", findSpecialInteger(nums)); // Best approach with binary search.
        //System.out.printf("Element appear more than 25 perc: %d\n\n", findSpecialIntegerI(nums)); // no additional space approach.
        //System.out.printf("Element appear more than 25 perc: %d\n\n", findSpecialIntegerII(nums)); // brute force approach.
    }

    // Best approach with binary search.
    // Time: O(logn), space: O(1)
    public static int findSpecialInteger(int[] nums) {
        final int n = nums.length;
        final int quarter = (int) n/4;

        if(quarter == 0) return nums[0];

        for(int i = 0; i <= n-quarter; i += quarter) {
            int firstIndex = binarySearchFirstOccur(nums, nums[i]);
            int lastIndex = binarySearchLastOccur(nums, nums[i]);
            if(firstIndex == -1 || lastIndex == -1) continue;
            if(lastIndex - firstIndex + 1 > quarter)
                return nums[i];
        }
        
        return -1; // Cannot find.
    }

    private static int binarySearchFirstOccur(int[] nums, int val) {
        if(nums[0] == val) return 0;

        int low = 0, high = nums.length-1;

        while(low < high) {
            int mid = (low+high)/2;
            if(nums[mid] == val) {
                if(nums[mid-1] != val) return mid;
                else high = mid;
            } else if(nums[mid] < val) low = mid;
            else high = mid;
        }

        return -1; // Cannot find.
    }

    private static int binarySearchLastOccur(int[] nums, int val) {
        if(nums[nums.length-1] == val) return nums.length-1;
        int low = 0, high = nums.length-1;

        while(low < high) {
            int mid = (low+high)/2;
            if(nums[mid] == val) {
                if(nums[mid+1] != val) return mid;
                else low = mid;
            } else if(nums[mid] < val) low = mid;
            else high = mid;
        }

        return -1; // Cannot find.
    }

    // Better approach with no extra space usage.
    // Time: O(n), space: O(1)
    public static int findSpecialIntegerI(int[] nums) {
        final int n = nums.length;
        final int quarter = (int) n/4;

        for(int i = 0; i < n-quarter; ++i) {
            if(nums[i] == nums[i+quarter])
                return nums[i];
        }

        return -1; // Cannot find such value.
    }

    // Brute force approach.
    // Time: O(n), space: O(n)
    public static int findSpecialIntegerII(int[] nums) {
        final int n = nums.length;

        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums)
            map.put(num, map.getOrDefault(num,0)+1);

        int ans = nums[0];
        int freq = 0;

        for(Map.Entry<Integer, Integer> kv: map.entrySet()) {
            if(kv.getValue() > freq) {
                freq = kv.getValue();
                ans = kv.getKey();
                if((float) freq/n > 0.25) return ans;
            }
        }

        return ans;
    }
}
