import java.util.*;

// leetcode 2150.
public class FindAllLonelyNumbersInTheArray {
    public static void main(String[] args) {
        showResults(new int[] {10,6,5,8}); // expect [10,8]
        showResults(new int[] {1,3,5,3}); // expect [1,5]
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        List<Integer> rs = findLonelyII(nums);
        System.out.printf("%s\n\n", rs.toString());
    }

    // Using hashmap
    // Time: O(n), space: O(n)
    public static List<Integer> findLonely(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(); // freq of each value.
        List<Integer> rs = new ArrayList<>(); // resulting output.

        for(int num: nums)
            map.put(num, map.getOrDefault(num, 0)+1);

        for(int num: nums) {
            if(map.get(num) == 1 &&
                !map.containsKey(num-1) &&
                !map.containsKey(num+1))
                rs.add(num);
        }

        return rs;
    }
}
