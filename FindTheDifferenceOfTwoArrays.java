import java.util.*;


// leetcode 2215.
public class FindTheDifferenceOfTwoArrays {
    public static void main(String[] args) {
        showResults(new int[] {1,2,3}, new int[] {2,4,6}); // expect [[1,3],[4,6]]
        showResults(new int[] {1,2,3,3}, new int[] {1,1,2,2}); // expect [[3],[]]
    }
    
    private static void showResults(int[] nums1, int[] nums2) {
        System.out.println("\t----ShowResults----");
        List<List<Integer>> rs = findDifference(nums1, nums2);
        System.out.printf("%s\n%s\n%s\n\n", Arrays.toString(nums1),
                Arrays.toString(nums2), rs.toString());
    }

    // Time: O(n), space: O(n)
    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> rs = new ArrayList<>();
        Set<Integer> holder1 = new HashSet<>();
        Set<Integer> holder2 = new HashSet<>();

        for(int num: nums1)
            holder1.add(num);

        for(int num: nums2)
            holder2.add(num);

        List<Integer> tmp1 = new ArrayList<>();
        List<Integer> tmp2 = new ArrayList<>();

        Iterator<Integer> i = holder1.iterator();
        while(i.hasNext()) {
            Integer num = i.next();
            if(!holder2.contains(num))
                tmp1.add(num);
        }

        i = holder2.iterator();
        while(i.hasNext()) {
            Integer num = i.next();
            if(!holder1.contains(num))
                tmp2.add(num);
        }
        
        rs.add(tmp1);
        rs.add(tmp2);

        return rs;
    }
}
