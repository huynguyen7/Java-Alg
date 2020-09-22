import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

//leetcode 349, element-prog 14.1
public class IntersectionOfTwoArrays {
    public static void main(String args[]) {
        int[] a1 = { 1, 2, 2, 1 };
        int[] b1 = { 2, 2 };
        showResults(a1, b1); // expect {2}

        int[] a2 = { 4, 9, 5 };
        int[] b2 = { 9, 4, 9, 8, 4 };
        showResults(a2, b2); // expect {9,4}
    }

    private static void showResults(int[] nums1, int[] nums2) {
        System.out.println("----ShowResults----");
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));

        System.out.println("OUTPUT");
        int[] rs = intersection(nums1, nums2);
        System.out.println(Arrays.toString(rs) + "\n");
    }

	// Time: O(n + mlogm), space: O(n)
	// n is nums1.length, m is nums2.length
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        List<Integer> holder = new ArrayList<>();

        for (int i = 0; i < nums1.length; ++i)
            set.add(nums1[i]);

        Arrays.sort(nums2); // time: O(mlogm)
        Iterator<Integer> iter = set.iterator();
        while (iter.hasNext()) { // time: O(m)
            int val = iter.next();
            if (binarySearch(nums2, val, 0, nums2.length - 1)) // time: O(logm)
                holder.add(val);
        }

        int[] rs = new int[holder.size()];
        for (int i = 0; i < holder.size(); ++i)
            rs[i] = holder.get(i);

        return rs;
    }

    private static boolean binarySearch(int[] nums, int target, int lo, int hi) {
        if (lo > hi)
            return false;

        int mid = lo + (hi - lo) / 2;
        if (nums[mid] == target)
            return true;
        else if (nums[mid] > target)
            return binarySearch(nums, target, lo, mid - 1);
        else
            return binarySearch(nums, target, mid + 1, hi);
    }
}

// constraints:
// elements in the result must be UNIQUE.
