import java.util.stream.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Example {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		Student[] students = generateRandom(n);
		
		executeInSequential(students);
		executeInParallelStream(students);
	}

	private static void executeInSequential(Student[] students) {
		double startTime = System.nanoTime();
		
		Map<String, Integer> mapNameInactive = new HashMap<>();
		int ageSum = 0;
		int numStudentsIsEnrolled = 0;
		int numFailedStudentsOlderThan20 = 0;

		for(Student student: students) {
			if(student.isEnrolled) {
				ageSum += student.age;
				numStudentsIsEnrolled++;
			}
			if(!student.isActive)
				mapNameInactive.put(student.firstName, mapNameInactive.getOrDefault(student.firstName, 0) + 1);
			if(!student.testPassed && student.age > 20)
				numFailedStudentsOlderThan20++;
		}

		int avgAgeOfEnrolledStudents = ageSum / numStudentsIsEnrolled;
		
		String mostCommonFirstNameOfInactiveStudents = "";
		int currCount = Integer.MIN_VALUE;
		for(Map.Entry<String, Integer> entry: mapNameInactive.entrySet()) {
			if(entry.getValue() > currCount) {
				currCount = entry.getValue();
				mostCommonFirstNameOfInactiveStudents = entry.getKey();
			}
		}
		
		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;
		System.out.println("Time taken: " + timeTaken + " ms.");
		
		System.out.println("Average Age Of Enrolled Students: " + avgAgeOfEnrolledStudents);
		System.out.println("Most Common First Name Of Inactive Students: " + mostCommonFirstNameOfInactiveStudents);
		System.out.println("Number of Failed Students Older Than 20: " + numFailedStudentsOlderThan20 + "\n");
	}

	private static void executeInParallelStream(Student[] students) {
		double startTime = System.nanoTime();

		int avgAgeOfEnrolledStudents = (int) Stream.of(students)
								.parallel()
								.filter(student -> student.isEnrolled)
								.mapToInt(student -> student.age)
								.average()
								.getAsDouble();
		String mostCommonFirstNameOfInactiveStudents = Stream.of(students)
.parallel()
.filter(student -> !student.isActive)
.distinct()

		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;
		System.out.println("Time taken: " + timeTaken + " ms.");
	}

	private static final String[] names = {"HUY", "TOAN", "SANG", "QUOC", "HOA", "HUNG", "DUY", "AN"};

	private static Student[] generateRandom(int n) {
		Student[] students = new Student[n];
		Random rand = new Random();
		for(int i = 0; i < students.length; ++i)
			students[i] = new Student(
							rand.nextInt(100),
							names[rand.nextInt(8)],
							rand.nextBoolean(),
							rand.nextBoolean(),
							rand.nextBoolean()
						);
		return students;
	}

	static class Student implements Comparable{
		int age;
		String firstName;
		boolean isEnrolled;
		boolean isActive;
		boolean testPassed;

		public Student(int age, String firstName, boolean isEnrolled, boolean isActive, boolean testPassed) {
			this.age = age;
			this.firstName = firstName;
			this.isEnrolled = isEnrolled;
			this.isActive = isActive;
			this.testPassed = testPassed;
		}

		public int compareTo(Student another) {
			return firstName.compareTo(another.firstName);
		}
	}
}
