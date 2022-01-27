import java.util.Arrays;

public class CountElementsWithStrictlySmallerAndGreaterElements {
    public static void main(String[] args) {
        assert(showResults(new int[] {11,7,2,15}) == 2); // expect 2
        assert(showResults(new int[] {-3,3,3,90}) == 2); // expect 2
    }

    private static int showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        int rs = countElements(nums);
        System.out.printf("-> %d\n\n", rs);

        return rs;
    }

    // Time: O(nlogn), space: O(1)
    public static int countElements(int[] nums) {
        final int n = nums.length;
        Arrays.sort(nums); // time: O(nlogn)

        int low = 0, high = n-1;
        while(low+1 < n && nums[low] == nums[low+1])
            low++;
        while(high-1 >= 0 && nums[high] == nums[high-1])
            high--;

        int count = 0;
        if(high > low)
            count = high-low-1;

        return count;
    }
}
