import java.util.Arrays;

// leetcode 2161.
public class PartitionArrayAccordingToGivenPivot {
    public static void main(String[] args) {
        showResults(new int[] {9,12,5,10,14,3,10}, 10); // expect [9,5,3,10,10,12,14]
        showResults(new int[] {-3,4,3,2}, 2); // expect [-3,2,4,3]
    }

    private static void showResults(int[] nums, int pivot) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        int[] rs = pivotArray(nums, pivot);
        System.out.printf("%d\n%s\n\n", pivot, Arrays.toString(rs));
    }

    // Time: O(n), space: O(1)
    public static int[] pivotArray(int[] nums, int pivot) {
        final int n = nums.length;
        int smaller = 0;
        for(int i = 0; i < n; ++i) {
            if(nums[i] < pivot)
                swap(nums, smaller++, i);
        }

        int larger = n-1;
        for(int i = n-1; i >= 0 && nums[i] >= pivot; --i) {
            if(nums[i] > pivot)
                swap(nums, larger--, i);
        }

        return nums;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // Time: O(n), space: O(n)
    public static int[] pivotArrayII(int[] nums, int pivot) {
        final int n = nums.length;
        int[] rs = new int[n];
        int curr = 0;

        int countEqual = 0;

        // < pivot
        for(int i = 0; i < n; ++i) {
            if(nums[i] < pivot)
                rs[curr++] = nums[i];
            else if(nums[i] == pivot)
                countEqual += 1;
        }

        // == pivot
        while(countEqual != 0) {
            rs[curr++] = pivot;
            countEqual -= 1;
        }

        // > pivot
        for(int i = 0; i < n; ++i) {
            if(nums[i] > pivot)
                rs[curr++] = nums[i];
        }

        return rs;
    }
}
