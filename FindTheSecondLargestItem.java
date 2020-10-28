import java.util.Arrays;
import java.util.PriorityQueue;

public class FindTheSecondLargestItem {
	public static void main(String[] args) {
		int[] nums1 = {-1,10,8,9,10,9,-8,11};
		showResutls(nums1); // expect 10

		int[] nums2 = {2,1};
		showResutls(nums2); // expect 1

		int[] nums3 = {3,1,2};
		showResutls(nums3); // expect 2
	}

	private static void showResutls(int[] nums) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums));
		int rs = findSecondLargest(nums);
		System.out.printf("%d\n\n", rs);
	}

	// GOOD EXPLANATION:
	// https://www.youtube.com/watch?v=NheWPxGpoxQ

	// Best approach
	// Time: O(n), space: O(1)
	public static int findSecondLargest(int[] nums) {
		if(nums == null || nums.length <= 1) return Integer.MIN_VALUE;
		int largest = Integer.MIN_VALUE, secondLargest = Integer.MIN_VALUE;

		for(int num: nums) {
			if(num > largest) {
				secondLargest = largest;
				largest = num;
			} else if(num > secondLargest) {
				secondLargest = num;
			}
		}

		return secondLargest;
	}

	// Time: O(n), space: O(1)
	public static int findSecondLargestI(int[] nums) {
		if(nums == null || nums.length <= 1) return Integer.MIN_VALUE;
		int largest = nums[0], secondLargest = largest;

		for(int num: nums) {
			if(largest < num) {
				secondLargest = largest;
				largest = num;
			}
		}
		
		if(secondLargest == largest) {
			for(int num: nums) { // find num which differs from largest.
				if(num != largest) {
					secondLargest = num;
					break;
				}
			}

			for(int num: nums) { // find second largest.
				if(num != largest)
					secondLargest = Math.max(secondLargest, num);
			}
		}

		return secondLargest;
	}

	// Time: O(n), space: O(1)
	public static int findSecondLargestII(int[] nums) {
		if(nums == null || nums.length <= 1) return Integer.MIN_VALUE;

		PriorityQueue<Integer> minHeap = new PriorityQueue<>(
			(a, b) -> Integer.compare(a, b)
		);

		for(int num: nums) {
			if(!minHeap.contains(num))minHeap.add(num);
			if(minHeap.size() == 3)
				minHeap.remove();
		}

		return minHeap.peek() != null ? minHeap.peek() : Integer.MIN_VALUE;
	}
}
