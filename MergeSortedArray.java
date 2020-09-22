import java.util.Arrays;

// leetcode 88, element-prog 14.2
public class MergeSortedArray {
	public static void main(String args[]) {
		int[] a1 = {1,2,3,0,0,0};
		int m1 = 3;
		int[] b1 = {2,5,6};
		int n1 = 3;
		showResults(a1, m1, b1, n1); // expect {1,2,2,3,5,6}
	}

	private static void showResults(int[] nums1, int m, int[] nums2, int n) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(nums1));
		System.out.println(Arrays.toString(nums2));
		System.out.printf("m = %d, n = %d\n\n", m, n);
		
		int[] rs = merge(nums1, m, nums2, n);
		System.out.println(Arrays.toString(rs) + "\n");
	}

	// better approach
	// Time: O(m + n), space: O(1)
	public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
		int index = nums1.length - 1;
		int i, j;
		
		for(i = m - 1, j = n - 1; i >= 0 && j >= 0; ) 
			nums1[index--] = nums1[i] >= nums2[j] ? nums1[i--] : nums2[j--];
		while(i >= 0)
			nums1[index--] = nums1[i--];
		while(j >= 0) // avoid if m == n
			nums1[index--] = nums2[j--];

		return nums1;
	}

	// naive approach
	// Time: O(m + n), space: O(m + n)
	// m is length of desired First elements in nums1, n is also for nums2
	public static int[] mergeII(int[] nums1, int m, int[] nums2, int n) {
		int[] aux = new int[m + n];
		
		int i = 0, j = 0; // i is used with nums1, j is used with nums2
		for(int k = 0; k < aux.length; ++k) {
			if(i == m) aux[k] = nums2[j++];
			else if(j == n) aux[k] = nums1[i++];
			else if(nums1[i] <= nums2[j]) aux[k] = nums1[i++];
			else aux[k] = nums2[j++];
		}

		return aux;
	}
}

// Constraints
// nums1.length = m + n
// nums2.length = n
