public class IsPrime {
	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		System.out.printf("%d -> is prime: %b\n", num, isPrimeII(num));
	}

	// iterative approach
	// Time: O(n^(1/2)), space: O(1)
	public static boolean isPrimeI(int num) {
		num = num < 0 ? num * -1 : num;
		if(num <= 1) return false;
		
		int counter = (int) Math.sqrt(num);
		while(counter != 1) {
			if(num % counter == 0) return false;
			counter--;
		}

		return true;
	}

	// recursive approach
	// Time: O(n^(1/2)), space: O(n^(1/2))
	public static boolean isPrimeII(int num) {
		num = num < 0 ? num * -1 : num;
		if(num <= 1) return false;
		
		return dfs(num, (int) Math.sqrt(num));
	}
	
	private static boolean dfs(int num, int counter) {
		if(counter == 1) return true;
		if(num % counter == 0) return false;		

		return dfs(num, counter - 1);
	}
}
