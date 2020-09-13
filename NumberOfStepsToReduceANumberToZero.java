//leetcode 1342.
public class NumberOfStepsToReduceANumberToZero {
	public static void main(String args[]) {
		int num = Integer.parseInt(args[0]);
		System.out.printf("%d -> %d\n\n", num, numberOfSteps(num));
	}

	public static int numberOfSteps(int num) {
		int stepCounts = 0;
		while(num != 0) {
			stepCounts++;
			if(num % 2 == 0) num /= 2;
			else num -= 1;
		}
			
		return stepCounts;
	}
}

//constraints:
//num is non-negative integer
