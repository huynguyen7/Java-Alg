// leetcode 202.
public class HappyNumber {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		System.out.printf("%d -> %b\n\n", n, isHappy(n));
	}

	public static boolean isHappy(int n) {
		for(int i = 0; i < 100; i++) {
			int sumSquaredDigits = 0;
			
			while(n != 0) {
				sumSquaredDigits += (n % 10) * (n % 10);
				n /= 10;
			}
			
			if(sumSquaredDigits == 1) return true;
			n = sumSquaredDigits;
		}
		return false;
	}
}
