import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

// leetcode 442
public class FindAllDuplicatesInAnArray {
	public static void main(String[] args) {
		int[] nums1 = {4,3,2,7,8,2,3,1};
		showResults(nums1); // expect {2,3}

		int[] nums2 = {1,1};
		showResults(nums2); // expect {1}
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		List<Integer> rs = findDuplicatesI(nums);
		System.out.println(rs.toString() + "\n");
	}

    // best approach, CYCLIC TRAVERSING
    // Time: O(n), space: O(n)
    public static List<Integer> findDuplicatesI(int[] nums) {
        List<Integer> rs = new ArrayList<>();
        if(nums.length == 0) return rs;
        
        for(int i = 0; i < nums.length; ++i) {
            int x = Math.abs(nums[i]) - 1; // -1 is needed since nums[i] is from 1 <= val <= nums.length
            if(nums[x] < 0) rs.add(x + 1); // if negative value(visited element) 
            else nums[x] *= -1; // Mark visited elements with negative value
        }
        
        return rs;
    }
    
    // using set to check dup keys
    // Time: O(n), space: O(n)
    public static List<Integer> findDuplicatesII(int[] nums) {
        Set<Integer> set = new HashSet<>();
        List<Integer> rs = new ArrayList<>();
        
        for(int i = 0; i < nums.length; ++i) {
            if(set.contains(nums[i]))
                rs.add(nums[i]);
            set.add(nums[i]);
        }
        
        return rs;
    }
    
    // naive approach, with sorting
    // Time: O(nlogn), space: O(n)
    public static List<Integer> findDuplicatesIII(int[] nums) {
        List<Integer> rs = new ArrayList<>();
        if(nums.length == 0) return rs;
        
        Arrays.sort(nums);
        for(int i = 1; i < nums.length; ++i)
            if(nums[i] == nums[i - 1]) rs.add(nums[i]);
        
        return rs;
    }
}

// CONSTRAINTS:
// Duplicate elements appear TWICE
// Element value: 1 <= val <= n; n is array size
