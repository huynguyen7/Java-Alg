import java.util.PriorityQueue;
import java.util.Queue;

// leetcode 1796.
public class SecondLargestDigitInAString {
    public static void main(String[] args) {
        showResults("dfa12321afd"); // expect 2
        showResults("abc1111"); // expect -1
        showResults("sjhtz8344"); // expect 4
    }

    private static void showResults(String s) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s -> %d\n\n", s, secondHighest(s));
    }

    // Time: O(nlogn), space: O(1)
    public static int secondHighest(String s) {
        if(s == null || s.length() < 2) return -1;

        Queue<Integer> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

        for(int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if(!Character.isDigit(c)) continue;

            int digit = ((int) c) - 48;
            if(!minHeap.contains(digit))
                minHeap.add(digit);
            if(minHeap.size() > 2) minHeap.poll();
        }

        if(minHeap.size() < 2) return -1;
        
        return minHeap.poll();
    }
}

/*
 * CONSTRAINTS:
 * - Input s consists of only lowercase English letters and/or digits.
 */
