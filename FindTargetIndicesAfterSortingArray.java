import java.util.*;

// leetcode 2089.
public class FindTargetIndicesAfterSortingArray {
    public static void main(String[] args) {
        showResults(new int[] {1,2,5,2,3}, 2); // expect [1,2]
        showResults(new int[] {1,2,5,2,3}, 3); // expect [3]
        showResults(new int[] {1,2,5,2,3}, 5); // expect [4]
        showResults(new int[] {1,2,5,2,3}, 4); // expect []
    }

    private static void showResults(int[] nums, int target) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%d\n%s\n%s\n\n", target, Arrays.toString(nums),
                        targetIndices(nums, target).toString());
    }

    private static int binarySearchLeftMost(int[] nums, int target) {
        int low = 0, high = nums.length-1;
        int rs = -1;

        while(low <= high) {
            int mid = low+(high-low)/2;
            if(target == nums[mid]) {
                high = mid-1;
                rs = mid;
            } else if(target < nums[mid])
                high = mid-1;
            else low = mid+1;
        }
        
        
        return rs;
    }

    private static int binarySearchRightMost(int[] nums, int target) {
        int low = 0, high = nums.length-1;
        int rs = -1;

        while(low <= high) {
            int mid = low+(high-low)/2;
            if(target >= nums[mid]){
                rs = mid;
                low = mid+1;
            }
            else if(target > nums[mid])
                low = mid+1;
            else high = mid-1;
        }
        
        return rs;
    }

    // Time: O(nlogn), space: O(n)
    public static List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        int leftMostIndex = binarySearchLeftMost(nums, target);
        int rightMostIndex = binarySearchRightMost(nums, target);

        System.out.printf("%d %d\n", leftMostIndex, rightMostIndex);
        if(leftMostIndex == -1 || rightMostIndex == -1)
            return Collections.emptyList();

        List<Integer> rs = new ArrayList<>();
        for(int i = leftMostIndex; i <= rightMostIndex; ++i)
            rs.add(i);

        return rs;
    }
}
