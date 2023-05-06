import java.util.ArrayList;
import java.util.List;

// Elements of Programming Interview 6.3
public class IncrementArbitraryPrecisionInteger {
    public static void main(String[] args) {
        List<Integer> l0 = new ArrayList<>();
        l0.add(1);
        l0.add(0);
        showResults(l0); // expect 11

        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        l1.add(2);
        l1.add(9);
        showResults(l1); // expect 130

        List<Integer> l2 = new ArrayList<>();
        l2.add(9);
        l2.add(9);
        l2.add(9);
        showResults(l2); // expect 1000
    }

    private static void showResults(List<Integer> num) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s\n", num.toString());
        increment(num);
        System.out.printf("%s\n\n", num.toString());
    }

    // Time: O(n), space: O(1)
    public static void increment(List<Integer> num) {
        if(num == null || num.size() == 0) return;
        final int n = num.size();
        num.set(n-1, num.get(n-1)+1); // increment
        
        for(int i = n-1; i >= 0 && num.get(i) == 10; i--) {
            num.set(i, 0);
            if(i-1 >= 0)
                num.set(i-1, num.get(i-1)+1);
        }

        if(num.get(0) == 10) {
            num.set(0, 0);
            num.add(0, 1);
        }
    }
}
