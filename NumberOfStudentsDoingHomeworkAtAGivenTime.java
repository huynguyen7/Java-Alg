import java.util.Arrays;

//leetcode 1450.
public class NumberOfStudentsDoingHomeworkAtAGivenTime {
	public static void main(String args[]) {
		int[] startTime1 = {1,2,3};
		int[] endTime1 = {3,2,7};
		int queryTime1 = 4;
		showResults(startTime1, endTime1, queryTime1); //expect 1
		
		int[] startTime2 = {4};
		int[] endTime2 = {4};
		int queryTime2 = 4;
		showResults(startTime2, endTime2, queryTime2); //expect 1

		int[] startTime3 = {1,1,1,1};
		int[] endTime3 = {1,3,2,4};
		int queryTime3 = 7;
		showResults(startTime3, endTime3, queryTime3); //expect 0
		
		int[] startTime4 = {4};
		int[] endTime4 = {4};
		int queryTime4 = 5;
		showResults(startTime4, endTime4, queryTime4); //expect 0

		int[] startTime5 = {9,8,7,6,5,4,3,2,1};
		int[] endTime5 = {10,10,10,10,10,10,10,10,10};
		int queryTime5 = 5;
		showResults(startTime5, endTime5, queryTime5); //expect 5
	}

	private static void showResults(int[] startTime, int[] endTime, int queryTime) {
		System.out.println("----ShowResults----");
		System.out.println("Start time:\t" + Arrays.toString(startTime));
		System.out.println("End time:\t" + Arrays.toString(endTime));
		System.out.printf("Query time: %d\n", queryTime);
		System.out.printf("RESULTS: %d\n\n", busyStudent(startTime, endTime, queryTime));
	}

	//time: O(n), space: O(1); n is startTime.length
	public static int busyStudent(int[] startTime, int[] endTime, int queryTime) {
		int numBusyStudents = 0;
		for(int i = 0; i < startTime.length; ++i) //time: O(n)
			if(startTime[i] <= queryTime && queryTime <= endTime[i])
				numBusyStudents++;

		return numBusyStudents;
	}
}
