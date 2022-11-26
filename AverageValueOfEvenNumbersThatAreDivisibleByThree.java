import java.util.Arrays;

// leetcode 2455.
public class AverageValueOfEvenNumbersThatAreDivisibleByThree {
    public static void main(String[] args) {
        assert(showResults(new int[] {1,3,6,10,12,15}) == 9); // expect 9
        assert(showResults(new int[] {1,2,4,7,10}) == 0); // expect 0
        assert(showResults(new int[] {4,4,9,10}) == 0); // expect 0
        assert(showResults(new int[] {9,3,8,4,2,5,3,8,6,1}) == 6); // expect 6
    }

    private static int showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        int rs = averageValue(nums);
        System.out.printf("%s\n-> %d\n\n", Arrays.toString(nums), rs);
        return rs;
    }

    // Time: O(n), space: O(1)
    public static int averageValue(int[] nums) {
        final int n = nums.length;
        int count = 0, sum = 0;
        for(int num: nums) {
            if(isEven(num) && isDivisibleByThree(num)) {
                count += 1;
                sum += num;
            }
        }

        return count > 0 ? sum / count : 0;
    }

    public static boolean isEven(int num) {
        return num % 2 == 0;
    }

    public static boolean isDivisibleByThree(int num) {
        return num % 3 == 0;
    }
}
