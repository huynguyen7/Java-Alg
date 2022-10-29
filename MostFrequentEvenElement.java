import java.util.*;

// leetcode 2404.
public class MostFrequentEvenElement {
    public static void main(String[] args) {
        assert(showResults(new int[] {0,1,2,2,4,4,1}) == 2); // expect 2
        assert(showResults(new int[] {4,4,4,9,2,4}) == 4); // expect 4
        assert(showResults(new int[] {29,47,21,41,13,37,25,7}) == -1); // expect -1
        assert(showResults(new int[] {0,0,0,0}) == 0); // expect 0
    }

    private static int showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        int rs = mostFrequentEven(nums); 
        System.out.println(Arrays.toString(nums));
        System.out.printf("-> %d\n\n", rs);
        return rs;
    }

    // Time: O(nlogn), space: O(n)
    public static int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums) {
            if(num % 2 == 0)
                map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>((a, b) -> {
            int comp = Integer.compare(map.get(b), map.get(a));
            if(comp == 0) return Integer.compare(a, b);
            return comp;
        });
        sortedMap.putAll(map);
        return sortedMap.isEmpty() ? -1 : sortedMap.firstKey();
    }
}
