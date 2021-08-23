import java.util.Arrays;

// leetcode 1979.
public class FindGreatestCommonDivisorOfArray {
    public static void main(String[] args) {
        showResults(new int[] {2,5,6,9,10}); // expect 2
        showResults(new int[] {7,5,6,8,3}); // expect 1
        showResults(new int[] {3,3}); // expect 3
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        System.out.printf("OUTPUT: %d\n\n", findGCD(nums));
    }

    // Time: O(n), space: O(1)
    public static int findGCD(int[] nums) {
        int smallest = nums[0];
        int largest = nums[0];

        for(int num: nums) { // time: O(n)
            smallest = Math.min(smallest, num);
            largest = Math.max(largest, num);
        }
        
        int div = 1, next = div;
        if(smallest == largest || largest % smallest == 0) return smallest;
        else {
            while(next <= smallest/2) {
                next++;
                if(smallest % next == 0 && largest % next == 0)
                    div = next;
            }
        }

        return div;
    }
}
