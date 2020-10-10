import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

// leetcode 860.
public class LemonadeChange {
	public static void main(String[] args) {
		int[] nums1 = {5,5,5,10,20};
		showResults(nums1); // expect true
		
		int[] nums2 = {5,5,10};
		showResults(nums2); // expect true

		int[] nums3 = {10,10};
		showResults(nums3); // expect false

		int[] nums4 = {5,5,10,10,20};
		showResults(nums4); // expect false
	}

	private static void showResults(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		
		boolean rs = lemonadeChangeI(nums);
		System.out.printf("Is able to pay change: %b\n\n", rs);
	}

	// RULES:
	// Each lemonade cost 5$.
	// Customers are standing and buying in the order of bills array.

	// Time: O(n), space: O(1)
	public static boolean lemonadeChangeII(int[] bills) {
		Map<Integer, Integer> map = new HashMap<>(); // <bill-freq> pairs.
		for(int i = 0; i < bills.length; ++i) {
			if(bills[i] == 5) {
				map.put(5, map.getOrDefault(5, 0) + 1);
			} else if(bills[i] == 10) {
				map.put(10, map.getOrDefault(10, 0) + 1);
				
				if(map.containsKey(5) && map.get(5) > 0)
					map.put(5, map.get(5) - 1);
				else return false;
			} else { // bills[i] = 20
				int total = 20;
				if(map.containsKey(10) && map.get(10) > 0) {
					total -= 10;
					map.put(10, map.get(10) - 1);
					if(map.containsKey(5) && map.get(5) > 0) {
						total -= 5;
						map.put(5, map.get(5) - 1);
					}
				} else { // there is no 10 bill exists.
					if(map.containsKey(5) && map.get(5) >= 3) {
						total -= (5*3);
						map.put(5, map.get(5) - 3);
					}
				}

				if(total != 5) return false;
			}
		}

		return true;
	}
}

// Constraints:
// bills[i] = 5 or 10 or 20.
