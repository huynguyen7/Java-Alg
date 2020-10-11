import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// element-prog 18.2
public class ScheduleToMinimizeWaitingTime {
	public static void main(String[] args) {
		int[] t1 = {2,5,1,3};
		showResults(t1); // expect 10
		
		int[] t2 = {};
		showResults(t2); // expect 0
	}

	private static void showResults(int[] serviceTimes) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(serviceTimes));
		
		List<Integer> input = new ArrayList<>();
		for(int i: serviceTimes)
			input.add(i);
		int rs = minimumTotalWaitingTime(input);
		System.out.printf("Minimum Total Waiting Time: %d\n\n", rs);
	}

	// The time a query waits before its turn comes is called its waiting time.

	// Greedy approach.
	// Time: O(nlogn), space: O(1)
	public static int minimumTotalWaitingTime(List<Integer> serviceTimes) {
		Collections.sort(serviceTimes); // time: O(nlogn)
		
		int minTotalWaitingTime = 0;
		int waitingTime = 0;
		for(int i = 0; i < serviceTimes.size() - 1; ++i) {
			waitingTime += serviceTimes.get(i);
			minTotalWaitingTime += waitingTime;
		}

		return minTotalWaitingTime;
	}
}
