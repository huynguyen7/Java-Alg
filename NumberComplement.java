import java.util.List;
import java.util.ArrayList;

// leetcode 476.
public class NumberComplement {
    public static void main(String[] args) {
        //findComplement(Integer.parseInt(args[0]));
        showResults(5); // expect  2
        showResults(1); // expect 0
    }

    private static void showResults(int num) {
        System.out.println("\t----ShowResults----");
        int rs = findComplement(num);
        System.out.printf("%d -> %d\n\n", num, rs);
    }
    
    // Constraints:
    // num >= 1

    // m is the length of num in binary.
    // Time: O(m), space: O(m)
    public static int findComplement(int num) {
        if(num == 1) return 0;
        List<Integer> bits = new ArrayList<>();

        while(num != 0) {
            if(num % 2 == 0) bits.add(0);
            else bits.add(1);
            num /= 2;
        }

        int rs = 0;
        for(int i = 0; i < bits.size(); ++i)
            if(bits.get(i) == 0) rs += (int) (1 * Math.pow(2, i));

        return rs;
    }
}
