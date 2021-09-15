import java.util.*;

// leetcode 1985.
public class FindTheKthLargestIntegerInTheArray {
    public static void main(String[] args) {
        showResults(new String[] {"3","6","7","10"}, 4); // expect "3"
        showResults(new String[] {"2","21","12","1"}, 3); // expect "2"
        showResults(new String[] {"0","0"}, 2); // expect "0"
        showResults(new String[] {"623986800","3","887298","695","794","6888794705","269409","59930972","723091307","726368","8028385786","378585"}, 11); // expect "695"
    }

    private static void showResults(String[] nums, int k) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        System.out.printf("%d -> %s\n\n", k, kthLargestNumber(nums, k));
    }

    // Time: O(nlogk), space: O(k)
    public static String kthLargestNumber(String[] nums, int k) {
        PriorityQueue<String> minHeap = new PriorityQueue<>(
                    (a,b) -> {
                        if(a.length() == b.length()) {
                            for(int i = 0; i < a.length(); ++i) {
                                if(a.charAt(i) > b.charAt(i)) return 1;
                                else if(a.charAt(i) < b.charAt(i)) return -1;
                            }
                            return 0;
                        }
                        return Integer.compare(a.length(), b.length());
                    }
                );

        for(String num: nums) {
            minHeap.add(num);
            if(minHeap.size() > k)
                minHeap.poll();
        }
        
        return minHeap.poll();
    }
}
