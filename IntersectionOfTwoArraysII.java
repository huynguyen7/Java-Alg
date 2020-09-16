import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

//leetcode 350.
public class IntersectionOfTwoArraysII {
    public static void main(String args[]) {
        int[] a1 = { 1, 2, 2, 1 };
        int[] b1 = { 2, 2 };
        showResults(a1, b1); // expect {2,2}

        int[] a2 = { 4, 9, 5 };
        int[] b2 = { 9, 4, 9, 8, 4 };
        showResults(a2, b2); // expect {4,9}

        int[] a3 = { 1, 2, 2, 1 };
        int[] b3 = { 2 };
        showResults(a3, b3); // expect {2}
    }

    private static void showResults(int[] nums1, int[] nums2) {
        System.out.println("----ShowResults----");
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
        int[] rs = intersect(nums1, nums2);

        System.out.println(Arrays.toString(rs) + "\n\n");
    }

    // time: O(m + n), space: O(m + n)
    public static int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> holder = new ArrayList<>();
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();

        for (int i : nums1)
            map1.put(i, map1.getOrDefault(i, 0) + 1);
        for (int i : nums2)
            map2.put(i, map2.getOrDefault(i, 0) + 1);

        for (int key : map1.keySet()) {
            while (map2.containsKey(key) && map2.get(key) != 0 && map1.get(key) != 0) {
                holder.add(key);
                map1.put(key, map1.get(key) - 1);
                map2.put(key, map2.get(key) - 1);
            }
        }

        int[] rs = new int[holder.size()];
        for (int i = 0; i < rs.length; ++i)
            rs[i] = holder.get(i);
        return rs;
    }
}
