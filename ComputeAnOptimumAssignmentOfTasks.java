import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// element-prog 18.1
public class ComputeAnOptimumAssignmentOfTasks {
	public static void main(String[] args) {
		int[] t1 = {5,2,1,6,4,4};
		showResults(t1); // expect [5,2], [1,6], [4,4]
						// all tasks will be finished after 8 hours.
		
		int[] t2 = {5,7,1,9,2,3};
		showResults(t2); // expect [1,9], [2,7], [3,5]
						// all tasks will be finished after 10 hours.
	}

	private static void showResults(int[] taskDurations) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(taskDurations));

		List<Integer> input = new ArrayList();
		for(int i: taskDurations)
			input.add(i);
		
		List<PairedTasks> rs = getOptimumPairedTasks(input);
		System.out.println(rs.toString());
	}

	// RULES:
	// Each worker must be assigned exactly 2 tasks.
	
	// Time: O(nlogn), space: O(n)
	public static List<PairedTasks> getOptimumPairedTasks(List<Integer> taskDurations) {
		List<PairedTasks> rs = new ArrayList<>();
		if(taskDurations.size() == 0) return rs;

		Collections.sort(taskDurations);
		int i = 0, j = taskDurations.size() - 1;
		while(i < j)
			rs.add(new PairedTasks(taskDurations.get(i++), taskDurations.get(j--)));
		
		return rs;
	}

	static class PairedTasks {
		int task1;
		int task2;

		public PairedTasks(int task1, int task2) {
			this.task1 = task1;
			this.task2 = task2;
		}

		@Override
		public String toString() {
			return String.format("[%d,%d]", task1, task2);
		}
	}
}

// Constraints:
// taskDurations.length % 2 == 0
