import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// leetcode 1700.
public class NumberOfStudentsUnableToEatLunch {
	public static void main(String[] args) {
		int[] students1 = {1,1,0,0};
		int[] sandwiches1 = {0,1,0,1};
		showResults(students1, sandwiches1); // expect 0

		int[] students2 = {1,1,1,0,0,1};
		int[] sandwiches2 = {1,0,0,0,1,1};
		showResults(students2, sandwiches2); // expect 3
	}

	private static void showResults(int[] students, int[] sandwiches) {
		System.out.println("----ShowResults----");
		System.out.println("STUDENTS: " + Arrays.toString(students));
		System.out.println("SANDWICHES: " + Arrays.toString(sandwiches));
		int rs = countStudents(students, sandwiches);
		System.out.printf("RS: %d\n\n", rs);
	}

	// Constraints:
	// sandwiches[i] = 0 or 1 is circular or square sandwich respectively.
	// students.length == sandwiches.length

	// Time: O(n), space: O(n)
	public static int countStudents(int[] students, int[] sandwiches) {
		if(students.length == 0 || sandwiches.length == 0) return students.length;
		
		Deque<Integer> queue = new LinkedList<>();
		for(int student: students)
			queue.addLast(student);

		int i = 0;
		int size = queue.size(); // dummy value
		while(size != 0) {
			size = queue.size();
			int currSize = queue.size();
			while(currSize-- != 0) {
				int studentNeed = queue.removeFirst();
				if(studentNeed == sandwiches[i]) i++;
				else queue.addLast(studentNeed);
			}

			if(queue.size() == size) return queue.size();
		}

		return queue.size();
	}
}
