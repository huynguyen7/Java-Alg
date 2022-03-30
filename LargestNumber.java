import java.util.Arrays;
import java.util.PriorityQueue;

// leetcode 179.
public class LargestNumber {
    public static void main(String[] args) {
        assert(showResults(new int[] {10,2}).compareTo("210") == 0); // expect 210
        assert(showResults(new int[] {3,30,34,5,9}).compareTo("9534330") == 0); // expect 9534330 
    }

    private static String showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        String rs = largestNumber(nums);
        System.out.printf("%s\n%s\n\n", Arrays.toString(nums), rs);
        return rs;
    }

    // Time: O(nlogn), space: O(n)
    public static String largestNumber(int[] nums) {
        if(nums.length == 0) return "";

        String[] numsS = new String[nums.length];
        
        for(int i = 0; i < nums.length; ++i)
            numsS[i] = "" + nums[i];
        Arrays.sort(numsS, (a,b) -> (b+a).compareTo(a+b)); // lexicographically sorted strings, also, if the characters are match, check whether if the next char is 0, very important..

        if(numsS[0].compareTo("0") == 0) return "0";

        StringBuilder s = new StringBuilder();
        for(String num: numsS)
            s.append(num);

        return s.toString();
    }
}
