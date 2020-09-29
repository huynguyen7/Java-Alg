public class Factorial {
	public static void main(String[] args) {
		int input = Integer.parseInt(args[0]);
		int rs = factorialI(input);
		System.out.printf("%d! = %d\n", input, rs);
	}

	// Time: O(n), space: O(1)
	public static int factorialI(int num) {
		if(num == 0) return 1;		

		int counter = 1;
		int rs = 1;
		
		while(counter++ < num)
			rs *= counter;

		return rs;
	}

	// DP approach.
	// bottom-up DP.
	// Time: O(n), space: O(n)
	public static int factorialII(int num) {
		int[] factorials = new int[num + 1];
		factorials[0] = 1; // base case
		
		for(int i = 1; i < factorials.length; ++i) {
			factorials[i] = i * factorials[i - 1];
		}

		return factorials[num];
	}

	// recursive approach
	// n = num
	// Time: O(n), space: O(n)
	public static int factorialIII(int num) {
		if(num == 0) return 1; // base case
		return factorialIII(num - 1) * num;
	}
}
