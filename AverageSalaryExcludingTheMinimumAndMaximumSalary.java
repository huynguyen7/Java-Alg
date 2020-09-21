import java.util.Arrays;

// leetcode 1491.
public class AverageSalaryExcludingTheMinimumAndMaximumSalary {
	public static void main(String[] args) {
		int[] nums1 = {4000,3000,1000,2000};
		showResults(nums1); // expect 2500

		int[] nums2 = {1000,2000,3000};
		showResults(nums2); // expect 2000
	}

	private static void showResults(int[] salary) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(salary));
		System.out.printf("Average: %f\n\n", average(salary));
	}

	// Time: O(n), space: O(1)
	public static double average(int[] salary) {
		double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
		double sum = 0;
		for(int i = 0; i < salary.length; ++i) {
			min = Math.min(min, salary[i]);
			max = Math.max(max, salary[i]);
			sum += salary[i];
		}

		sum = sum - min - max;
		return sum / (salary.length - 2);
	}
}
