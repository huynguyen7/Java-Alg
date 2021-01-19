import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

// leetcode 1356.
public class SortIntegersByTheNumberOfOneBits {
	public static void main(String[] args) {
		int[] arr1 = {0,1,2,3,4,5,6,7,8};
		showResults(arr1); // expect [0,1,2,4,8,3,5,6,7]

		int[] arr2 = {1024,512,256,128,64,32,16,8,4,2,1};
		showResults(arr2); // expect [1,2,4,8,16,32,64,128,256,512,1024]

		int[] arr3 = {10000,10000};
		showResults(arr3); // expect [10000, 10000]

		int[] arr4 = {2,3,5,7,11,13,17,19};
		showResults(arr4); // expect [2,3,5,17,7,11,13,19]

		int[] arr5 = {10,100,1000,10000};
		showResults(arr5); // expect [10,100,1000,10000]
	}

	private static void showResults(int[] arr) {
		System.out.println("\t----ShowResults----");
		System.out.println(Arrays.toString(arr));
		int[] rs = sortByBits(arr);
		System.out.println(Arrays.toString(rs) + "\n");
	}

	// Time: O(nlogn), space: O(n)
	public static int[] sortByBits(int[] arr) {
		if(arr == null || arr.length <= 1) return arr;
		List<Integer> tmp = new ArrayList<>();
		for(int num: arr)
			tmp.add(num);

		tmp.sort((a, b) -> {
			int compare = Integer.compare(countNumOneBits(a), countNumOneBits(b));
			if(compare == 0) return Integer.compare(a, b);
			else return compare;
		});

		return tmp.stream().mapToInt(Integer::intValue).toArray();
	}

	private static int countNumOneBits(int num) {
		int count = 0;
		while(num != 0) {
			count += num % 2;
			num /= 2;
		}

		return count;
	}
}
