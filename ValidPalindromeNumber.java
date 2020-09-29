public class ValidPalindromeNumber {
	public static void main(String[] args) {
		int input = Integer.parseInt(args[0]);
		System.out.println(isPalindrome(input));
	}

	// Time: O(n), space: O(1)
	public static boolean isPalindrome(int num) {
		num = num < 0 ? num * (-1) : num;
		int numLength = (int) Math.log10(num) + 1;

		int right = numLength - 1;
		final int BASE = 10;
		
		while(num != 0) {
			int leftValue = num / (int) Math.pow(BASE, right);
			int rightValue = num % BASE;
			if(leftValue != rightValue) return false;
		
			num = num % (int) Math.pow(BASE, right);
			num /= BASE;
			right -= 2;
		}

		return true;
	}
}
