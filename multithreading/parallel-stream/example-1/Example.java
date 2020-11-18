import java.util.stream.*;
import java.util.*;

public class Example {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		Student[] students = generateRandom(n);

		printTimeInSequential(students);
		printTimeInParallel(students);
	}

	private static Student[] generateRandom(int n) {
		Random rand = new Random();
		Student[] students = new Student[n];
		for(int i = 0; i < students.length; ++i)
			students[i] = new Student(rand.nextInt(100), rand.nextBoolean());
		return students;
	}

	private static void printTimeInSequential(Student[] students) {
		double startTime = System.nanoTime();
		
		List<Student> activeStudents = new ArrayList<>();
		for(Student student: students) {
			if(student.isActive)
				activeStudents.add(student);
		}

		int ageSum = 0;
		for(Student student: activeStudents)
			ageSum += student.age;

		int avg = ageSum / activeStudents.size();

		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;
		System.out.println("Time taken in sequential: " + (timeTaken / 1e9) + " seconds.");
		System.out.println("Average age = " + avg);
	}

	private static void printTimeInParallel(Student[] students) {
		double startTime = System.nanoTime();

		int avg = (int) Stream.of(students)
					.parallel()
					.filter(student -> student.isActive)
					.mapToInt(student -> student.age)
					.average()
					.getAsDouble();
		
		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;
		System.out.println("Time taken in parallel stream: " + (timeTaken / 1e9) + " seconds.");
		System.out.println("Average age = " + avg);
	}

	static class Student {
		int age;
		boolean isActive;
		
		Student(int age, boolean isActive) {
			this.age = age;
			this.isActive = isActive;
		}

		@Override
		public String toString() {
			return String.format("[%d, %b]", age, isActive);
		}
	}
}
