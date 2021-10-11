import java.util.*;

// leetcode 2032.
public class TwoOutOfThree {
    public static void main(String[] args) {
        showResults(new int[] {1,1,3,2}, new int[] {2,3}, new int[] {3}); // expect [3,2]
        showResults(new int[] {3,1}, new int[] {2,3}, new int[] {1,2}); // expect [2,3,1]
        showResults(new int[] {1,2,2}, new int[] {4,3,3}, new int[] {5}); // expect []
    }

    private static void showResults(int[] nums1, int[] nums2, int[] nums3) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
        System.out.println(Arrays.toString(nums3));
        System.out.printf("--> %s\n", twoOutOfThree(nums1, nums2, nums3));
    }

    // c = 100
    // Time: O(c), space: O(n)
    public static List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        List<Integer> rs = new ArrayList<>();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        Set<Integer> set3 = new HashSet<>();

        for(int num: nums1)
            set1.add(num);
        for(int num: nums2)
            set2.add(num);
        for(int num: nums3)
            set3.add(num);
        
        for(int i = 1; i <= 100; ++i) {
            int count = 0;
            if(set1.contains(i)) count++;
            if(set2.contains(i)) count++;
            if(set3.contains(i)) count++;

            if(count >= 2) rs.add(i);
        }

        return rs;
    }
}
