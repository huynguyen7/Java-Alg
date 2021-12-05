import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// leetcode 2094.
public class Finding3DigitEvenNumbers {
    public static void main(String[] args) {
        showResults(new int[] {2,1,3,0}); // expect [102,120,130,132,210,230,302,310,312,320]
        showResults(new int[] {2,2,8,8,2}); // expect [222,228,282,288,822,828,882]
        showResults(new int[] {3,7,5}); // expect []
        showResults(new int[] {0,2,0,0}); // expect [200]
        showResults(new int[] {0,0,0}); // expect []
    }

    private static void showResults(int[] digits) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s\n%s\n\n", Arrays.toString(digits),
                            Arrays.toString(findEvenNumbers(digits)));
    }
    
    // Time: O(n), space: O(n)
    public static int[] findEvenNumbers(int[] digits) {
        List<Integer> rs = new ArrayList<>();
        int[] digitCount = new int[10];

        for(int i = 0; i < digits.length; ++i)
            digitCount[digits[i]]++;

        for(int num = 100; num <= 999; ++num) {
            // If not evenly divided.
            if(num % 2 != 0) continue;
            
            // Check if we have the available digits.
            int a = num%10; // Last digit
            int b = (num%100)/10; // Second digit
            int c = (num%1000)/100; // First digit

            digitCount[a]--;
            digitCount[b]--;
            digitCount[c]--;

            if(digitCount[a] >= 0 && digitCount[b] >= 0 && digitCount[c] >= 0) 
                rs.add(num);

            digitCount[a]++;
            digitCount[b]++;
            digitCount[c]++;
        }

        return rs.stream().mapToInt(Integer::intValue).toArray();
    }
}
