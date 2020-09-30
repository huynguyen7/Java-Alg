public class IsPrime {
	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		System.out.printf("%d -> is prime: %b\n", num, isPrime(num));
	}

	// Time: O(n^(1/2)), space: O(1)
	public static boolean isPrime(int num) {
		num = num < 0 ? num * -1 : num;
		if(num <= 1) return false;
		
		int counter = (int) Math.sqrt(num);
		while(counter != 1) {
			if(num % counter == 0) return false;
			counter--;
		}

		return true;
	}
}
